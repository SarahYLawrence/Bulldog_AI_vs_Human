import java.util.Scanner;

public class HumanPlayer extends Player {

    public HumanPlayer(String name) {
        super(name);
    }

    @Override
    public int play() {
        int turnScore = 0;
        Scanner scanner = new Scanner(System.in);
        while (true) {
            int roll = (int) (Math.random() * 6) + 1;
            System.out.println(getName() + " rolled: " + roll);

            if (roll == 6) {
                System.out.println("Rolled a 6! Turn over with 0 points.");
                return 0; // turn ends with no score
            }

            turnScore += roll;
            System.out.println("Current turn score: " + turnScore);
            System.out.print("Do you want to roll again? (y/n): ");
            String response = scanner.nextLine();

            if (response.equalsIgnoreCase("n")) {
                System.out.println(getName() + " ends turn with score: " + turnScore);
                return turnScore;
            }
        }
    }
}