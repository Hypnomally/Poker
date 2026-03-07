import java.util.ArrayList;

public class HandStrength {
    public static final int WEIGHT_FLUSH = 600;
    public static final int WEIGHT_STRAIGHT = 500;
    public static final int WEIGHT_PAIR = 200;

    public int calculateTotalStrength(ArrayList<Card> hole, Card[] tableCards){
        ArrayList<Card> all = new ArrayList<>(hole);
        for (Card c : tableCards) if (c != null) all.add(c);

        int score = 0;

        if (isFlush(all)) score += WEIGHT_FLUSH;
        if (isStraight(all)) score += WEIGHT_STRAIGHT;
        score += (countPairs(all) * WEIGHT_PAIR);

        all.sort((a,b) -> b.getRankNum() - a.getRankNum());
        if (!all.isEmpty()) score += all.get(0).getRankNum();

        return score;
    }

    private int isFlush(ArrayList<Card> cards){
        int[] suitCounts = new int[5]; //the indexes 1-4 represent the suits
        int max = 0;
        for (Card c : cards) { //iterate through cards
            int suit = c.getSuitNum();
            suitCounts[suit]++; //we add 1 to the index that correlates to the suit of the card in hand
            //basically counting the number of each suit that appears
        }

        for (int i=1; i<=4; i++){
            if (suitCounts[i] > max){
                max = suitCounts[i];
            }
        }

        return max;
    }

    private int isStraight(ArrayList<Card> cards){
        int[] rankPresent = new int[15];
        for (Card c: cards) {
           rankPresent[c.getRankNum()] = 1;
        }

        int maxSequence = 0;
        for (int i=2; i <=10; i++){
            int currentSequence = countConsecutive(rankPresent, i);
            if(currentSequence > maxSequence){
                maxSequence = currentSequence;
            }
        }
        return maxSequence;
    }

    private int countConsecutive(int[] ranks, int currentRank){
        if (currentRank > 14 || ranks[currentRank] == 0){
            return 0;
        }

        return 1 + countConsecutive(ranks, currentRank + 1);
    }

    private int countPairs(ArrayList<Card> cards){
        
    }
    
}
