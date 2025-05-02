/**
 * Written by Sarah Lawrence. 
 * With the assistance of ChatGBT 3.5.
 */ 

/**
 * The {@code Referee} class manages the flow of a game by keeping track of the current player.
 * <p>
 * It follows the Singleton pattern to ensure that only one instance of the Referee exists
 * throughout the game. It operates on a {@code PlayerList} to cycle through players.
 */
public class Referee {
    /** Singleton instance of the Referee. */
    private static Referee instance;

    /** The list of players participating in the game. */
    private PlayerList players;

    /** Index of the current player in the player list. */
    private int currentPlayerIndex;

    /**
     * Private constructor to prevent direct instantiation.
     *
     * @param players the list of players in the game
     */
    private Referee(PlayerList players) {
        this.players = players;
        this.currentPlayerIndex = 0;
    }

    /**
     * Returns the singleton instance of the {@code Referee}, creating it if necessary.
     *
     * @param players the list of players (only used on first call)
     * @return the single {@code Referee} instance
     */
    public static Referee getInstance(PlayerList players) {
        if (instance == null) {
            instance = new Referee(players);
        }
        return instance;
    }

    /**
     * Returns the player whose turn it currently is.
     *
     * @return the current {@code Player}
     */
    public Player getCurrentPlayer() {
        return players.getPlayers().get(currentPlayerIndex);
    }

    /**
     * Advances to the next player in the list, cycling back to the beginning if needed.
     */
    public void nextPlayer() {
        currentPlayerIndex = (currentPlayerIndex + 1) % players.getPlayers().size();
    }

    /**
     * Returns the index of the current player in the player list.
     *
     * @return the current player index
     */
    public int getCurrentPlayerIndex() {
        return currentPlayerIndex;
    }
}
