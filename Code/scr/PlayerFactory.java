import javax.swing.*;

/**
 * A factory class for creating different types of Player instances.
 */
public class PlayerFactory {

    /**
     * Creates and returns a specific type of Player based on the provided type string.
     *
     * @param type            the type of player to create ("Human", "Random", "Seven", or "Unique")
     * @param name            the name of the player
     * @param rollButton      the button associated with rolling the dice
     * @param rollLabel       the label showing the result of a roll
     * @param totalLabel      the label displaying the player's total score
     * @param turnTotalLabel  the label showing the current turn's total score
     * @param gui             the GameGUI instance to update game state and visuals
     * @param dice            the Dice object used by AI players to simulate rolls
     * @return                a new Player instance of the specified type
     * @throws IllegalArgumentException if the type is not recognized
     */
    public static Player createPlayer(
        String type,
        String name,
        JButton rollButton,
        JLabel rollLabel,
        JLabel totalLabel,
        JLabel turnTotalLabel,
        GameGUI gui,
        Dice dice
    ) {
        switch (type) {
            case "Human":
                return new HumanPlayer(name, rollButton, rollLabel, totalLabel, turnTotalLabel, gui);
            case "Random":
                return new RandomPlayer(name, rollButton, rollLabel, totalLabel, turnTotalLabel, gui, dice);
            case "Seven":
                return new SevenPlayer(name, rollButton, rollLabel, totalLabel, turnTotalLabel, gui, dice);
            case "Unique":
                return new UniquePlayer(name, rollButton, rollLabel, totalLabel, turnTotalLabel, gui, dice);
            default:
                throw new IllegalArgumentException("Unknown player type: " + type);
        }
    }
}
