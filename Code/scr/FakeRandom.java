/**
 * A deterministic dice roller that returns a predefined sequence of values.
 * <p>
 * This class is useful for testing purposes where predictable outcomes are needed.
 * It extends {@code RandomDice} and overrides the {@code roll()} method to return
 * values from a specified sequence instead of generating random numbers.
 */
public class FakeRandom extends RandomDice {
    /** The predefined sequence of values to return. */
    private final int[] sequence;

    /** The current index into the sequence. */
    private int index;

    /**
     * Constructs a {@code FakeRandom} dice with a given number of sides and a sequence of values.
     *
     * @param sides    the number of sides on the dice (passed to the superclass)
     * @param sequence the fixed sequence of values to return on each call to {@code roll()}
     */
    public FakeRandom(int sides, int... sequence) {
        super(sides);
        this.sequence = sequence;
        this.index = 0;
    }

    /**
     * Returns the next value in the predefined sequence, cycling back to the start if necessary.
     *
     * @return the next integer from the sequence
     */
    @Override
    public int roll() {
        int value = sequence[index % sequence.length];
        index++;
        return value;
    }
}
