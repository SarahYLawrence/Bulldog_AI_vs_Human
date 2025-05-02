import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

/**
 * Represents a computer-controlled player for the Bulldog game that rolls the dice
 * repeatedly until a stopping condition is met.
 * <p>
 * This player will continue rolling the dice until their turn total exceeds 7,
 * or until a roll of 6 is encountered — which immediately ends their turn with zero points.
 * The class integrates with Swing components to handle display and interaction.
 */
public class RandomPlayer extends Player {

    /** The button used to trigger a dice roll action. */
    private JButton rollButton;

    /** Label for displaying the total score of the player. */
    private JLabel scoreLabel;

    /** Label for displaying the result of the latest dice roll. */
    private JLabel rollResultLabel;

    /** Label for displaying the current accumulated turn total. */
    private JLabel turnTotalLabel;

    /** Reference to the main Bulldog game GUI controller. */
    private GameGUI bulldog;

    /** The dice object used to simulate dice rolls. */
    private RandomDice dice;

    /**
     * Constructs a new {@code RandomPlayer} with the necessary GUI components and game logic.
     *
     * @param name            the name of the player
     * @param rollButton      the button used to initiate a dice roll
     * @param rollResultLabel the label used to display dice roll outcomes
     * @param scoreLabel      the label used to display the player's total score
     * @param turnTotalLabel  the label used to display the current turn total
     * @param bulldog         reference to the main game GUI logic
     * @param dice            the dice object used for rolling
     */
    public RandomPlayer(String name, JButton rollButton, JLabel rollResultLabel,
                        JLabel scoreLabel, JLabel turnTotalLabel, GameGUI bulldog, RandomDice dice) {
        super(name);
        this.bulldog = bulldog;
        this.rollButton = rollButton;
        this.rollResultLabel = rollResultLabel;
        this.scoreLabel = scoreLabel;
        this.turnTotalLabel = turnTotalLabel;
        this.dice = dice;
        addActionListeners();
    }

    /**
     * Attaches an {@link ActionListener} to the roll button.
     * When triggered, the player will automatically roll the dice in a loop
     * until a stopping condition is met (rolling a 6 or exceeding a safe turn total).
     */
    public void addActionListeners() {
        rollButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!isCurrentPlayer()) return;

                int roll = 0;
                boolean result = true;

                while (result) {
                    roll = dice.roll();
                    result = play(roll);
                    updateUI();
                }
                rollButton.setEnabled(false);
            }
        });
    }

    /**
     * Updates the user interface labels to reflect the current state of the game,
     * including the roll result, turn total, and total score.
     * <p>
     * Note: Currently displays "--" for the roll result and should ideally
     * show the actual sequence of rolls for better feedback.
     */
    private void updateUI() {
        rollResultLabel.setText("Roll: --");  // Potential improvement: Display actual rolls
        turnTotalLabel.setText("Turn Total: " + getTurnTotal());
        scoreLabel.setText("Total: " + getScore());
    }

    /**
     * Checks if it is currently this player's turn in the Bulldog game.
     *
     * @return {@code true} if this player is the active player, {@code false} otherwise.
     */
    private boolean isCurrentPlayer() {
        return bulldog.getCurrentPlayer() == this;
    }

    /**
     * Random decision-making logic to determine if the player should continue rolling.
     * <p>
     * This method uses a random boolean generator to simulate indecisive or
     * unpredictable behavior typical of a non-human player.
     *
     * @return {@code true} if the player should continue rolling; {@code false} otherwise.
     */
    protected boolean shouldContinueRolling() {
        Random random = new Random();
        return random.nextBoolean();
    }
}
