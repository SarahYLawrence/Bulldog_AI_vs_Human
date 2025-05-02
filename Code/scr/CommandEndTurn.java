import javax.swing.*;
import java.awt.CardLayout;

public class CommandEndTurn implements Command {
    private Player currentPlayer;
    private GameStatus gameStatus;
    private JLabel winnerLabel;
    private JLabel rollResultLabel;
    private JLabel turnTotalLabel;
    private JLabel totalLabel;
    private JButton rollDiceButton;
    private JButton endTurnButton;
    private JButton nextPlayerButton;
    private JPanel mainPanel;
    private CardLayout cardLayout;
    private JFrame frame;

    public CommandEndTurn(Player currentPlayer, GameStatus gameStatus, JLabel winnerLabel,
                          JLabel rollResultLabel, JLabel turnTotalLabel, JLabel totalLabel,
                          JButton rollDiceButton, JButton endTurnButton, JButton nextPlayerButton,
                          JPanel mainPanel, CardLayout cardLayout, JFrame frame) {
        this.currentPlayer = currentPlayer;
        this.gameStatus = gameStatus;
        this.winnerLabel = winnerLabel;
        this.rollResultLabel = rollResultLabel;
        this.turnTotalLabel = turnTotalLabel;
        this.totalLabel = totalLabel;
        this.rollDiceButton = rollDiceButton;
        this.endTurnButton = endTurnButton;
        this.nextPlayerButton = nextPlayerButton;
        this.mainPanel = mainPanel;
        this.cardLayout = cardLayout;
        this.frame = frame;
    }

    @Override
    public void execute() {
        // End the current player's turn
        currentPlayer.endTurn();

        // Check if the game is over
        if (gameStatus.isGameOver()) {
            if (currentPlayer instanceof HumanPlayer) {
                winnerLabel.setText(currentPlayer.getName() + " Wins!");
                cardLayout.show(mainPanel, "WinScreen");
            } else {
                JOptionPane.showMessageDialog(frame, currentPlayer.getName() + " (bot) wins the game.");
            }
        } else {
            // If game isn't over, reset the board and move to next turn
            rollResultLabel.setText("Roll: --");
            turnTotalLabel.setText("Turn Total: 0");
            totalLabel.setText("Total: " + currentPlayer.getScore());
            nextPlayerButton.setEnabled(true);
        }

        // Disable buttons after turn is ended
        rollDiceButton.setEnabled(false);
        endTurnButton.setEnabled(false);
    }
}
