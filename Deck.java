import java.util.ArrayList;

class Deck {

    public static void main(String[] args) {
        generateDeck();
    }
    ArrayList<String> deck = new ArrayList<String>();
  
    public Deck() {
        String[] ranks = {"A", "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K"};
        String[] suits = {"♤", "♥", "♢", "♧"};

    }

    public static ArrayList<String> generateDeck() {
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
    }
}
