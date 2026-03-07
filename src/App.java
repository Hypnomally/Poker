public class App {
    public static void main(String[] args) {
        Table table = new Table();
        Deck test = new Deck();
        test.shuffle();
        System.out.println(test.deck);
        
    }

    /*public static void main(String[] args) {
        generateDeck();
        dealCards();
    }

    public static void main(String[] args) {
        Player player1 = new Player("Alice", 1000);
        Player player2 = new Player("Bob", 1000);
        Deck deck = new Deck();
        Card card1 = new Card(deck);
        Card card2 = new Card(deck);
        player1.hand[0] = card1;
        player1.hand[1] = card2;
        System.out.println(player1.name + " has " + player1.chips + " chips and is dealt: " + card1.rank + " of suit " + card1.suit + " and " + card2.rank + " of suit " + card2.suit);
    }*/
}
