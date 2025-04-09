/**
 * Written by Sarah Lawrence. 
 * With the assistance of ChatGBT 3.5.
 */

/**
 * Main class for the Bulldog game.
 */
import java.util.Scanner;
import java.util.logging.Logger;
import javax.swing.*;

/**
 * The Main class initializes and runs the Bulldog game.
 */
public class Main {
    private static final Logger logger = Logger.getLogger(Main.class.getName());
    private PlayerList players;
    private Scoreboard scoreboard;

    /**
     * Constructs a Main game instance.
     * @param numPlayers The number of players.
     * @param scanner The scanner object for user input.
     */
    public Main(int numPlayers, Scanner scanner) {
        this.players = new PlayerList();
        this.scoreboard = new Scoreboard();
        initializePlayers(numPlayers, scanner);
    }

    /**
     * Initializes the players for the game.
     * @param num The number of players.
     * @param scanner The scanner object for user input.
     */
    private void initializePlayers(int num, Scanner scanner) {
        for (int i = 1; i <= num; i++) {
            logger.info("Player " + i + ", choose player type (type 'Player_Info' for options):");
            boolean validChoice = false;
            while (!validChoice) {
                String choice = scanner.nextLine();
                Player newPlayer = createPlayer(choice, "Player " + i);
                if (newPlayer != null) {
                    players.addPlayer(newPlayer);
                    scoreboard.addPlayer(newPlayer.getName());
                    logger.info("Player " + i + " is a " + choice + " player.");
                    validChoice = true;
                } else if (choice.equals("Player_Info")) {
                    displayPlayerInfo();
                } else {
                    logger.warning("Invalid choice. Try again.");
                }
            }
        }
    }

    /**
     * Creates a player instance based on the specified type.
     * @param type The type of player.
     * @param name The name of the player.
     * @return A new Player instance or null if the type is invalid.
     */
    private Player createPlayer(String type, String name) {
        return switch (type) {
            case "Human" -> new HumanPlayer(name);
            default -> null;
        };
    }

    /**
     * Displays information about the different player types.
     */
    private void displayPlayerInfo() {
        logger.info("""
            Players: (type what's in parentheses to pick)
            (Human): Choose whether to end turn.
            (Fifteen): Rolls until score is at least 15.
            (Random): 50% chance to continue rolling.
            (Unique): Flips coin; heads = high rolls only.
            (Wimp): Ends turn after first roll.
            """);
    }

    /**
     * Starts and manages the Bulldog game.
     */
    public void playGame() {
        logger.info("Starting Bulldog!");
        boolean gameRunning = true;
        int round = 1;

        while (gameRunning) {
            for (Player player : players.getPlayers()) {
                int score = player.play();
                player.setScore(score);
                scoreboard.updateScore(player.getName(), score);

                if (score >= 104) {
                    logger.info("\n" + player.getName() + " wins with a score of " + score + "!");
                    gameRunning = false;
                    break;
                }
            }
            if (gameRunning) {
                logger.info("Round " + round++ + " over!");
            }
        }
    }

    /**
     * The main entry point of the application.
     * @param args Command-line arguments.
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        logger.info("Welcome to Bulldog!");
        
        int numPlayers = 0;
        while (numPlayers <= 0) {
            logger.info("How many players?");
            try {
                numPlayers = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                logger.warning("Invalid number. Try again.");
            }
        }

        Main game = new Main(numPlayers, scanner);
        game.playGame();
        scanner.close();
    }
}

/**
 * Scoreboard class represents the game's scoreboard using Swing.
 */
class Scoreboard extends JFrame {
    private final DefaultListModel<String> scoreModel;
    private final JList<String> scoreList;

    /**
     * Constructs a new Scoreboard window.
     */
    public Scoreboard() {
        setTitle("Bulldog Scoreboard");
        setSize(300, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        scoreModel = new DefaultListModel<>();
        scoreList = new JList<>(scoreModel);
        add(new JScrollPane(scoreList));
        setVisible(true);
    }

    /**
     * Adds a player to the scoreboard.
     * @param playerName The name of the player.
     */
    public void addPlayer(String playerName) {
        scoreModel.addElement(playerName + ": 0");
    }

    /**
     * Updates the score of a player on the scoreboard.
     * @param playerName The name of the player.
     * @param newScore The new score to update.
     */
    public void updateScore(String playerName, int newScore) {
        for (int i = 0; i < scoreModel.size(); i++) {
            if (scoreModel.get(i).startsWith(playerName)) {
                scoreModel.set(i, playerName + ": " + newScore);
                return;
            }
        }
    }
}