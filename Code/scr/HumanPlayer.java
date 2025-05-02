import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * The {@code HumanPlayer} class represents a player controlled by a human in the game.
 * <p>
 * This class extends {@code Player} and provides the functionality for interacting with
 * the game through a GUI. It listens for button actions to roll the dice and updates
 * the user interface with the results.
 */
public class HumanPlayer extends Player {
    /** The button used to roll the dice. */
    private JButton rollButton;

    /** The label that displays the player's score. */
    private JLabel scoreLabel;

    /** The label that displays the result of the dice roll. */
    private JLabel rollResultLabel;

    /** The label that displays the total score for the current turn. */
    private JLabel turnTotalLabel;

    /** The {@code GameGUI} instance that provides the current game state. */
    private GameGUI bulldog;

    /**
     * Constructs a {@code HumanPlayer} with the specified parameters.
     * <p>
     * This constructor initializes the player's GUI elements and sets up action listeners
     * for the roll button.
     *
     * @param name             the name of the player
     * @param rollButton       the button used to roll the dice
     * @param rollResultLabel  the label that shows the dice roll result
     * @param scoreLabel       the label that shows the player's total score
     * @param turnTotalLabel   the label that shows the current turn's total score
     * @param bulldog          the game GUI that holds the current game state
     */
    public HumanPlayer(String name, JButton rollButton, JLabel rollResultLabel, JLabel scoreLabel, JLabel turnTotalLabel, GameGUI bulldog) {
        super(name);
        this.bulldog = bulldog;
        this.rollButton = rollButton;
        this.rollResultLabel = rollResultLabel;
        this.scoreLabel = scoreLabel;
        this.turnTotalLabel = turnTotalLabel;  // Store the reference
        addActionListeners();
    }

    /**
     * Adds action listeners to the roll button.
     * <p>
     * This method listens for a click on the roll button, rolls the dice, updates the UI,
     * and decides whether the player should continue rolling based on the game logic.
     */
    public void addActionListeners() {
        rollButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!isCurrentPlayer()) return;  // Only allow the current player to roll
                Dice dice = new Dice(6);
                int roll = dice.roll();
                rollResultLabel.setText("Roll: " + roll);

                // Check if the player should keep rolling
                boolean keepRolling = play(roll);
                turnTotalLabel.setText("Turn Total: " + getTurnTotal());
                scoreLabel.setText("Total: " + getScore());

                // Disable the roll button if the turn ends
                if (!keepRolling) {
                    rollButton.setEnabled(false);  // end of turn
                }
            }
        });
    }

    /**
     * Checks if this player is the current player in the game.
     *
     * @return {@code true} if this player is the current player, {@code false} otherwise
     */
    private boolean isCurrentPlayer() {
        return bulldog.getCurrentPlayer() == this;
    }

    /**
     * Determines whether the human player should continue rolling the dice.
     * <p>
     * For a human player, this always returns {@code true} because the decision to
     * continue rolling is made through the GUI.
     *
     * @return {@code true} to continue rolling, as determined by the player via GUI
     */
    protected boolean shouldContinueRolling() {
        return true;  // Humans choose via GUI, not auto-roll.
    }
}
