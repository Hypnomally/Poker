import java.util.ArrayList;

public class HandStrength {
    public static final int WEIGHT_FLUSH = 600;
    public static final int WEIGHT_STRAIGHT = 500;
    public static final int WEIGHT_PAIR = 200;

    public int calculateTotalStrength(ArrayList<Card> hole, Card[] tableCards){
        ArrayList<Card> all = new ArrayList<>(hole);
        for (Card c : tableCards) if (c != null) all.add(c);

        int score = 0;
        // checking flush potential
        int suitCount = getMaxSuitCount(all);
        if (suitCount >= 5){
            score += WEIGHT_FLUSH;
        } else if (suitCount == 4) {
            score += 150; //flush draw potential
        }
        // checking  straight potentials
        int straightCount = getMaxStraightCount(all);
        if (straightCount >=5){
            score += WEIGHT_STRAIGHT;
        } else if (straightCount == 4){
            score += 100;
        }

        score += countPairs(all);

        int highCard = 0;
        for (Card c : all) {
            if (c.getRankNum() > highCard){
                highCard = c.getRankNum();
            }
        }
        score += highCard;
        return score;
    }

    private int getMaxSuitCount(ArrayList<Card> cards){
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

    private int getMaxStraightCount(ArrayList<Card> cards){
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
        int[] rankCounts = new int[15];
        int totalScore = 0;

        for (Card c : cards){
            rankCounts[c.getRankNum()]++;
        }

        for(int i = 2; i <= 14; i++){
            if (rankCounts[i] == 4){
                totalScore += 800; //four of a kind
            } else if (rankCounts[i] == 3){ // three of a kind
                totalScore += 400;
            } else if(rankCounts[i] == 2){// a pair
                totalScore += 200;
            }
        }

        return totalScore;
    }
    
}
