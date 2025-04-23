public abstract class RandomDice {
    protected final int sides;

    public RandomDice(int sides) {
        this.sides = sides;
    }

    public abstract int roll();
}