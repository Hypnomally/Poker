package Poker;
import java.util.ArrayList;
import java.util.Random;

class Deck {

    ArrayList<String> deck = new ArrayList<String>();
    public static String[] ranks = {"2:", "3:", "4:", "5:", "6:", "7:", "8:", "9:", "10:", "J:", "Q:", "K:", "A:"};
    public static String[] suits = {"♤", "♥", "♢", "♧"};
  
    public Deck() {
        deck = generateDeck();
    }

    public static void main(String[] args) {
        Deck test = new Deck();
        test.shuffle();
        System.out.println(test.deck);
    }

    public static ArrayList<String> generateDeck() {
        ArrayList<String> d = new ArrayList<String>();
        for (String r : ranks) {
            for (String s : suits) {
                d.add(r + s);
            }
        }
        return d;
    }

    public void shuffle() {
        Random random = new Random();
        ArrayList<String> e = new ArrayList<String>();
        int size = this.deck.size();
        for (int i = 0; i < size; i++) {
            int a = random.nextInt(0,this.deck.size());
            e.add(this.deck.get(a));
            this.deck.remove(a);
        }
        this.deck = e;
    }

    // public void draw() {
    //     String c = this.deck.get(0);
    //     this.deck.remove(c);
    //     return Card(c);
    // }

    // public void dealCards(){
    //     for (int i = 0; i < 2; i++) {
    //         for (Player p : players) {
    //             p.hand[i] = new Card(this);
    //         }
    //     }
    // }
}
