public class FakeRandom extends RandomDice {
    private final int[] sequence;
    private int index;

    public FakeRandom(int sides, int... sequence) {
        super(sides);
        this.sequence = sequence;
        this.index = 0;
    }

    @Override
    public int roll() {
        int value = sequence[index % sequence.length];
        index++;
        return value;
    }
}