import java.util.ArrayList;
import java.util.Stack;
    
public class GameState {
    private ArrayList<Player> players;
    private Table table;
    private Stack<Card> deck;
    private int currentPlayer;
    private int stage; // 0 preflop, 1 flop, 2 turn, 3 river
    
    public GameState(ArrayList<Player> p, Table t, Stack<Card> d, int cp, int s) {
        this.players = p;
        this.table = t;
        this.deck = d;
        this.currentPlayer = cp;
        this.stage = s;
    }

    public void applyMove(String move, int raiseAmount) {
        Player p = players.get(currentPlayer);
        if (move.equals("Fold") || move.equals("fold")) {
            p.fold();
        }
        else if (move.equals("Call") || move.equals("call")) {
            p.bet(20);
        }
        else if (move.equals("Raise") || move.equals("raise")) {
            p.bet(raiseAmount);
        }
        nextPlayer();
    }

    public GameState CloneState() {
        ArrayList<Player> newPlayers = new ArrayList<>();
        for (Player p : players) {
            Player copy = new Player(p.getName(), p.getChips());
            for (Card c: p.gethand()) {
                copy.gethand().add(c);
            }
            if (p.isFolded) {
                copy.fold();
            }
            newPlayers.add(copy);
        }
        
        Stack<Card> newDeck = new Stack<>();
        newDeck.addAll(this.deck);

        return new GameState(newPlayers, table.cloneTable(), newDeck, currentPlayer, stage);
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
