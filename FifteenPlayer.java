public class FifteenPlayer extends Player {
    public FifteenPlayer(String name) {
        super(name);
    }

    @Override
    public int play() {
        int turnScore = 0;

        while (turnScore < 15) {
            int roll = (int) (Math.random() * 6) + 1;
            System.out.println("You rolled: " + roll);

            if (roll == 6) {
                System.out.println("You rolled a 6! No points for this turn.");
                return 0;
            }

            turnScore += roll;
            System.out.println("Your current turn score: " + turnScore);
        }

        return turnScore;
    }
}
