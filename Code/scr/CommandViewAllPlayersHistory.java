import javax.swing.*;
import java.util.List;

public class CommandViewAllPlayersHistory implements Command {
    private List<Player> allPlayers;  // List of all players

    public CommandViewAllPlayersHistory(List<Player> allPlayers) {
        this.allPlayers = allPlayers;
    }

    @Override
    public void execute() {
        // Create a new window to display everyone's score history
        JFrame historyFrame = new JFrame("All Players Score History");
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
}
