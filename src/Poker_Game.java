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

    public void startTourney(){
        Scanner input = new Scanner(System.in);
        boolean keepPlaying = true;

        while (keepPlaying){
            playRound();

            //check money amount
            if(players.get(0).getChips() < 0) {
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
    }

    private void prepareNextRound(){
        this.deck = new Deck();
        this.deck.shuffle ();

        for (Player p: players) {
            p.clearHand();
        }

        this.table = new Table();
        this.state = new GameState(players, table, deck.getDeckStack(), 0, 0, 0);

    }

    public void playRound(){
        Scanner input = new Scanner(System.in);
        PokerBot bot = new PokerBot();
        deck.shuffle();
        Stack<Card> gameDeck = deck.getDeckStack();

        //Deal 2 cards to all of the players
        for(Player p: players){
            p.addCard(gameDeck.pop());
            p.addCard(gameDeck.pop());
        }

        //Preliminary bets
        System.out.println("Your cards: " + players.get(0).gethand());
        System.out.println("Your cards (fold/call/raise): ");
        String userMove = input.nextLine();
        state.applyMove(userMove, 50);

        //Bot's turn
        System.out.println("Bot is thinking...");
        String botAction = bot.getBestMove(state);
        state.applyMove(botAction, 50);
        System.out.println("Bot chose to: " + botAction);

        //Deal flop and display
        table.dealFlop(gameDeck);
        table.displayTable();

        //
        int userScore = new HandStrength().calculateTotalStrength(players.get(0).gethand(), table.getTableCards());
        int botScore = new HandStrength().calculateTotalStrength(players.get(1).gethand(), table.getTableCards());

        int finalPot = table.getPot();

        if (userScore > botScore){
            System.out.println("You win the pot of $" + finalPot);
            players.get(0).winPot(finalPot);
        } else if (botScore > userScore) {
            System.out.println("Bot wins the pot of $" + finalPot);
            players.get(1).winPot(finalPot);
        } else {
            System.out.println("It's a tie! Pot is split.");
            players.get(0).winPot(finalPot/2);
            players.get(1).winPot(finalPot/2);
        }
    }

}