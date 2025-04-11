import static org.junit.Assert.*;
import org.junit.Test;

public class FakeRandomTest {

    @Test
    public void testFixedRoll() {
        RandomDice dice = new FakeRandom(6, 4);
        assertEquals(4, dice.roll());
        assertEquals(4, dice.roll()); // Should always return 4
    }

    @Test
    public void testSequenceRolls() {
        RandomDice dice = new FakeRandom(6, 2, 5, 3);
        assertEquals(2, dice.roll());
        assertEquals(5, dice.roll());
        assertEquals(3, dice.roll());
        assertEquals(2, dice.roll()); // Loops back
    }
}
