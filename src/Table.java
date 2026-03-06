import java.util.ArrayList;
import java.util.Stack;

class Table {
    private Card[] tableCards = new Card[5];
    private int pot = 0;
    private int currentStage = 0; // ): pre-flop, 1: flop, 2: Turn, 3: River

    public void dealFlop(Stack<Card> deck){
        deck.pop(); // Burn
        for (int i = 0; i < 3; i++){ // the original flop of the first 3 cards
            tableCards[i] = deck.pop();
        }
        currentStage = 1;
    }

    public void dealTurn(Stack<Card> deck) {
        deck.pop(); // Burn
        tableCards[3] = deck.pop(); //flipping the second to last card
        currentStage = 2;
    }

    public void dealRiver(Stack<Card> deck) {
        deck.pop(); // Burn
        tableCards[4] = deck.pop(); //flipping the last card
        currentStage = 3;
    }

    public void displayTable(){
        System.out.print("Table: ");
        for (Card c: tableCards){
            if (c != null) System.out.print( c+ " ");
        }
        System.out.println("Pot: $" + pot);
    }
}