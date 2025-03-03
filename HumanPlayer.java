import java.util.Scanner;

public class HumanPlayer extends Player {
    public HumanPlayer(String name) {
        super(name);
    }

    @Override
    public int play() {
        Scanner scanner = new Scanner(System.in);
        int turnScore = 0;

        while (true) {
            int roll = (int) (Math.random() * 6) + 1;
            System.out.println("You rolled: " + roll);

            if (roll == 6) {
                System.out.println("You rolled a 6! No points for this turn.");
                return 0;
            }

            turnScore += roll;
            System.out.println("Your current turn score: " + turnScore);

            System.out.print("Do you want to continue? (y/n): ");
            String response = scanner.nextLine();
            if (response.equalsIgnoreCase("n")) {
                break;
            }
        }
        return turnScore;
    }
}
