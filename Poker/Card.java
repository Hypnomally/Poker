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
        if ( s.equals("♤") ){ return hold = 1;}
        if ( s.equals("♥") ){ return hold = 2;}
        if ( s.equals("♢") ){ return hold = 3;}
        if ( s.equals("♧") ){ return hold = 4;}
        if ( s.equals("2") ) { return hold = 2;}
        if ( s.equals("3") ) { return hold = 3;}
        if ( s.equals("4") ) { return hold = 4;}
        if ( s.equals("5") ) { return hold = 5;}
        if ( s.equals("6") ) { return hold = 6;}
        if ( s.equals("7") ) { return hold = 7;}
        if ( s.equals("8") ) { return hold = 8;}
        if ( s.equals("9") ) { return hold = 9;}
        if ( s.equals("10") ) { return hold = 10;}
        if ( s.equals("J") ) { return hold = 11;}
        if ( s.equals("Q") ) { return hold = 12;}
        if ( s.equals("K") ) { return hold = 13;}
        if ( s.equals("A") ) { return hold = 14;}
    }

    public static void main(String[] args) {
        
    }

    public int 
}
