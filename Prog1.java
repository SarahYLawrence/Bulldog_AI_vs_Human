import java.util.ArrayList;
import java.util.Scanner;

public class Prog1 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the number of players: ");
        int numPlayers = scanner.nextInt();
        scanner.nextLine();  // Consume the newline character

        ArrayList<Player> players = new ArrayList<>();
        for (int i = 0; i < numPlayers; i++) {
            System.out.print("Enter name for player " + (i + 1) + ": ");
            String name = scanner.nextLine();

            System.out.print("Choose player type (HumanPlayer, RandomPlayer, FifteenPlayer, UniquePlayer, WimpPlayer, UniquePlayerOG): ");
            String playerType = scanner.nextLine();

            switch (playerType) {
                case "HumanPlayer":
                    players.add(new HumanPlayer(name));
                    break;
                case "RandomPlayer":
                    players.add(new RandomPlayer(name));
                    break;
                case "FifteenPlayer":
                    players.add(new FifteenPlayer(name));
                    break;
                case "UniquePlayer":
                    players.add(new UniquePlayer(name));
                    break;
                case "WimpPlayer":
                    players.add(new WimpPlayer(name));
                    break;
                case "UniquePlayerOG":
                    players.add(new UniquePlayerOG(name));
                    break;
                default:
                    System.out.println("Invalid player type! Defaulting to HumanPlayer.");
                    players.add(new HumanPlayer(name));
            }
        }

        // Start the game
        int targetScore = 104;
        boolean gameWon = false;
        while (!gameWon) {
            for (Player player : players) {
                System.out.println("\n" + player.getName() + "'s turn:");
                int turnScore = player.play();
                player.setScore(player.getScore() + turnScore);
                System.out.println(player.getName() + "'s total score: " + player.getScore());

                if (player.getScore() >= targetScore) {
                    System.out.println(player.getName() + " wins the game with " + player.getScore() + " points!");
                    gameWon = true;
                    break;
                }
            }
        }

        scanner.close();
    }
}