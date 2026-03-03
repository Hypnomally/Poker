public class Player{
    public String name;
    public int chips;
    public Card[] hand = new Card[2];
    public boolean isFolded = false;

    public Player(String name, int chips) {
        this.name = name;
        this.chips = chips;
    }
}
