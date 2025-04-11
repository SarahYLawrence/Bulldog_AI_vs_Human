/**
 * Sarah Lawrence
 * Login ID: sarah.lawrence@maine.edu
 * COS 420, spring 2025
 * Programming Assignment 1
 * Abstract Player class: The game Bulldog
 */

 public class FifteenPlayer extends Player {
    /**
     * Default FifteenPlayer constructor.
     */
    public FifteenPlayer () {
        this("Fifteen");
    }

    /**
     * Constructs a new FifteenPlayer object with the specified name.
     * 
     * @param name the name of the FifteenPlayer
     */
    public FifteenPlayer (String name) {
        super(name);
    }

    /**
     * Plays a turn for the FifteenPlayer.
     * Continues rolling until a score of at least 15 is reached.
     * 
     * @return the total score for the turn
     */
    public int play() {
        boolean continue_g = true;
        int total_score = getScore();
        int save_roll = 0;

        // Choose to continue the turn until a score of at least 15 is reached.
        while (continue_g) {
            Dice dice = new Dice(6);
            int roll = dice.roll();
            total_score = total_score + roll;
            System.out.print("   Player " + getName() + " rolled " + roll );
            
            // If score = 104, end game.
            if (total_score >= WINNING_SCORE){
                return total_score;
            }
            // If 6 is rolled, score for the round is 0.
            else if (roll == 6) {
                roll = 0;
                System.out.println(" and scored 0 for the turn.");
                return getScore();
            } 
            // Keep rolling until the accumulated roll is 15 or more.
            else {
                save_roll = save_roll + roll;
                if (save_roll < 15) {
                    System.out.println(" and chose to continue, the roll "  
                        + roll + " will be added making their total " + total_score + " for the turn.");
                }
                else {
                    total_score = total_score + roll;
                    System.out.println(" and chose not to continue, scoring " 
                        + total_score + " for the turn.");
                    return total_score;
                }
            }
        }
        return 0;
    }
}
