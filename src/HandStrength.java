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

    private boolean isFlush(ArrayList<Card> cards){

    }

    private boolean isStraight(ArrayList<Card> cards){
        
    }

    private boolean countPairs(ArrayList<Card> cards){
        
    }
    
}
