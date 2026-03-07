import java.util.ArrayList;

public class Player{
    private String name;
    private int chips;
    private ArrayList<Card> hand  = new ArrayList<>();
    public boolean isFolded = false;
    private int currentBet = 0;

    public Player(String name, int chips) {
        this.name = name;
        this.chips = chips;
    }

    public void addCard(Card card){
        hand.add(card);
    }

    public void bet(int amount){
        if (amount < chips) {
            this.chips -= amount;
            this.currentBet += amount;
        }
    }

    public void fold(){
        this.isFolded = true;
    }

    public void clearHand(){
        hand.clear();
        isFolded = false;
        currentBet = 0;
    }

    public ArrayList<Card> gethand() { return hand;}
    public int getChips() { return chips;}
    public String getName() { return name;}
}
