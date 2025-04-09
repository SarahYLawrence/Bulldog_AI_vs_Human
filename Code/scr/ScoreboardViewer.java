import java.awt.BorderLayout;
import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;

// Viewer class for Scoreboard
class ScoreboardViewer extends JFrame {
    private PlayerList playerList;
    private JTextArea displayArea;

    public ScoreboardViewer(PlayerList playerList) {
        this.playerList = playerList;
        this.playerList.setViewer(this);

        setTitle("Scoreboard");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        displayArea = new JTextArea();
        displayArea.setEditable(false);
        add(new JScrollPane(displayArea), BorderLayout.CENTER);

        updateView();
        setVisible(true);
    }

    public void updateView() {
        StringBuilder sb = new StringBuilder("Player Scores:\n");
        for (Player player : playerList.getPlayers()) {
            sb.append(player.getName()).append(" - ").append(player.getScore()).append("\n");
        }
        displayArea.setText(sb.toString());
    }
}