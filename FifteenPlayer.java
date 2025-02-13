
public class FifteenPlayer extends Player {

	public FifteenPlayer () {
		this("Fifteen");
	}

	public FifteenPlayer (String name) {
		super(name);
	}

	public int play() {
		boolean continue_g = true;
		int total_score = getScore();
		int save_roll = 0;

        // Choose to continue the turn until a score of at least 15 is reached.
		while (continue_g) {
			int roll = (int) (Math.random()*6 + 1);
			total_score = total_score + roll;
			System.out.print("   Player " + getName() + " rolled " + roll );
			
			// If score = 104 end game.
			if (total_score >= 104){
				return total_score;
			}
			// If 6 score for round = 0.
			else if (roll == 6) {
				roll = 0;
				System.out.println(" and scored 0 for the turn.");
				return getScore();
			} 
			// Keep going untill the roll is 15 or more.
			else {
				save_roll = save_roll + roll;
				if (save_roll < 15) {
					System.out.println(" and chose to continue, the roll "  
						+ roll + " will be add makeing their total " + total_score + " for the turn.");
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