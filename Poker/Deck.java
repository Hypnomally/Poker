package Poker;
import java.util.ArrayList;
import java.util.Random;

class Deck {

    ArrayList<String> deck = new ArrayList<String>();
  
    public Deck() {
        public static String[] ranks = {"A", "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K"};
        public static String[] suits = {"♤", "♥", "♢", "♧"};
        generateDeck(ranks, suits);
    }

    public static ArrayList<String> generateDeck(String[] ranks, String[] suits) {
        ArrayList<String> d = new ArrayList<String>();
        for (String r : ranks) {
            for (String s : suits) {
                d.add(r + s);
            }
        }
        System.out.println(d);
        return d;
    }

    public void shuffle() {
        Random random = new Random();
        ArrayList<String> e = new ArrayList<String>();
        int size = this.size();
        for (int i = 0; i < size; i++) {
            int a = random.nextInt(0,d.size());
            e.add(d.get(a));
            deck.remove(a);
        }
        d = e;
    }

    public void dealCards(){
        for (int i = 0; i < 2; i++) {
            for (Player p : players) {
                p.hand[i] = new Card(this);
            }
        }
    }
}
