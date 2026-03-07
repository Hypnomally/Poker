import java.util.ArrayList;

public class App {
    public static void main(String[] args) {
        // 1. Initialize Players
        Player user = new Player("You", 1000);
        Player botPlayer = new Player("PokerBot", 1000);

        ArrayList<Player> players = new ArrayList<>();
        players.add(user);
        players.add(botPlayer);

        // 2. Initialize the Game Engine
        Poker_Game game = new Poker_Game(user, players);

        // 3. Start the Game
        System.out.println("Welcome to the Java Poker Tournament!");
        System.out.println("Starting Chips: $1000 each.");
        System.out.println("------------------------------------");
        
        game.startTourney();
    }
}