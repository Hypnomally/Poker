import java.util.ArrayList;
import java.util.List;

public class PokerNode {
    private GameState state;
    private List<PokerNode> children = new ArrayList<>();
    private int value;
    private String moveApplied;

    public PokerNode(GameState state) {
        this.state = state;
    }

    public void addChild(PokerNode child) {
        children.add(child);
    }

    public GameState getState() {
        return state;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int v) {
        this.value = v;
    }

    public String getMoveApplied() {
        return moveApplied;
    }

    public void setMoveApplied(String m) {
        this.moveApplied = m;
    }

    public List<PokerNode> getChildren() {
        return children;
    }
}
