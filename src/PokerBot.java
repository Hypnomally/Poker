public class PokerBot {
    private HandStrength evaluator = new HandStrength();

    public String getBestMove(GameState currentState) {
        // Create root of our tree
        PokerNode root = new PokerNode(currentState);

        buildTree(root, 4);

        return selectBestAction(root);
    }

    private String selectBestAction(PokerNode root) {
        String bestMove = "call";
        int maxScore = -1;

        for (PokerNode child : root.getChildren()) {
            if (child.getValue() > maxScore) {
                maxScore = child.getValue();
                bestMove = child.getMoveApplied();
            }
        }

        if (maxScore <= 8)
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

            int strength = evaluator.calculateTotalStrength(
                    simulatedState.getPlayers().get(1).gethand(),
                    simulatedState.getTable().getTableCards());

            
            int finalScore = 0;

            if (move.equalsIgnoreCase("fold")) {
                finalScore = 0; 
            } else if (move.equalsIgnoreCase("call")) {
                finalScore = strength; 
            } else if (move.equalsIgnoreCase("raise")) {
                if (strength >= 200) {
                    finalScore = strength + 50; // Encourages raising with good hands
                } else {
                    finalScore = strength - 50; // Discourages raising with bad hands
                }
            }

            child.setValue(finalScore);

            node.addChild(child);

            buildTree(child, depth - 1);
        }
    }
}
