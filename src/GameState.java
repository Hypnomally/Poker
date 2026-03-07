import java.util.ArrayList;
import java.util.Stack;
    
public class GameState {
    private ArrayList<Player> players;
    private Table table;
    private Stack<Card> deck;
    private int pot;
    private int currentPlayer;
    private int stage; // 0 preflop, 1 flop, 2 turn, 3 river
    
    public GameState(ArrayList<Player> players, Table table, Stack<Card> deck, int pot, int currentPlayer, int stage) {
        this.players = players;
        this.table = table;
        this.deck = deck;
        this.pot = pot;
        this.currentPlayer = currentPlayer;
        this.stage = stage;
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
    
    public int getPot() {
        return pot;
    }
    
    public int getCurrentPlayer() {
        return currentPlayer;
    }
    
    public int getStage() {
        return stage;
    }
    
    public void setPot(int pot) {
        this.pot = pot;
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
        newDeck.addAll(deck);
        
        Table newTable = table; // simple reference for now
        
        return new GameState(newPlayers, newTable, newDeck, pot, currentPlayer, stage);
    }
}
