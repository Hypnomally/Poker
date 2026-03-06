import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class HandStrength {

    public enum HandRank{
        High_Card(1), Pair(2), Two_Pair(3), Three_of_a_kind(4), Straight(5), Flush(6), Full_House(7), Four_Of_A_Kind(8), Straight_Flush(9), Royal_Flush(10);

        private final int value;
        HandRank(int value) {this.value = value;}
        public int getValue() {return value;}
    }
    
}
