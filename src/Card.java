class Card {
    private int rankNum;
    private int suitNum;
    
    public Card(String s) { //splitting the string into the rank and suit to create these properties for the card
        rankNum = convertRank(s.split(":")[0]);
        suitNum = convertSuit(s.split(":")[1]);
    }

    public static int convertSuit(String s){ //converts the suits into ints
        if ( s.equals("♠") ){ return 1;}
        if ( s.equals("♥") ){ return 2;}
        if ( s.equals("♦") ){ return 3;}
        else {return 4;}
    }

    public int convertRank(String s){ //converts the ranks into ints using it's index in the deck array
        for (int i = 0; i < Deck.rankSize(); i++ ){ //therefore we have to convert Ranks and suits before turning the deck into a Stack
            if ((s + ":").equals(Deck.rankGet(i))){
                return i + 2;
            }
        }
        return -1;
    }

    public int getRankNum(){ //return rankNum
        return rankNum;
    }

    public int getSuitNum(){ //return suitNum
        return suitNum;
    }
}
