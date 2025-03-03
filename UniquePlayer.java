/**
 * Sarah Lawrence
 * Login ID: sarah.lawrence@maine.edu
 * COS 420, Spring 2025
 * Programming Assignment 1
 * UniquePlayer class: The game Bulldog
 */
public class UniquePlayer extends Player {
    /**
     * Default constructor for UniquePlayer.
     */
    public UniquePlayer() {
        this("Unique");
    }
    
    /**
     * Constructs a new UniquePlayer object with a specified name.
     * 
     * @param name The name of the player.
     */
    public UniquePlayer(String name) {
        super(name);
    }

    /**
     * Executes the play logic for the UniquePlayer.
     * 
     * @return The total score earned for the turn.
     */
    public int play() {
        boolean continue_g = true;
        int total_score = getScore();

        // Create a new Dice object for coin flips (2-sided die)
        Dice coin = new Dice(2);

        while (continue_g) {
            // Generate a random number for coin flip (even = heads, odd = tails)
            int num = coin.roll();
            
            // If even, roll high (4-6) and do not continue.
            if (num % 2 == 0) {
                // Create a new Dice object for the high roll (6-sided die)
                Dice highRoll = new Dice(3);
                int roll = highRoll.roll() + 3; // Rolls a value between 1 and 6
                if (roll >= 4) {
                    total_score += roll;
                    System.out.print("   Player " + getName() + " a coin has been flipped, you got heads and rolled a " + roll + ".");
                    
                    // If total score reaches 104, end game.
                    if (total_score >= WINNING_SCORE) {
                        return total_score;
                    }
                    // If roll is 6, score for the round is 0.
                    else if (roll == 6) {
                        roll = 0;
                        System.out.println(" They have scored 0 for the turn.");
                        return getScore();
                    } else {
                        System.out.println(" They have chosen not to continue, scoring " + total_score + " for the turn.");
                        return total_score;
                    }
                }
            }
            // If odd, roll low (1-3) and continue.
            else {
                // Create a new Dice object for the low roll (3-sided die)
                Dice lowRoll = new Dice(3);
                int roll = lowRoll.roll(); // Rolls a value between 1 and 3
                total_score += roll;
                
                // If total score reaches 104, end game.
                if (total_score >= WINNING_SCORE) {
                    return total_score;
                } else {
                    System.out.print("   Player " + getName() + " a coin has been flipped, you got tails and rolled a " + roll + ".");
                    System.out.println(" They have chosen to continue, the roll " + roll + " will be added, making their total " + total_score + " for the turn.");
                }
            }
        }
        return 0;
    }
}
