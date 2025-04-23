import java.util.Random;

public class Dice extends RandomDice {
    private static final Random random = new Random(); // Singleton Random instance

    public Dice(int sides) {
        super(sides); // Call constructor of RandomDice
    }

    @Override
    public int roll() {
        return random.nextInt(sides) + 1; // Random number between 1 and sides
    }
}