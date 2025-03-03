/**
 * ********************************************************
 * Sarah Lawrence
 * Login ID: sarah.lawrence@maine.edu
 * COS 420, spring 2025
 * Programming Assignment 1
 * abstract Player class: The game Bulldog
 * ********************************************************
 */

/**
 * Abstract class representing a player in the Bulldog game.
 */
public abstract class Player {
	public static final int WINNING_SCORE = 104;
	/**
	 * The name of the Player.
	 */
	private String name;   	
	
	/**
	 * The score earned by this Player during the game.
	 */
	private int score;		
	
	/**
	 * Constructs a new Player with the given name and initializes the score to zero.
	 * @param name The name of the player.
	 */
	public Player (String name) {
		this.name = name;
		this.score = 0;
	}
	
	/**
	 * Returns the name of this Player.
	 * @return The name of the player.
	 */
	public String getName() {
		return this.name;
	}
	
	/**
	 * Returns the current score of this Player.
	 * @return The player's current score.
	 */
	public int getScore() {
		return this.score;
	}
	
	/**
	 * Sets the current score of this Player.
	 * @param score The new score to set.
	 */
	public void setScore(int score) {
		this.score = score;
	}
	
	/**
	 * Abstract method representing a player's turn.
	 * The score earned by the player on this turn will be zero if a six was rolled.
	 * @return The score earned during this turn.
	 */
	public abstract int play();
	
}
