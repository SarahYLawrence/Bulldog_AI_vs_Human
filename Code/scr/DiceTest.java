/**
 * A simple test class to demonstrate the behavior of the {@code Dice} class.
 * <p>
 * This class creates and rolls two dice objects: a 6-sided die (d6) and a 2-sided die (d2),
 * printing the results of 10 rolls for each.
 */
public class DiceTest {

    /**
     * The entry point of the program.
     * <p>
     * This method creates two {@code Dice} objects with different side counts and rolls
     * each of them 10 times, printing the results to the console.
     *
     * @param args command-line arguments (not used)
     */
    public static void main(String[] args) {
        Dice d6 = new Dice(6);
        Dice d2 = new Dice(2);

        System.out.println("Rolling d6 (6-sided) 10 times:");
        for (int i = 0; i < 10; i++) {
            System.out.print(d6.roll() + " ");
        }

        System.out.println("\nRolling d2 (2-sided) 10 times:");
        for (int i = 0; i < 10; i++) {
            System.out.print(d2.roll() + " ");
        }
    }
}
