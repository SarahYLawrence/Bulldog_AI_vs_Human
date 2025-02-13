
public abstract class Player {
	
	private String name;   	// The name of the Player
	
	private int score;		// The score earned by this Player during the game
	
	public Player (String name) {
		this.name = name;
		this.score = 0;
	}
	
	public String getName() {
		return this.name;
	}

	public int getScore() {
		return this.score;
	}
	
	public void setScore(int score) {
		this.score = score;
	}
	
	public abstract int play();
	
}