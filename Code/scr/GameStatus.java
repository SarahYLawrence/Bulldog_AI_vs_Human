public class GameStatus {
    private final PlayerList players;
    private final int winningScore;

    public GameStatus(PlayerList players, int winningScore) {
        this.players = players;
        this.winningScore = winningScore;
    }

    public GameStatus(PlayerList players) {
        this(players, 104);
    }

    //get score // other peopls scored

    public boolean isGameOver() {
        return players.getPlayers().stream()
                      .anyMatch(p -> p.getScore() >= winningScore);
    }
}
