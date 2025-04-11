public class WimpPlayer extends Player {
    public WimpPlayer(String name) {
        super(name);
    }

    @Override
    public int play() {
        // Wimp player rolls automatically
        int roll = (int) (Math.random() * 6) + 1;  // Roll between 1 and 6
        addTurnTotal(roll);  // Add the roll to turn total
        return roll;
    }

    @Override
    public void endTurn() {
        // Wimp player automatically ends the turn
        super.endTurn();  // Call the base class method to add turn total to score and reset it
    }
}
