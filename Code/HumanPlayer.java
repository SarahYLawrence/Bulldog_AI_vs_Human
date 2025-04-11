/**
 * ********************************************************
 * Sarah Lawrence
 * Login ID: sarah.lawrence@maine.edu
 * COS 420, spring 2025
 * Programming Assignment 1
 * abstract Player class: The game Bulldog
 * ********************************************************
 */

 import java.util.Scanner;

 /**
  * Represents a human player in the Bulldog game.
  * The human player decides whether to continue rolling or end their turn.
  */
 public class HumanPlayer extends Player {
	 /**
	  * Default constructor for HumanPlayer.
	  * Creates a HumanPlayer with the default name "Human".
	  */
	 public HumanPlayer () {
		 this("Human");
	 }
 
	 /**
	  * Constructs a new HumanPlayer object with a given name.
	  * @param name The name of the player.
	  */
	 public HumanPlayer (String name) {
		 super(name);
	 }
 
	 /**
	  * Executes the human player's turn, allowing them to decide whether to continue rolling.
	  * @return The total score for this turn.
	  */
	 public int play() {
		 boolean continue_g = true;
		 boolean continue_g_two = true;
		 int total_score = getScore();
		 Scanner scanner = new Scanner(System.in);
 
		 /**
		  * Allows the player to decide whether to continue rolling or not.
		  */
		 while(continue_g){
			 continue_g_two = true; 
			 Dice dice = new Dice(6);
             int roll = dice.roll();
			 total_score = total_score + roll;
			 System.out.print("   Player " + getName() + " rolled " + roll );
			 
			 // If score reaches 104, end game.
			 if (total_score >= WINNING_SCORE){
				 return total_score;
			 }
			 // If a 6 is rolled, the score for this turn is 0.
			 else if (roll == 6) {
				 roll = 0;
				 System.out.println(" and scored 0 for the turn.");
				 return getScore();
			 }
			 else {
				 // Player decision to continue or stop.
				 while (continue_g_two) {
					 System.out.print(" Would you like to continue? (Yes or No)");
					 String answer = scanner.nextLine();
					 // Player decides to continue.
					 if (answer.equals("Yes") || answer.equals("yes")) {
						 System.out.println(" and chose to continue, the roll "  
							 + roll + " will be added making their total " + total_score + " for the turn.");
						 continue_g_two = false;
					 }
					 // Player decides to stop.
					 else if (answer.equals("No") || answer.equals("no")) { 
						 System.out.println(" and chose not to continue, scoring " 
							 + total_score + " for the turn.");
						 return total_score;
					 }
					 // Player gives invalid input.
					 else {
						 System.out.println(" That doesn't work, please try again. Would you like to continue? (Yes or No)");
					 }
				 }
			 }
		 }
		  return 0;
	 }
 }
 