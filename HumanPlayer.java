import java.util.Scanner;

public class HumanPlayer extends Player {

	public HumanPlayer () {
		this("Human");
	}

	public HumanPlayer (String name) {
		super(name);
	}

	public int play() {
		boolean continue_g = true;
		boolean continue_g_two = true;
		int total_score = getScore();
		Scanner scanner = new Scanner(System.in);

		while(continue_g){
			continue_g_two = true; 
			int roll = (int) (Math.random()*6 + 1);
			total_score =  total_score + roll;
			System.out.print("   Player " + getName() + " rolled " + roll );
			
			if (total_score >= 104){
				return total_score;
			}
			else if (roll == 6) {
				roll = 0;
				System.out.println(" and scored 0 for the turn.");
				return getScore();
			}
			else {
				while (continue_g_two) {
					System.out.print(" Woud you like to continue? (Yes or No)");
					String answer = scanner.nextLine();
					if (answer.equals("Yes") || answer.equals("yes")) {
						System.out.println(" and chose to continue, the roll "  
							+ roll + " will be add makeing their total " + total_score + " for the turn.");
						continue_g_two = false;
					}
					else if (answer.equals("No") || answer.equals("no")) { 
						System.out.println(" and chose not to continue, scoring " 
							+ total_score + " for the turn.");
						return total_score;
					}
					else {
						System.out.println(" That dosn't work please try again would you like to continue (Yes or No)");
					}
				}
			}
		}
	 	return 0;
	}

}
