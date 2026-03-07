import java.util.ArrayList;
import java.util.Stack;
    
public class GameState {
    private ArrayList<Player> players;
    private Table table;
    private Stack<Card> deck;
    private int currentPlayer;
    private int stage; // 0 preflop, 1 flop, 2 turn, 3 river
    
    public GameState(ArrayList<Player> p, Table t, Stack<Card> d, int currentPlayer, int stage) {
        this.players = p;
        this.table = t;
        this.deck = d;
        this.currentPlayer = currentPlayer;
        this.stage = stage;
    }

    public GameState clone() {
        return new GameState(clonedUser, clonedBot, clonedTable, clonedDeck);
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

    public GameState cloneState() {
        ArrayList<Player> newPlayers = new ArrayList<>();
        
        for (Player p : players) {
            Player copy = new Player(p.getName(), p.getChips());
            
            for (Card c : p.gethand()) {
                copy.gethand().add(c);
            }
            
            if (p.isFolded) {
                copy.fold();
            }
            
            newPlayers.add(copy);
        }
        
        Stack<Card> newDeck = new Stack<>();
        newDeck.addAll(this.deck);
        
        Table newTable = table; // simple reference for now
        
        return new GameState(newPlayers, newTable, newDeck, pot, currentPlayer, stage);
    }
}
