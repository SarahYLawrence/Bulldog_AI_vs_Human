

public class WimpPlayer extends Player {

	public WimpPlayer () {
		this("Wimp");
	}

	public WimpPlayer (String name) {
		super(name);
	}

	public int play() {
		// Will always choose to end their turn after the first roll. 
		int roll = (int) (Math.random()*6 + 1);
		int total_score = getScore();
		total_score = total_score + roll;
		System.out.print("   Player " + getName() + " rolled " + roll );

		// If score = 104 end game.
		if (total_score >= 104){
			return total_score;
		}
		// If 6 score for round = 0.
		else if (roll != 6) {
			System.out.println(" and chose not to continue, scoring " 
		           + total_score + " for the turn.");
			return total_score;
		} 
		else {
			System.out.println(" and scored 0 for the turn.");
			return getScore();
		}
	}

}