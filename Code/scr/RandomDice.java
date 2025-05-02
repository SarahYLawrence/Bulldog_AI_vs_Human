/**
 * An abstract class representing a dice with a specified number of sides.
 * <p>
 * This class defines the basic structure for a dice that can be rolled, with the number
 * of sides being specified in the constructor. Subclasses must implement the {@code roll()} method
 * to define how the dice should generate a random result.
 */
public abstract class RandomDice {
    /** The number of sides on the dice. */
    protected final int sides;

    /**
     * Constructs a {@code RandomDice} with a specified number of sides.
     *
     * @param sides the number of sides on the dice
     */
    public RandomDice(int sides) {
        this.sides = sides;
    }

    /**
     * Rolls the dice and returns a result.
     * <p>
     * This method must be implemented by subclasses to define how the dice are rolled
     * and how the result is determined.
     *
     * @return the result of the dice roll, typically a random integer between 1 and {@code sides}
     */
    public abstract int roll();
}
