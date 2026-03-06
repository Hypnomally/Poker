import java.util.ArrayList;

public class Player{
    private String name;
    private int chips;
    private ArrayList<Card> hand  = new ArrayList<>();
    public boolean isFolded = false;

    public Player(String name, int chips) {
        this.name = name;
        this.chips = chips;
    }
}
