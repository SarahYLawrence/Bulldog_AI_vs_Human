import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

/**
 * A player implementation for the Bulldog game that keeps rolling the dice
 * until their turn total exceeds 7 or they roll a 6 (which ends their turn with 0 points).
 * This class uses Swing components for interaction and display.
 */
public class FifteenPlayer extends Player {
    private JButton rollButton;
    private JLabel scoreLabel;
    private JLabel rollResultLabel;
    private JLabel turnTotalLabel;
    private Random random;
    private BulldogGameGUI bulldog;
    private int turn_total;
    private JButton endTurnButton;
    private JButton nextPlayerButton;

    /**
     * Constructs a new SevenPlayer with the necessary GUI components and game logic.
     *
     * @param name             the name of the player
     * @param rollButton       the button to trigger dice rolls
     * @param rollResultLabel  label to display dice roll results
     * @param scoreLabel       label to display the player's total score
     * @param turnTotalLabel   label to display the current turn total
     * @param bulldog          reference to the main game GUI
     * @param dice             the dice object used for rolling
     */
    public FifteenPlayer(String name, JButton rollButton, JButton endTurnButton, JButton nextPlayerButton, JLabel rollResultLabel,
                       JLabel scoreLabel, JLabel turnTotalLabel, BulldogGameGUI bulldog) {
        super(name);
        this.bulldog = bulldog;
        this.rollButton = rollButton;
        this.rollResultLabel = rollResultLabel;
        this.endTurnButton = endTurnButton;
        this.nextPlayerButton = nextPlayerButton;
        this.scoreLabel = scoreLabel;
        this.turnTotalLabel = turnTotalLabel;
        this.random = new Random();
        addActionListeners();
    }

    /**
     * Adds the ActionListener to the roll button to define how the player takes turns.
     * The player will roll until they exceed a total of 7 or roll a 6.
     */
    public void addActionListeners() {
        rollButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!isCurrentPlayer()) return;

                int roll = 0;
                turn_total = getTurnTotal();

                // Continue rolling until the turn total exceeds 7
                while (turn_total <= 15) {
                    roll = rollDice();

                    if (roll == 6) {
                        rollResultLabel.setText("Roll: " + roll);
                        turnTotalLabel.setText("Turn Total: 0");
                        setTurnTotal(0);
                        scoreLabel.setText("Total: " + getScore());
                        rollButton.setEnabled(false);
                        break;
                    } else {
                        addTurnTotal(roll);
                        turn_total = getTurnTotal();
                        turnTotalLabel.setText("Turn Total: " + turn_total);
                    }
                }

                rollButton.setEnabled(false);
            }
        });

        endTurnButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!isCurrentPlayer()) return;
                // Set score
                endTurn();  

                // Reset board
                rollResultLabel.setText("Roll: --");
                turnTotalLabel.setText("Turn Total: 0");
                scoreLabel.setText("Total: " + getScore());

                // Disable roll and end turn buttons, enable next player button
                rollButton.setEnabled(false);
                endTurnButton.setEnabled(false);
                nextPlayerButton.setEnabled(true);
            }
        });
    }

    /**
     * Checks if it is currently this player's turn.
     *
     * @return true if this player is the current player in the game, false otherwise
     */
    private boolean isCurrentPlayer() {
        return bulldog.getCurrentPlayer() == this;
    }

     // Define rollDice() to generate a random number between 1 and 6
     private int rollDice() {
        return random.nextInt(6) + 1;
    }

    /**
     * Defines how the player plays their turn.
     * Not used directly in this implementation since it's event-driven.
     *
     * @return always returns 0 (not used in event-driven context)
     */
    @Override
    public int play() {
        return 0;
    }
}
