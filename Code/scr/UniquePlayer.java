import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

/**
 * A player implementation for the Bulldog game that keeps rolling the dice
 * until their turn total exceeds 7 or they roll a 6 (which ends their turn with 0 points).
 * This class uses Swing components for interaction and display.
 */
public class UniquePlayer extends Player {
    private JButton rollButton;
    private JLabel scoreLabel;
    private JLabel rollResultLabel;
    private JLabel turnTotalLabel;
    private GameGUI bulldog;
    private RandomDice dice;
    private int roll = 0;

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
    public UniquePlayer(String name, JButton rollButton, JLabel rollResultLabel,
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

                boolean result = true;

                // Continue rolling until the turn total exceeds 7
                while (result) {
                    roll = dice.roll();
                    result = play(roll);
                    updateUI();
                }
                rollButton.setEnabled(false);
            }
        });
    }

    private void updateUI() {
        rollResultLabel.setText("Roll: --"); //Better improve: show all the rolls 
        turnTotalLabel.setText("Turn Total: " + getTurnTotal());
        scoreLabel.setText("Total: " + getScore());
    }

    /**
     * Checks if it is currently this player's turn.
     *
     * @return true if this player is the current player in the game, false otherwise
     */
    private boolean isCurrentPlayer() {
        return bulldog.getCurrentPlayer() == this;
    }

     // @Override
     protected boolean shouldContinueRolling() {
        return  roll == 3 && roll == 5;
    }

}
