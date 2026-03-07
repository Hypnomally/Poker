class Card {
    private int rankNum;
    private int suitNum;

    public Card(String s) { // splitting the string into the rank and suit to create these properties for
                            // the card
        rankNum = convertRank(s.split(":")[0]);
        suitNum = convertSuit(s.split(":")[1]);
    }

    public static int convertSuit(String s) { // converts the suits into ints
        if (s.equals("S")) {
            return 1;
        }
        if (s.equals("H")) {
            return 2;
        }
        if (s.equals("D")) {
            return 3;
        } else {
            return 4;
        }
    }

    public int convertRank(String s) { // converts the ranks into ints using it's index in the deck array
        for (int i = 0; i < Deck.rankSize(); i++) { // therefore we have to convert Ranks and suits before turning the
                                                    // deck into a Stack
            if ((s + ":").equals(Deck.rankGet(i))) {
                return i + 2;
            }
        }
        return -1;
    }

    public int getRankNum() { // return rankNum
        return rankNum;
    }

    public int getSuitNum() { // return suitNum
        return suitNum;
    }

    @Override
    public String toString() {
        // This finds the rank string (e.g., "A:") and suit (e.g., "♠")
        String r = Deck.ranks[rankNum - 2];
        String s = Deck.suits[suitNum - 1];
        return r + s;
    }
}
