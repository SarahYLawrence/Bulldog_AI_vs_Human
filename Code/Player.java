public abstract class Player {
    private String name;
    private int score;
    protected int turnTotal;

    public Player(String name) {
        this.name = name;
        this.score = 0;
        this.turnTotal = 0;
    }

    public String getName() {
        return name;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getTurnTotal() {
        return turnTotal;
    }

    // This method adds the roll to the turn total
    public void addTurnTotal(int roll) {
        this.turnTotal += roll;
    }

    // This method will be called to end the player's turn
    public void endTurn() {
        this.score += this.turnTotal;  // Add the turn total to the player's score
        this.turnTotal = 0;  // Reset the turn total for the next turn
    }

    // Abstract method to be implemented by subclasses to play the turn
    public abstract int play();
}
