import java.util.Random;

public class Dice {
    // Static Random object to ensure only one random number generator
    private static final Random random = new Random();
    
    private int sides; // Number of sides of the dice
    
    // Constructor to initialize the dice with N sides
    public Dice(int sides) {
        this.sides = sides;
    }
    
    // Method to roll the dice and get a random number from 1 to N
    public int roll() {
        return random.nextInt(sides) + 1; // Generates a number between 1 and N (inclusive)
    }
}
