import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

import javax.swing.*;

public class SevenPlayerTest {
    private SevenPlayer player;
    private JButton rollButton;
    private JLabel rollResultLabel;
    private JLabel scoreLabel;
    private JLabel turnTotalLabel;
    private MockGameGUI bulldog;

    @Before
    public void setUp() {
        rollButton = new JButton();
        rollResultLabel = new JLabel();
        scoreLabel = new JLabel();
        turnTotalLabel = new JLabel();
        bulldog = new MockGameGUI();
    }

    @Test
    public void testStopsAtSix() throws Exception {
        FakeRandom fakeDice = new FakeRandom(6, 6); // rolls a 6 immediately
        setupPlayer(fakeDice);

        SwingUtilities.invokeAndWait(() -> rollButton.doClick());

        assertEquals("Roll: 6", rollResultLabel.getText());
        assertEquals("Turn Total: 0", turnTotalLabel.getText());
        assertEquals("Total: 0", scoreLabel.getText());
        assertFalse(rollButton.isEnabled());
    }

    @Test
    public void testRollsUntilOverSeven() throws Exception {
        FakeRandom fakeDice = new FakeRandom(6, 3, 2); // sum = 8
        setupPlayer(fakeDice);

        SwingUtilities.invokeAndWait(() -> rollButton.doClick());

        assertEquals("Turn Total: 8", turnTotalLabel.getText());
        assertFalse(rollButton.isEnabled());
    }

    @Test
    public void testRollsAndThenSix() throws Exception {
        FakeRandom fakeDice = new FakeRandom(6, 2, 6); // should reset to 0 at 6
        setupPlayer(fakeDice);

        SwingUtilities.invokeAndWait(() -> rollButton.doClick());

        assertEquals("Roll: 6", rollResultLabel.getText());
        assertEquals("Turn Total: 0", turnTotalLabel.getText());
        assertFalse(rollButton.isEnabled());
    }
        

    private void setupPlayer(RandomDice dice) {
        player = new SevenPlayer("Test", rollButton, rollResultLabel, scoreLabel, turnTotalLabel, bulldog,dice);
        bulldog.setCurrentPlayer(player);
    }

    // Minimal mock GameGUI
    private static class MockGameGUI extends GameGUI {
        private Player current;

        public void setCurrentPlayer(Player p) {
            this.current = p;
        }

        @Override
        public Player getCurrentPlayer() {
            return current;
        }
    }
}
