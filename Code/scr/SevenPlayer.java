import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * A player implementation for the Bulldog game that keeps rolling the dice
 * until their turn total exceeds 7 or they roll a 6 (which ends their turn with 0 points).
 * This class uses Swing components for interaction and display.
 */
public class SevenPlayer extends Player {
    /** Button to initiate a dice roll */
    private JButton rollButton;

    /** Label to display the result of a dice roll */
    private JLabel scoreLabel;

    /** Label to display the score result of a turn */
    private JLabel rollResultLabel;

    /** Label to display the cumulative total of the current turn */
    private JLabel turnTotalLabel;

    /** Reference to the main GameGUI controller */
    private GameGUI bulldog;

    /** The cumulative total for the current turn */
    private int turn_total;

    /** Dice used for generating random rolls */
    private RandomDice dice;

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
    public SevenPlayer(String name, JButton rollButton, JLabel rollResultLabel,
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
                while (turn_total <= 7) {
                    roll = dice.roll();

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
    }

    /**
     * Checks if it is currently this player's turn.
     *
     * @return true if this player is the current player in the game, false otherwise
     */
    private boolean isCurrentPlayer() {
        return bulldog.getCurrentPlayer() == this;
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
