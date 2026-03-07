public class PokerBot {
    private HandStrength evaluator = new HandStrength();

    public String getBestMove(GameState currentState) {
        // Create root of our tree
        PokerNode root = new PokerNode(currentState);

        buildTree(root, 2);

        return selectBestAction(root);
    }

    private String selectBestAction(PokerNode root) {
        String bestMove = "fold";
        int maxScore = -9999;

        for (PokerNode child : root.getChildren()) {
            if (child.getValue() > maxScore) {
                maxScore = child.getValue();
                bestMove = child.getMoveApplied();
            }
        }

        if (maxScore < 15)
            return "fold";

        return bestMove;
    }

    private void buildTree(PokerNode node, int depth) {
        if (depth == 0)
            return;

        String[] possibleMoves = { "fold", "call", "raise" };

        for (String move : possibleMoves) {
            // cloning gamestate and setting variable
            GameState simulatedState = node.getState().CloneState();
            // apply move to cloned state
            simulatedState.applyMove(move, 50);

            PokerNode child = new PokerNode(simulatedState);
            child.setMoveApplied(move);

            int score = evaluator.calculateTotalStrength(
                    simulatedState.getPlayers().get(1).gethand(),
                    simulatedState.getTable().getTableCards());

            child.setValue(score);

            node.addChild(child);

            buildTree(child, depth - 1);
        }
    }
}
