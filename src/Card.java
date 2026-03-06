
import java.util.Random;

class Card {
    private int rankNum;
    private int suitNum;
    
    public Card(String s) {
        rankNum = convertRank(s.split(":")[0]);
        suitNum = convertSuit(s.split(":")[1]);
    }

    public static int convertSuit(String s){
        if ( s.equals("♠") ){ return 1;}
        if ( s.equals("♥") ){ return 2;}
        if ( s.equals("♦") ){ return 3;}
        else {return 4;}
    }

    public int convertRank(String s){
        for (int i = 0; i < Deck.rankSize() - 1; i++ ){
            if ((s + ":").equals(Deck.rankGet(i))){
                return i + 2;
            }
        }
        return -1;
    }

}
