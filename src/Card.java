
import java.util.Random;

class Card {
    public int rank;
    public int suit;
    
    public Card(String s) {
        rank = s.convertRank(s.split(":")[0]);
        suit = s.convertSuit(s.split(":")[1]);
    }

    public int convertSuit(String s){
        if ( s.equals("♠") ){ return 1;}
        if ( s.equals("♥") ){ return 2;}
        if ( s.equals("♦") ){ return 3;}
        else {return 4;}
    }

    public int convertRank(String s){
        for (int i = 0; i < deck.size() - 1; i++ ){
            if (s + ":" == d.getRank(i)){
                return i + 2;
            }
        }
    }

}
