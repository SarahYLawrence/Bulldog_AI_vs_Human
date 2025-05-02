import java.util.Random;

/**
 * The {@code Dice} class represents a die with a specified number of sides.
 * It extends {@code RandomDice} and uses a singleton {@code Random} instance
 * to generate random rolls.
 * <p>
 * Each call to {@link #roll()} returns a pseudo-random integer between 1 and the number of sides.
 * </p>
 */
public class Dice extends RandomDice {

    /** A shared {@code Random} instance used for generating rolls. */
    private static final Random random = new Random();

    /**
     * Constructs a {@code Dice} object with the specified number of sides.
     *
     * @param sides the number of sides on the die
     */
    public Dice(int sides) {
        super(sides); // Call constructor of RandomDice
    }

    /**
     * Rolls the die and returns a pseudo-random result.
     *
     * @return a random integer between 1 and the number of sides (inclusive)
     */
    @Override
    public int roll() {
        return random.nextInt(sides) + 1;
    }
}
