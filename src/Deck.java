
import java.util.ArrayList;
import java.util.Random;
import java.util.Stack;

class Deck { // creating deck class

    ArrayList<String> deck = new ArrayList<String>(); // creating an array for the deck with strings to be converted to
                                                      // cards
    public static String[] ranks = { "2:", "3:", "4:", "5:", "6:", "7:", "8:", "9:", "10:", "J:", "Q:", "K:", "A:" };
    public static String[] suits = { "♠", "♥", "♦", "♣" };

    public Deck() { // constructor
        deck = generateDeck();
    }

    public static int rankSize() {
        return ranks.length;
    }

    public static String rankGet(int i) {
        return ranks[i];
    }

    public static ArrayList<String> generateDeck() {
        ArrayList<String> d = new ArrayList<String>();
        for (String r : ranks) { // iterating through ranks
            for (String s : suits) { // iterating through suits
                d.add(r + s); // creating card strings by adding each rank with each suit
            }
        }
        return d;
    }

    // shuffle method, choosing an int randomly from the array, then moving it into
    // a new array
    // keep doing this, then return the new randomized array as our deck
    public void shuffle() {
        Random random = new Random();
        ArrayList<String> e = new ArrayList<String>();
        int size = this.deck.size();
        for (int i = 0; i < size; i++) {
            int a = random.nextInt(0, this.deck.size());
            e.add(this.deck.get(a));
            this.deck.remove(a);
        }
        this.deck = e;
    }

    // turning the deck from arraylist to stack for easier handling
    public Stack<Card> getDeckStack() {
        Stack<Card> deckStack = new Stack<>();
        for (String s : this.deck) {
            deckStack.push(new Card(s));
        }
        return deckStack;
    }

    // method for drawing cards
    public Card draw() {
        String c = this.deck.remove(0);
        return new Card(c);
    }

    // public void dealCards(){
    // for (int i = 0; i < 2; i++) {
    // for (Player p : players) {
    // p.hand[i] = new Card(this);
    // }
    // }
    // }
}
