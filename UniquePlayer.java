import java.util.Random;

public class UniquePlayer extends Player {
    public UniquePlayer () {
		this("Fifteen");
	}

	public UniquePlayer (String name) {
		super(name);
	}

	public int play() {
        /* 
        Will flip pick a even or odd number. 
        If even the player will only roll high (6-4) and won't continue. 
        If odd will only rolls low (1-3) and continues.
        */ 
		boolean continue_g = true;
        int total_score = getScore();

        while(continue_g){
            // Random num (even = heads) (odd = tails).
            Random rand = new Random();
			int num = rand.nextInt(50);
            
            // If even can only roll high and don't continue.
            if(num % 2 == 0){
                int roll = 4 + (int)(Math.random() * ((6 - 4) + 1));
                total_score = total_score + roll;
                System.out.print("   Player " + getName() + " a coin has been fliped you got heads and rolled a " + roll + ".");
                
                // If score = 104 end game.
                if (total_score >= 104){
                    return total_score;
                }
                // If 6 score for round = 0.
                else if (roll == 6) {
                    roll = 0;
                    System.out.println(" They have scored 0 for the turn.");
                    return getScore();
                }
                else {
					System.out.println(" They have chosen not to continue, scoring " 
						+ total_score + " for the turn.");
					return total_score;
				}
            }
            // If odd can only roll low and continues.
            else {
                int roll = 1 + (int)(Math.random() * ((3 - 1) + 1));
                total_score = total_score + roll;
                
                // If score = 104 end game.
                if (total_score >= 104){
                    return total_score;
                }
                else {
                System.out.print("   Player " + getName() + " a coin has been fliped you got tails and rolled a " + roll + ".");
                System.out.println(" They have chosen to continue, the roll "  + roll + " will be add makeing their total " + total_score + " for the turn.");
                }
            }
        }
	 	return 0;
	}
}
