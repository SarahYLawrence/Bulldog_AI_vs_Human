import javax.swing.*;

public class PlayerFactory {
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
