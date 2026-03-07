import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class HandStrength {
    public static final int WEIGHT_FLUSH = 600;
    public static final int WEIGHT_STRAIGHT = 500;
    public static final int WEIGHT_PAIR = 200;

    public int calculateTotalStrength(ArrayList<Card> hole, Card[] tableCards){
        ArrayList<Card> all = new ArrayList<>(hole);
        for (Card c : tableCards) {
            if (c != null) {
                all.add(c);
            }
        }

        int score = 0;

        if (isFlush(all)) score += WEIGHT_FLUSH;
        if (isStraight(all)) score += WEIGHT_STRAIGHT;
        score += (countPairs(all) * WEIGHT_PAIR);

        all.sort((a,b) -> b.getRankNum() - a.getRankNum());
        if (!all.isEmpty()) score += all.get(0).getRankNum();

        return score;
    }

    private boolean isFlush(ArrayList<Card> cards){
        int[] suits = new int[5];
        for (Card c : cards) {
            suits[c.getSuitNum()]++;
            if (suits[c.getSuitNum()] >= 5) {
                return true;
            }
        }
        return false;
    }

    private boolean isStraight(ArrayList<Card> cards){
        ArrayList<Integer> ranks = new ArrayList<>();
        for (Card c : cards) {
            int r = c.getRankNum();
            if (!ranks.contains(r)) {
                ranks.add(r);
            }
        }
        Collections.sort(ranks);
        
        if (ranks.contains(14)) {
            ranks.add(1);
        }

        int consecutive = 1;
        for (int i = 1; i < ranks.size(); i++) {
            if (ranks.get(i) == ranks.get(i-1) + 1) {
                consecutive++;
                if (consecutive >= 5) {
                    return true;
                }
            } 
            else if (ranks.get(i) != ranks.get(i-1)) {
                consecutive = 1;
            }
        }
        return false;
    }

    private int countPairs(ArrayList<Card> cards){
        HashMap<Integer, Integer> counts = new HashMap<>();
        for (Card c : cards) {
            counts.put(c.getRankNum(), counts.getOrDefault(c.getRankNum(),0)+1);
        }
        int pairScore = 0;
        for (int val : counts.values()) {
            if (val == 2) pairScore += 1;
            if (val == 3) pairScore += 3;
            if (val == 4) pairScore += 5;
        }
        return pairScore;
    }
}
