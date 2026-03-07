import java.util.ArrayList;
import java.util.Scanner;
import java.util.Stack;

public class Poker_Game {
    public Player U;

    public ArrayList<Player> players;
    public Deck deck;
    public Table table;
    public GameState state;

    public Poker_Game(Player user, ArrayList<Player> players) {
        this.U = user;
        this.players = players;
        this.deck = new Deck();
        this.table = new Table();
        this.state = new GameState(players, table, deck.getDeckStack(), 0, 0, 0);
    }

    public void startTourney() {
        Scanner input = new Scanner(System.in);
        boolean keepPlaying = true;

        while (keepPlaying) {
            playRound(input);

            // check money amount
            if (players.get(0).getChips() <= 0) {
                System.out.println("You are bankrupt! Game Over.");
                break;
            } else if (players.get(1).getChips() <= 0) {
                System.out.println("The Bot is bankrupt! You win!");
                break;
            }

            System.out.print("Play another round? (y/n: ");
            String choice = input.nextLine();
            if (choice.equalsIgnoreCase("n")) {
                keepPlaying = false;
            } else {
                prepareNextRound();
            }
        }
        input.close();
    }

    private void prepareNextRound() {
        this.deck = new Deck();
        this.deck.shuffle();

        for (Player p : players) {
            p.clearHand();
        }

        this.table = new Table();
        this.state = new GameState(players, table, deck.getDeckStack(), 0, 0, 0);

    }

    public void playRound(Scanner input) {
        PokerBot bot = new PokerBot();
        deck.shuffle();
        Stack<Card> gameDeck = deck.getDeckStack();

        System.out.println("Blinds");
        players.get(0).payBlind(10); // User Small Blind
        players.get(1).payBlind(20); // Bot Big Blind

        table.addPot(30); // Add the $30 to the table pot
        // Also update the state's internal pot
        state.addPot(30);

        // Deal 2 cards to all of the players
        for (Player p : players) {
            p.addCard(gameDeck.pop());
            p.addCard(gameDeck.pop());
        }

        handleBetting("Pre-Flop", input, bot);

        // flop 1, 3 cards
        table.dealFlop(gameDeck);
        table.displayTable();
        handleBetting("Flop", input, bot);

        // first card turn
        table.dealTurn(gameDeck);
        table.displayTable();
        handleBetting("Turn", input, bot);

        // last card turn
        table.dealRiver(gameDeck);
        table.displayTable();
        handleBetting("River", input, bot);

        determineWinner();

    }

    private void handleBetting(String phaseName, Scanner input, PokerBot bot) {
        // user turn
        System.out.println("Your cards: " + players.get(0).gethand());
        System.out.print("Your cards (fold/call/raise): ");
        String userMove = input.nextLine();
        state.applyMove(userMove, 50);

        // bot turn
        if (!players.get(0).isFolded) {
            System.out.println("Bot is thinking...");
            String botAction = bot.getBestMove(state);
            state.applyMove(botAction, 50);
            System.out.println("Bot chose to: " + botAction);
        }
    }

    private void determineWinner() {

        if (players.get(0).isFolded) {
            System.out.println("You folded. Bot wins the pot!");
            players.get(1).winPot(table.getPot());
            return;
        }

        if (players.get(1).isFolded) {
            System.out.println("Bot folded. You win the pot!");
            players.get(0).winPot(table.getPot());
            return;
        }

        HandStrength hs = new HandStrength();
        int userScore = hs.calculateTotalStrength(players.get(0).gethand(), table.getTableCards());
        int botScore = hs.calculateTotalStrength(players.get(1).gethand(), table.getTableCards());

        int finalPot = table.getPot();

        System.out.println("You have " + hs.getHandName(userScore));
        System.out.println("Bot has " + hs.getHandName(botScore));

        if (userScore > botScore) {
            System.out.println("You win the pot of $" + finalPot);
            players.get(0).winPot(finalPot);
        } else if (botScore > userScore) {
            System.out.println("Bot wins the pot of $" + finalPot);
            players.get(1).winPot(finalPot);
        } else {
            System.out.println("It's a tie! Pot is split.");
            players.get(0).winPot(finalPot / 2);
            players.get(1).winPot(finalPot / 2);
        }
    }

}