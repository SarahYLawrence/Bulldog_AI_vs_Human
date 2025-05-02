import java.util.ArrayList;
import java.util.List;

/**
 * The {@code Player} class represents an abstract player in the Bulldog dice game.
 * It encapsulates the player's name, total game score, and the running total for the current turn.
 * 
 * <p>This class implements core game logic, including updating scores, handling rolls,
 * and determining turn outcomes based on the Bulldog rules.
 * 
 * <p>The {@code play} method defines the basic structure of a player's turn:
 * if a roll results in a 6, the turn ends with 0 points. Otherwise, the roll is added
 * to the turn total and the player's decision to roll again is deferred to the 
 * {@code shouldContinueRolling()} method, which must be implemented by concrete subclasses.
 * 
 * <p>This design follows the Strategy Pattern, separating the decision logic
 * from the gameplay flow and allowing different player behaviors to be implemented
 * via subclassing.
 */
public abstract class Player {
    
    private String name;
    private int score;
    protected int turnTotal;
    private List<Integer> scoreHistory;

    /**
     * Constructs a new Player with the given name.
     * 
     * @param name the name of the player
     */
    public Player(String name) {
        this.name = name;
        this.score = 0;
        this.turnTotal = 0;
        this.scoreHistory = new ArrayList<>();
        this.scoreHistory.add(score);
    }

    private static final int MAX_HISTORY_SIZE = 5; 

    /**
     * Returns the name of the player.
     *
     * @return the player's name
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the player's total score.
     *
     * @return the player's score
     */
    public int getScore() {
        return score;
    }

    /**
     * Sets the player's total score.
     *
     * @param score the new total score
     */
    public void setScore(int score) {
        this.score = score;
    }

    /**
     * Returns the player's current turn total.
     *
     * @return the current turn total
     */
    public int getTurnTotal() {
        return turnTotal;
    }

    /**
     * Adds the value of a die roll to the turn total.
     *
     * @param roll the value rolled on the die
     */
    public void addTurnTotal(int roll) {
        this.turnTotal += roll;
    }

    /**
     * Sets the player's turn total to the specified value.
     *
     * @param value the new turn total
     */
    public void setTurnTotal(int value) {
        this.turnTotal = value;
    }

    /**
     * Ends the player's turn by adding the turn total to the total score 
     * and resetting the turn total to zero.
     */
    public void endTurn() {
        this.score += this.turnTotal;
        this.turnTotal = 0;

        scoreHistory.add(score);
        if (scoreHistory.size() > MAX_HISTORY_SIZE) {
            scoreHistory.remove(0);  // Remove the oldest score if the list exceeds the limit
        }
    }

    /**
     * Plays a single roll in the player's turn.
     * <p>If the roll is 6, the turn ends immediately with no points gained.
     * Otherwise, the roll value is added to the turn total and the decision to
     * continue rolling is determined by {@code shouldContinueRolling()}.
     *
     * @param roll the result of the die roll
     * @return {@code true} if the player should roll again, {@code false} to end the turn
     */
    public boolean play(int roll) {
        if (roll == 6) {
            // Roll a 6 ends the turn with 0 points
            setTurnTotal(0);
            return false;
        } 
        else {
            // Add the roll to the turn total
            addTurnTotal(roll);
            if (shouldContinueRolling()) {
                return true;
            }
        }
        // End the turn and update the score
        return false;
    }

    /**
     * Decision hook for subclasses to determine whether the player 
     * should continue rolling after a successful roll.
     *
     * @return {@code true} if the player decides to continue, {@code false} otherwise
     */
    protected abstract boolean shouldContinueRolling();

    public List<Integer> getScoreHistory() {
        return new ArrayList<>(scoreHistory);
    }
}
