public class RandomPlayer extends Player {

    public RandomPlayer(String name) {
        super(name);
    }

    @Override
    public int play() {
        int turnScore = 0;
        while (true) {
            int roll = (int) (Math.random() * 6) + 1;
            System.out.println(getName() + " rolled: " + roll);

            if (roll == 6) {
                System.out.println("Rolled a 6! Turn over with 0 points.");
                return 0; // turn ends with no score
            }

            turnScore += roll;
            System.out.println("Current turn score: " + turnScore);

            // 50% chance to continue or end turn
            if (Math.random() < 0.5) {
                System.out.println(getName() + " decides to stop.");
                return turnScore;
            }
        }
    }
}