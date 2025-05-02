import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

/**
 * The {@code ScoreboardViewer} class is a {@code JPanel} that displays the scores of all players
 * in the game.
 * <p>
 * This class updates the view of the scoreboard to show the current scores of all players
 * in a {@code JTextArea}. It also includes a button to close the scoreboard and return to the game screen.
 */
class ScoreboardViewer extends JPanel {
    /** The list of players whose scores are displayed on the scoreboard. */
    private PlayerList playerList;

    /** The text area used to display player scores. */
    private JTextArea displayArea;

    /**
     * Constructs a {@code ScoreboardViewer} with the given list of players.
     * <p>
     * This constructor sets up the layout, initializes the display area, and adds an action listener
     * to the close button to allow the user to leave the scoreboard and return to the game screen.
     *
     * @param playerList the list of players whose scores will be displayed
     */
    public ScoreboardViewer(PlayerList playerList) {
        this.playerList = playerList;
        this.playerList.setViewer(this);  // Set the viewer reference in the player list

        setLayout(new BorderLayout());

        // Initialize and configure the display area for player scores
        displayArea = new JTextArea();
        displayArea.setEditable(false);
        add(new JScrollPane(displayArea), BorderLayout.CENTER);

        // Button to leave the scoreboard and return to the game screen
        JButton closeScoreboardButton = new JButton("Leave");
        add(closeScoreboardButton, BorderLayout.SOUTH); // Add to bottom of panel
        
        // Action listener for closing the scoreboard
        closeScoreboardButton.addActionListener(e -> {
            Container parent = getParent(); // Get the parent container (ScoreboardPanel)
            if (parent != null && parent.getParent() instanceof JPanel) {
                CardLayout layout = (CardLayout) parent.getParent().getLayout();
                layout.show(parent.getParent(), "GameScreen");  // Show the game screen
            }
        });

        updateView();  // Initial update of the scoreboard view
    }

    /**
     * Updates the scoreboard view with the current scores of all players.
     * <p>
     * This method iterates through the list of players and updates the display area with
     * their names and current scores.
     */
    public void updateView() {
        StringBuilder sb = new StringBuilder("Player Scores:\n");
        for (Player player : playerList.getPlayers()) {
            sb.append(player.getName()).append(" - ").append(player.getScore()).append("\n");
        }
        displayArea.setText(sb.toString());  // Set the updated text in the display area
    }
}
