public class RandomPlayer extends Player {
    public RandomPlayer(String name) {
        super(name);
    }

    @Override
    public int play() {
        int turnScore = 0;
        boolean continueTurn = Math.random() > 0.5;

        while (continueTurn) {
            int roll = (int) (Math.random() * 6) + 1;
            System.out.println("You rolled: " + roll);

            if (roll == 6) {
                System.out.println("You rolled a 6! No points for this turn.");
                return 0;
            }

            turnScore += roll;
            System.out.println("Your current turn score: " + turnScore);

            continueTurn = Math.random() > 0.5;
        }

        return turnScore;
    }
}
