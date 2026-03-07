import java.util.ArrayList;

public class PokerBot {
    private HandStrength evaluator = new HandStrength();

    Public String getBestMove(GameState currentState){
        //Create root of our tree
        PokerNode root = new PokerNode(currentState);

        buildTree(root,2);

        return selectBestAction(root;)
    }

    private void buildTree(PokerNode node, int depth){
        if (depth == 0) return;

        String[] possibleMoves = {"fold", "call", "raise"};

        for (String move : possibleMoves) {
            //cloning gamestate and setting variable
            GameState simulatedState = node.getState().CloneState();
            //apply move to cloned state
            simulatedState.applyMove(move,50);

            PokerNode child = new PokerNode(simulatedState);
            child.seetMoveApplied(move);

            int score = evaluator.calculateTotalStrength(
                simulatedState.getPlayers().get(1).gethand(), 
                simulatedState.getTable().getTableCards());

            child.setValue(score);

            node.addChild(child);

            buildTree(child, depth - 1);
        }
    }
}
