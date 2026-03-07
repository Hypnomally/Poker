import java.util.ArrayList;

public class Player {
    // properties, it has a name, chip amount, a hand which is an array of cards
    // a fold status, and a current amount bet
    private String name;
    private int chips;
    private ArrayList<Card> hand = new ArrayList<>();
    public boolean isFolded = false;
    private int currentBet = 0;

    public Player(String name, int chips) { // main constructor
        this.name = name;
        this.chips = chips;
    }

    public void addCard(Card card) {
        hand.add(card);
    }

    public void bet(int amount) { // allowing the player to actually bet chips
        if (amount <= chips) {
            this.chips -= amount;
            this.currentBet += amount;
        }
    }

    public void fold() { // allows plater to fold
        this.isFolded = true;
    }

    public int getCurrentBet() {
        return currentBet;
    }

    public void payBlind(int amount) {
        this.chips -= amount;
        this.currentBet += amount;
    }

    public void clearHand() { // clears the player hand
        hand.clear();
        isFolded = false;
        currentBet = 0;
    }

    public void winPot(int amount) {
        this.chips += amount;
        System.out.println(this.name + " won $" + amount + "!");
    }

    // get methods
    public ArrayList<Card> gethand() {
        return hand;
    }

    public int getChips() {
        return chips;
    }

    public String getName() {
        return name;
    }
}
