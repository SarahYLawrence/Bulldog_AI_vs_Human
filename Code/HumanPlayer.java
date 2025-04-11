import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class HumanPlayer extends Player {
    private JButton rollButton;
    private JButton endTurnButton;
    private JLabel scoreLabel;
    private JLabel rollResultLabel;
    private Random random;
    private JLabel turnTotalLabel;
    private JButton nextPlayerButton;
    private BulldogGameGUI bulldog;


    public HumanPlayer(String name, JButton rollButton, JButton endTurnButton, JButton nextPlayerButton, JLabel rollResultLabel, JLabel scoreLabel, JLabel turnTotalLabel, BulldogGameGUI bulldog) {
        super(name);
        this.bulldog = bulldog;
        this.rollButton = rollButton;
        this.endTurnButton = endTurnButton;
        this.nextPlayerButton = nextPlayerButton;
        this.rollResultLabel = rollResultLabel;
        this.scoreLabel = scoreLabel;
        this.turnTotalLabel = turnTotalLabel;  // Store the reference
        this.random = new Random(); // Initialize the random number generator
        addActionListeners();
    }


    public void addActionListeners() {
        rollButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                    if (!isCurrentPlayer()) return;
                    int roll = rollDice();
                    rollResultLabel.setText("Roll: " + roll);

                    if (roll == 6) {
                        turnTotalLabel.setText("Turn Total: 0");
                        setTurnTotal(0);
                        scoreLabel.setText("Total: " + getScore());
                        rollButton.setEnabled(false);
                    }
                    else {
                        addTurnTotal(roll);
                        turnTotalLabel.setText("Turn Total: " + getTurnTotal());
                    }
                
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

    // Define rollDice() to generate a random number between 1 and 6
    private int rollDice() {
        return random.nextInt(6) + 1;
    }

    private boolean isCurrentPlayer() {
        return bulldog.getCurrentPlayer() == this;
    }

    // Implement the abstract play() method (just for example purposes)
    @Override
    public int play() {
        return 0;
    }

}
