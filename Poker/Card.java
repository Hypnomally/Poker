package Poker;
import java.util.Random;

class Card {
    public int rank;
    public int suit;
    
    public Card(String s) {
        rank = s.convert(split(regex: ":")[0]);
        suit = s.convert(split(regex: ":")[1]);
    }

    public int convert(String s){
        int hold = 0;
        if ( s.equals("♠") ){ return hold = 1;}
        if ( s.equals("♥") ){ return hold = 2;}
        if ( s.equals("♦") ){ return hold = 3;}
        if ( s.equals("♣") ){ return hold = 4;}
    }

    public static void main(String[] args) {
        
    }

    public int 
}
