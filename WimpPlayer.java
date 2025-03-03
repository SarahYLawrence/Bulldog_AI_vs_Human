/**
 * The WimpPlayer class represents a player in the Bulldog game 
 * who always ends their turn after the first roll.
 * 
 * <p>This class extends the abstract Player class and overrides the 
 * {@code play()} method to implement the specific behavior of a "Wimp" player.</p>
 * 
 * @author Sarah Lawrence
 * @version Spring 2025
 */
public class WimpPlayer extends Player {

    /**
     * Default constructor that creates a WimpPlayer with the name "Wimp".
     */
    public WimpPlayer() {
        this("Wimp");
    }

    /**
     * Constructs a WimpPlayer with a specified name.
     *
     * @param name The name of the player.
     */
    public WimpPlayer(String name) {
        super(name);
    }

    /**
     * Simulates a turn for the WimpPlayer. 
     * The player rolls once and then stops, unless they roll a 6, 
     * in which case their score for the turn is reset to 0.
     *
     * @return The player's updated score after the turn.
     */
    @Override
    public int play() {
		Dice dice = new Dice(6);
        int roll = dice.roll();
        int totalScore = getScore() + roll;

        System.out.print("   Player " + getName() + " rolled " + roll);

        // If the score reaches 104, the game ends.
        if (totalScore >= WINNING_SCORE) {
            return totalScore;
        }
        // If the roll is 6, the score for this turn is 0.
        else if (roll == 6) {
            System.out.println(" and scored 0 for the turn.");
            return getScore();
        } 
        // Otherwise, the player chooses not to continue and keeps their score.
        else {
            System.out.println(" and chose not to continue, scoring " + totalScore + " for the turn.");
            return totalScore;
        }
    }
}
