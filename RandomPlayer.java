/**
 * Sarah Lawrence  
 * Login ID: sarah.lawrence@maine.edu  
 * COS 420, spring 2025  
 * Programming Assignment 1  
 * abstract Player class: The game Bulldog  
 */  

 import java.util.Random;

 /**
  * Represents a RandomPlayer in the game Bulldog. 
  * This player will choose to continue or end the current turn randomly, with a 50% probability.
  */
 public class RandomPlayer extends Player {
	 /**
	  * Default constructor for RandomPlayer.
	  */
	 public RandomPlayer() {
		 this("Random");
	 }
 
	 /**
	  * Constructs a new RandomPlayer object with the specified name.
	  * 
	  * @param name The name of the player.
	  */
	 public RandomPlayer(String name) {
		 super(name);
	 }
 
	 /**
	  * Plays the turn for the RandomPlayer. 
	  * The player continues or ends the current turn randomly.
	  * 
	  * @return The total score after the turn.
	  */
	 public int play() {
		 boolean continue_g = true;
		 int total_score = getScore();
 
		 // Continue or end the current turn randomly
		 while (continue_g) {
			 Dice dice = new Dice(6);
             int roll = dice.roll();
			 total_score = total_score + roll;
			 System.out.print("   Player " + getName() + " rolled " + roll);
 
			 // If score = 104, end game.
			 if (total_score >= WINNING_SCORE) {
				 return total_score;
			 }
			 // If 6 is rolled, score for round = 0.
			 else if (roll == 6) {
				 roll = 0;
				 System.out.println(" and scored 0 for the turn.");
				 return getScore();
			 }
			 // Continue if odd, stop if even
			 else {
				 Dice num = new Dice(50);
				 int rand = num.roll();
				 if (rand % 2 == 0) {
					 System.out.println(" and chose to continue, the roll " + roll + " will be added, making their total " + total_score + " for the turn.");
				 } else {
					 System.out.println(" and chose not to continue, scoring " + total_score + " for the turn.");
					 return total_score;
				 }
			 }
		 }
		 return 0;
	 }
 }
 