import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

// Viewer class for Scoreboard (as a JPanel)
class ScoreboardViewer extends JPanel {
    private PlayerList playerList;
    private JTextArea displayArea;

    public ScoreboardViewer(PlayerList playerList) {
        this.playerList = playerList;
        this.playerList.setViewer(this);

        setLayout(new BorderLayout());

        displayArea = new JTextArea();
        displayArea.setEditable(false);
        add(new JScrollPane(displayArea), BorderLayout.CENTER);

        // Leave Button to leave the scoreboard
        JButton closeScoreboardButton = new JButton("Leave");
        add(closeScoreboardButton, BorderLayout.SOUTH); // Add to bottom
        
        closeScoreboardButton.addActionListener(e -> {
            Container parent = getParent(); // ScoreboardPanel
            if (parent != null && parent.getParent() instanceof JPanel) {
                CardLayout layout = (CardLayout) parent.getParent().getLayout();
                layout.show(parent.getParent(), "GameScreen");
            }
        });

        updateView();
    }

    public void updateView() {
        StringBuilder sb = new StringBuilder("Player Scores:\n");
        for (Player player : playerList.getPlayers()) {
            sb.append(player.getName()).append(" - ").append(player.getScore()).append("\n");
        }
        displayArea.setText(sb.toString());
    }
}