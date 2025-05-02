/**
 * Written by Sarah Lawrence. 
 * With the assistance of ChatGBT 3.5.
 */ 

/**
 * Model class for managing a list of players.
 */
import java.util.ArrayList;

/**
 * PlayerList class maintains a list of players and interacts with the scoreboard viewer.
 */
class PlayerList {
    private ArrayList<Player> players;
    private ScoreboardViewer viewer;

    /**
     * Constructs a PlayerList instance.
     */
    public PlayerList() {
        players = new ArrayList<>();
    }

    /**
     * Adds a player to the list and updates the viewer if available.
     * @param player The player to be added.
     */
    public void addPlayer(Player player) {
        players.add(player);
        if (viewer != null) {
            viewer.updateView();
        }
    }
    
    /**
     * Retrieves the name of a player at a given index.
     * @param index The index of the player.
     * @return The player's name, or "Invalid index" if out of bounds.
     */
    public String getPlayerName(int index) {
        if (isValidIndex(index)) {
            return players.get(index).getName(); 
        }
        return "Invalid index";
    }

    /**
     * Retrieves the score of a player at a given index.
     * @param index The index of the player.
     * @return The player's score, or -1 if the index is invalid.
     */
    public int getPlayerScore(int index) {
        if (isValidIndex(index)) {
            return players.get(index).getScore();
        }
        return -1; // Indicates an invalid index
    }

    /**
     * Sets the score of a player at a given index and updates the viewer if available.
     * @param index The index of the player.
     * @param score The new score to be assigned.
     */
    public void setPlayerScore(int index, int score) {
        if (isValidIndex(index)) {
            players.get(index).setScore(score);
            if (viewer != null) {
                viewer.updateView();
            }
        } else {
            System.out.println("Invalid index. Cannot update score.");
        }
    }

    /**
     * Checks whether the given index is valid within the player list.
     * @param index The index to check.
     * @return True if the index is valid, false otherwise.
     */
    private boolean isValidIndex(int index) {
        return index >= 0 && index < players.size();
    }

    /**
     * Retrieves the list of players.
     * @return The list of players.
     */
    public ArrayList<Player> getPlayers() {
        return players;
    }

    /**
     * Sets the scoreboard viewer that will display player scores.
     * @param viewer The ScoreboardViewer instance.
     */
    public void setViewer(ScoreboardViewer viewer) {
        this.viewer = viewer;
    }
}