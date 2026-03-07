import java.util.ArrayList;
import java.util.Stack;

public class GameState {
    // parts of the gamestate
    private ArrayList<Player> players;
    private Table table;
    private Stack<Card> deck;
    private int currentPlayer; // 1 player 1, 2 player 2, etc.
    private int stage; // 0 preflop, 1 flop, 2 turn, 3 river
    private int pot;
    private int highestBetInRound = 0;

    public GameState(ArrayList<Player> p, Table t, Stack<Card> d, int cp, int s, int pt) { // game state constructor
        this.players = p;
        this.table = t;
        this.deck = d;
        this.currentPlayer = cp;
        this.stage = s;
        this.pot = pt;
    }

    public void applyMove(String move, int raiseAmount) {
        Player p = players.get(currentPlayer);
        
        if (move.equalsIgnoreCase("fold")) {
            p.fold();
        } 
        else if (move.equalsIgnoreCase("call")) {
            // Find out how much more this player needs to add to match the leader
            int amountNeeded = highestBetInRound - p.getCurrentBet();
            
            // If someone hasn't raised yet, a "Call" is just a minimum bet (e.g., matching the Big Blind)
            if (amountNeeded <= 0) amountNeeded = 20; 
    
            p.bet(amountNeeded);
            table.addPot(amountNeeded);
            this.addPot(amountNeeded); // Keep GameState.pot in sync
        } 
        else if (move.equalsIgnoreCase("raise")) {
            // A raise is the cost to Call + the extra raise amount
            int callAmount = highestBetInRound - p.getCurrentBet();
            int totalBet = callAmount + raiseAmount;
    
            p.bet(totalBet);
            table.addPot(totalBet);
            this.addPot(totalBet);
    
            // Crucial: Update the new "price" for the next player
            highestBetInRound = p.getCurrentBet();
        }
        nextPlayer();
    }

    public GameState CloneState() { // clone state for the trees
        ArrayList<Player> newPlayers = new ArrayList<>(); // new array of copy players
        for (Player p : players) { // initiating the copied players and adding then to the new player array
            Player copy = new Player(p.getName(), p.getChips());
            for (Card c : p.gethand()) {
                copy.gethand().add(c);
            }
            if (p.isFolded) {
                copy.fold();
            }
            copy.bet(p.getCurrentBet());
            newPlayers.add(copy);
        }

        Stack<Card> newDeck = new Stack<>(); // creating a new clone deck
        newDeck.addAll(this.deck);

        GameState cloned = new GameState(newPlayers, table.cloneTable(), newDeck, currentPlayer, stage, pot);
        cloned.setHighestBet(this.highestBetInRound);
        return cloned;
        // returns cloned gamestate with cloned elements
    }

    public void addPot(int amount) {
        this.pot += amount;
    }

    public ArrayList<Player> getPlayers() {
        return players;
    }

    public int getPot(){
        return this.pot;
    }

    public Table getTable() {
        return table;
    }

    public Stack<Card> getDeck() {
        return deck;
    }

    public int getCurrentPlayer() {
        return currentPlayer;
    }

    public int getStage() {
        return stage;
    }

    public void nextPlayer() {
        currentPlayer = (currentPlayer + 1) % players.size();
    }

    public void setHighestBet(int amount) {
        this.highestBetInRound = amount;
    }
}
