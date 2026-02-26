import java.util.Random;

class Card extends Deck {
    public int rank;
    public int suit;
    
    
    
    public Card(Deck deck) {
        Random random = new Random();
        rank = random.nextInt(1,14);
        suit = random.nextInt(1,5);
    }
    public static void main(String[] args) {
        
    }

    public int 
}
