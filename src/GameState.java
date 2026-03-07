import java.util.ArrayList;
import java.util.Stack;
    
public class GameState {
    // parts of the gamestate
    private ArrayList<Player> players;
    private Table table;
    private Stack<Card> deck;
    private int currentPlayer; //1 player 1, 2 player 2, etc.
    private int stage; // 0 preflop, 1 flop, 2 turn, 3 river
    private int pot;
    
    public GameState(ArrayList<Player> p, Table t, Stack<Card> d, int cp, int s, int pt) { //game state constructor
        this.players = p;
        this.table = t;
        this.deck = d;
        this.currentPlayer = cp;
        this.stage = s;
        this.pot = pt;
    }

    public void applyMove(String move, int raiseAmount){ //apply's different moves
        Player p = players.get(currentPlayer);
        if (move.equals("Fold") || move.equals("fold")){
            p.fold();
        } else if (move.equals("Call") || move.equals("call")){
            int callAmount = 20;
            p.bet(callAmount);
            table.addPot(callAmount);
        } else if (move.equals("Raise") || move.equals("raise")){
            p.bet(raiseAmount);
            table.addPot(raiseAmount);
        }
        nextPlayer();
    }

    public GameState CloneState() { // clone state for the trees
        ArrayList<Player> newPlayers = new ArrayList<>(); //new array of copy players
        for (Player p : players){ //initiating the copied players and adding then to the new player array
            Player copy = new Player(p.getName(), p.getChips());
            for (Card c: p.gethand()) { copy.gethand().add(c); }
            if (p.isFolded) { copy.fold(); }
            newPlayers.add(copy);
        }
        
        Stack<Card> newDeck = new Stack<>(); //creating a new clone deck
        newDeck.addAll(this.deck);

        return new GameState(newPlayers, table.cloneTable(), newDeck, currentPlayer, stage, pot);
        //returns cloned gamestate with cloned elements
    }

    public void addPot(int amount) {
        this.pot += amount; 
        System.out.println("The pot has increased by $" + amount + ". Total Pot: $" + this.pot);
    }
    
    public ArrayList<Player> getPlayers() {
        return players;
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
}
