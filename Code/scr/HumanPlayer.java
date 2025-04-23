import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HumanPlayer extends Player {
    private JButton rollButton;
    private JLabel scoreLabel;
    private JLabel rollResultLabel;
    private JLabel turnTotalLabel;
    private GameGUI bulldog;


    public HumanPlayer(String name, JButton rollButton, JLabel rollResultLabel, JLabel scoreLabel, JLabel turnTotalLabel, GameGUI bulldog) {
        super(name);
        this.bulldog = bulldog;
        this.rollButton = rollButton;
        this.rollResultLabel = rollResultLabel;
        this.scoreLabel = scoreLabel;
        this.turnTotalLabel = turnTotalLabel;  // Store the reference
        addActionListeners();
    }


    public void addActionListeners() {
        rollButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                    if (!isCurrentPlayer()) return;
                    Dice dice = new Dice(6);
                    int roll = dice.roll();
                    rollResultLabel.setText("Roll: " + roll);

                    boolean keepRolling = play(roll);
                    turnTotalLabel.setText("Turn Total: " + getTurnTotal());
                    scoreLabel.setText("Total: " + getScore());
                    if (!keepRolling) {
                        rollButton.setEnabled(false);  // end of turn
                    }
            }
        });
    }

    private boolean isCurrentPlayer() {
        return bulldog.getCurrentPlayer() == this;
    }

    protected boolean shouldContinueRolling() {
        return true;  // humans choose via GUI, not auto-roll.
    }

}
