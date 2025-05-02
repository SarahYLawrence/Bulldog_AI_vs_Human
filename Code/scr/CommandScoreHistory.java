import javax.swing.*;
import java.util.List;

/**
 * A command class that displays the score history of all players in a separate window.
 * Implements the {@code Command} interface, allowing execution and undoing of the action.
 */
public class CommandScoreHistory implements Command {

    /** The list of all players whose score histories are displayed. */
    private List<Player> allPlayers;

    /** The JFrame used to show the history window. */
    private JFrame historyFrame;

    /**
     * Constructs a {@code CommandScoreHistory} object with the provided list of players.
     *
     * @param allPlayers the list of players whose score history will be displayed
     */
    public CommandScoreHistory(List<Player> allPlayers) {
        this.allPlayers = allPlayers;
    }

    /**
     * Executes the command by creating and displaying a window
     * that shows each player's score history.
     */
    @Override
    public void execute() {
        // Create a new window to display everyone's score history
        if (historyFrame == null) {
            historyFrame = new JFrame("All Players Score History");
        }
        historyFrame.setSize(400, 300);
        historyFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        
        // Create a new JTextArea to display all the player score histories
        JTextArea historyArea = new JTextArea();
        historyArea.setEditable(false);  // Make the area read-only
        historyArea.setText("Player Score Histories:\n");

        // Loop through all players and display their score history (latest 5 scores)
        for (Player player : allPlayers) {
            List<Integer> playerScoreHistory = player.getScoreHistory();
            historyArea.append("\n" + player.getName() + ":\n");
            
            if (playerScoreHistory.isEmpty()) {
                historyArea.append("No history available.\n");
            } else {
                for (int score : playerScoreHistory) {
                    historyArea.append("  " + score + "\n");
                }
            }
        }

        // Add the JTextArea to the new frame and make the frame visible
        historyFrame.add(new JScrollPane(historyArea));  // Use JScrollPane for scrolling
        historyFrame.setVisible(true);
    }

    /**
     * Undoes the command by closing the history window if it is open.
     */
    @Override
    public void undo() { 
        if (historyFrame != null) {
            historyFrame.dispose();  // Close the window
        }
    }
}
