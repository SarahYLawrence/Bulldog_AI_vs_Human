/**
 * Written by Sarah Lawrence. 
 * With the assistance of ChatGBT 3.5.
 */ 

 public class Referee {
    private static Referee instance;  // Singleton instance
    private PlayerList players;
    private int currentPlayerIndex;

    // Private constructor to prevent instantiation
    private Referee(PlayerList players) {
        this.players = players;
        this.currentPlayerIndex = 0;
    }

    // Public method to provide access to the instance
    public static Referee getInstance(PlayerList players) {
        if (instance == null) {
            instance = new Referee(players);
        }
        return instance;
    }

    public Player getCurrentPlayer() {
        return players.getPlayers().get(currentPlayerIndex);
    }

    public void nextPlayer() {
        currentPlayerIndex = (currentPlayerIndex + 1) % players.getPlayers().size();
    }

    public boolean isGameOver() {
        for (Player p : players.getPlayers()) {
            if (p.getScore() >= 100) return true; 
        }
        return false;
    }

    public Player getWinner() {
        for (Player p : players.getPlayers()) {
            if (p.getScore() >= 100) { // Match your isGameOver() threshold
                return p;
            }
        }
        return null;
    }

    public int getCurrentPlayerIndex() {
        return currentPlayerIndex;
    }
}

// public class Referee {
//     private PlayerList players;
//     private int currentPlayerIndex;
    
//     public Referee(PlayerList players) {
//         this.players = players;
//         this.currentPlayerIndex = 0;
//     }

//     public Player getCurrentPlayer() {
//         return players.getPlayers().get(currentPlayerIndex);
//     }

//     public void nextPlayer() {
//         currentPlayerIndex = (currentPlayerIndex + 1) % players.getPlayers().size();
//     }

//     public boolean isGameOver() {
//         for (Player p : players.getPlayers()) {
//             if (p.getScore() >= 100) return true; 
//         }
//         return false;
//     }
//     public Player getWinner() {
//         for (Player p : players.getPlayers()) {
//             if (p.getScore() >= 100) { // Match your isGameOver() threshold
//                 return p;
//             }
//         }
//         return null;
//     }

//     public int getCurrentPlayerIndex() {
//         return currentPlayerIndex;
//     }
// }
