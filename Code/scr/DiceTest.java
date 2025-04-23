public class DiceTest {
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