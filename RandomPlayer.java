import java.util.Random;

public class RandomPlayer extends Player {

	public RandomPlayer () {
		this("Random");
	}

	public RandomPlayer (String name) {
		super(name);
	}

	public int play() {
		boolean continue_g = true;
		int total_score = getScore();

		// Continue or end the current turn randomly
		while(continue_g){
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
			// Contintue if odd and dosn't based if even
			else{
				Random rand = new Random();
				int num = rand.nextInt(50);
				if(num % 2 == 0){
					System.out.println(" and chose to continue, the roll "  
						+ roll + " will be add makeing their total " + total_score + " for the turn.");
				}
				else {
					System.out.println(" and chose not to continue, scoring " 
						+ total_score + " for the turn.");
					return total_score;
				}
			}
		}
	 	return 0;
	}

}
