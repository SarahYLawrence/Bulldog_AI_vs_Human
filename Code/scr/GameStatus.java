/**
 * The {@code GameStatus} class tracks the current status of a game,
 * specifically monitoring whether any player has reached the winning score.
 */
public class GameStatus {
    /** The list of players participating in the game. */
    private final PlayerList players;

    /** The score required to win the game. */
    private final int winningScore;

    /**
     * Constructs a {@code GameStatus} with a specified list of players and a custom winning score.
     *
     * @param players      the list of players in the game
     * @param winningScore the score required to win the game
     */
    public GameStatus(PlayerList players, int winningScore) {
        this.players = players;
        this.winningScore = winningScore;
    }

    /**
     * Constructs a {@code GameStatus} with a specified list of players and a default winning score of 104.
     *
     * @param players the list of players in the game
     */
    public GameStatus(PlayerList players) {
        this(players, 104);
    }

    /**
     * Checks whether the game is over, i.e., if any player has reached or exceeded the winning score.
     *
     * @return {@code true} if the game is over, otherwise {@code false}
     */
    public boolean isGameOver() {
        return players.getPlayers().stream()
                      .anyMatch(p -> p.getScore() >= winningScore);
    }
}

