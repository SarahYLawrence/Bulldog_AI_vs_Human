public class UniquePlayer extends Player {

    public UniquePlayer(String name) {
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

            if (turnScore >= 10 && turnScore <= 20) {
                System.out.println(getName() + " ends turn with score: " + turnScore);
                return turnScore;
            }
        }
    }
}