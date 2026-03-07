import java.util.ArrayList;

public class Poker_Game {
    public Player U;

    public ArrayList<Player> players;
    public Deck deck;
    public Table table;
    public GameState state;

    public Poker_Game(Player user, ArrayList<Player> players) {
        this.U = user;
        this.players = players;
        this.deck = new Deck();
        this.table = new Table();
        this.state = new GameState(players, table, deck.getDeckStack(), 0, 0);
    }

    // add methods for startGame, dealing, betting, etc.
}