import java.util.ArrayList;
import java.util.List;

public class BulldogGame {
    private List<Player> players;
    private int currentPlayerIndex;
    private int turnScore;

    public BulldogGame() {
        players = new ArrayList<>();
        currentPlayerIndex = 0;
        turnScore = 0;
    }

    public void addPlayer(Player player) {
        players.add(player);
    }

    public void startGame() {
        // Start with the first player
        currentPlayerIndex = 0;
    }

    public void nextTurn() {
        Player currentPlayer = players.get(currentPlayerIndex);
        int scoreThisTurn = currentPlayer.play();
        currentPlayer.setScore(currentPlayer.getScore() + scoreThisTurn);

        // If a player reaches 104 points, they win
        if (currentPlayer.getScore() >= 104) {
            System.out.println(currentPlayer.getName() + " wins the game with " + currentPlayer.getScore() + " points!");
            return;
        }

        // Otherwise, move to the next player
        currentPlayerIndex = (currentPlayerIndex + 1) % players.size();
    }

    public Player getCurrentPlayer() {
        return players.get(currentPlayerIndex);
    }
}
