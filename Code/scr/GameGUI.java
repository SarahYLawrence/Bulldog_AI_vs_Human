import java.awt.*;
import javax.swing.*;
import java.util.*;

/**
 * A Swing-based game setup with multiple screens for player selection,
 * name entry, player type selection, and the main game screen.
 */
public class GameGUI {
    private JFrame frame;
    private JPanel mainPanel;
    private CardLayout cardLayout;
    private JList<Integer> numberList;
    private int selectedNumber = -1; // Number of players
    private ArrayList<String> playerNames = new ArrayList<>();
    private Map<String, String> playerTypes = new HashMap<>();
    private int currentPlayerIndex = 0;
    private Referee referee;
    private GameStatus gameStatus;


    // Components for the second screen (Name entry)
    private JLabel playerPromptLabel;
    private JTextField nameField;
    private JButton enterButton;

    // Components for the third screen (Player type selection)
    private JLabel typePromptLabel;
    private JButton wimpButton, randomButton, humanButton, sevenButton, uniqueButton;

    // Components for the fourth screen (Game screen)
    private JLabel gameTitleLabel;
    private JLabel playerNameLabel;
    private JLabel totalLabel;
    private JLabel turnTotalLabel;
    private JLabel rollResultLabel;
    private JButton rollDiceButton;
    private JButton endTurnButton; // Button to end the current turn and add Turn Total to Total Score
    private JButton nextPlayerButton; // Button to go to the next player
    private Random random;
    
    // PlayerList model instance
    private PlayerList playerList;



    /**
    * Constructor to initialize the game setup and UI components.
    */
    public GameGUI() {        

        frame = new JFrame("Game Setup");
        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);
        random = new Random();
        playerList = new PlayerList(); // Initialize PlayerList
        referee = Referee.getInstance(playerList);
        gameStatus = new GameStatus(playerList);

        // ========== First Screen: Welcome and Player Selection ==========
        JPanel welcomeScreen = new JPanel(new BorderLayout());
        JLabel welcomeLabel = new JLabel("Welcome! Select the number of players:", SwingConstants.CENTER);

        Integer[] numbers = {1, 2, 3, 4, 5};
        numberList = new JList<>(numbers);
        numberList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        numberList.setCellRenderer(new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
                JLabel label = (JLabel) super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                label.setHorizontalAlignment(SwingConstants.CENTER);
                return label;
            }
        });
        
        JScrollPane scrollPane = new JScrollPane(numberList);
        scrollPane.setPreferredSize(new Dimension(100, 100));

        JButton nextButton = new JButton("Next");

        JPanel inputPanel = new JPanel();
        inputPanel.add(scrollPane);
        welcomeScreen.add(welcomeLabel, BorderLayout.NORTH);
        welcomeScreen.add(inputPanel, BorderLayout.CENTER);
        welcomeScreen.add(nextButton, BorderLayout.SOUTH);

        // ========== Second Screen: Name Entry ==========
        JPanel secondScreen = new JPanel(new BorderLayout());
        playerPromptLabel = new JLabel("What is player 1's name?", SwingConstants.CENTER);
        nameField = new JTextField(15);
        enterButton = new JButton("Enter");

        JPanel inputNamePanel = new JPanel();
        inputNamePanel.add(playerPromptLabel);
        inputNamePanel.add(nameField);
        inputNamePanel.add(enterButton);
        secondScreen.add(inputNamePanel, BorderLayout.CENTER);

        // ========== Third Screen: Player Type Selection ==========
        JPanel thirdScreen = new JPanel(new BorderLayout());
        typePromptLabel = new JLabel("", SwingConstants.CENTER);
        wimpButton = new JButton("Wimp");
        randomButton = new JButton("Random");
        humanButton = new JButton("Human");
        sevenButton = new JButton("Seven");
        uniqueButton = new JButton("Unique");
        
        JPanel buttonPanel = new JPanel(new GridLayout(3, 2));
        buttonPanel.add(humanButton);
        buttonPanel.add(wimpButton);
        buttonPanel.add(randomButton);
        buttonPanel.add(sevenButton);
        buttonPanel.add(uniqueButton);
        thirdScreen.add(typePromptLabel, BorderLayout.NORTH);
        thirdScreen.add(buttonPanel, BorderLayout.CENTER);

        // ========== Fourth Screen: Game Screen ==========
        JPanel gameScreen = new JPanel(new GridLayout(6, 1));  // Increased grid rows for new Turn Total label
        gameTitleLabel = new JLabel("Game Screen", SwingConstants.CENTER);
        playerNameLabel = new JLabel("", SwingConstants.CENTER);
        totalLabel = new JLabel("Total: 0", SwingConstants.CENTER);
        turnTotalLabel = new JLabel("Turn Total: 0", SwingConstants.CENTER); // New Turn Total label
        rollResultLabel = new JLabel("Roll: --", SwingConstants.CENTER);
        rollDiceButton = new JButton("Roll Dice");
        endTurnButton = new JButton("End Turn"); // New button to finalize the turn and add to Total Score
        nextPlayerButton = new JButton("Next Player"); // New button for next player

        gameScreen.add(gameTitleLabel);
        gameScreen.add(playerNameLabel);
        gameScreen.add(totalLabel);
        gameScreen.add(turnTotalLabel);  // Add Turn Total label
        gameScreen.add(rollResultLabel);
        gameScreen.add(rollDiceButton);
        gameScreen.add(endTurnButton); // Add End Turn button
        gameScreen.add(nextPlayerButton); // Add Next Player button

        // ========== Win Screen ==========
        JPanel winScreen = new JPanel(new BorderLayout());
        JLabel winnerLabel = new JLabel("", SwingConstants.CENTER); 
       
        winScreen.add(winnerLabel, BorderLayout.CENTER); 

        // ========== Add screens to CardLayout ==========
        mainPanel.add(welcomeScreen, "WelcomeScreen");
        mainPanel.add(secondScreen, "SecondScreen");
        mainPanel.add(thirdScreen, "ThirdScreen");
        mainPanel.add(gameScreen, "GameScreen");
        mainPanel.add(winScreen, "WinScreen"); 

       // ========== View Scoreboard Button ==========
       JButton viewScoreboardButton = new JButton("View Scoreboard");
       gameScreen.add(viewScoreboardButton);

       viewScoreboardButton.addActionListener(e -> {
           ScoreboardViewer scoreboardViewer = new ScoreboardViewer(playerList); // Pass PlayerList
           JPanel scoreboardPanel = new JPanel();
           scoreboardPanel.add(scoreboardViewer);
           mainPanel.add(scoreboardPanel, "ScoreboardScreen");
           cardLayout.show(mainPanel, "ScoreboardScreen");
       });
        
        
        // ========== Button Actions ==========
        nextButton.addActionListener(e -> {
            selectedNumber = numberList.getSelectedValue() != null ? numberList.getSelectedValue() : -1;
            if (selectedNumber == -1) {
                JOptionPane.showMessageDialog(frame, "Please select a number before proceeding.");
            } else {
                playerNames.clear();
                playerTypes.clear();
                currentPlayerIndex = 0;
                updatePrompt();
                cardLayout.show(mainPanel, "SecondScreen");
            }
        });

        enterButton.addActionListener(e -> {
            String playerName = nameField.getText().trim();
            if (!playerName.isEmpty()) {
                playerNames.add(playerName);
                nameField.setText("");
                currentPlayerIndex++;

                if (currentPlayerIndex < selectedNumber) {
                    updatePrompt();
                } else {
                    currentPlayerIndex = 0;
                    updateTypePrompt();
                    cardLayout.show(mainPanel, "ThirdScreen");
                }
            } else {
                JOptionPane.showMessageDialog(frame, "Please enter a name.");
            }
        });
        

        humanButton.addActionListener(e -> {
            playerList.addPlayer(new HumanPlayer(
                playerNames.get(currentPlayerIndex), 
                rollDiceButton, 
                rollResultLabel,
                totalLabel,
                turnTotalLabel,
                this
            ));
            nextPlayerType();
        });

        sevenButton.addActionListener(e -> {
            playerList.addPlayer(new SevenPlayer(
                playerNames.get(currentPlayerIndex), 
                rollDiceButton, 
                rollResultLabel,
                totalLabel,
                turnTotalLabel,
                this,
                new Dice(6)
            ));
            nextPlayerType();
        });

        randomButton.addActionListener(e -> {
            playerList.addPlayer(new RandomPlayer(
                playerNames.get(currentPlayerIndex), 
                rollDiceButton, 
                rollResultLabel,
                totalLabel,
                turnTotalLabel,
                this,
                new Dice(6)
            ));
            nextPlayerType();
        });

        uniqueButton.addActionListener(e -> {
            playerList.addPlayer(new UniquePlayer(
                playerNames.get(currentPlayerIndex), 
                rollDiceButton, 
                rollResultLabel,
                totalLabel,
                turnTotalLabel,
                this,
                new Dice(6)
            ));
            nextPlayerType();
        });
        
        endTurnButton.addActionListener(e ->  {        
                // End the current player's turn
                getCurrentPlayer().endTurn();

                // Check if the game is over
                if (gameStatus.isGameOver()) {
                    Player winner = getCurrentPlayer();
                    if (winner instanceof HumanPlayer) {
                        winnerLabel.setText(winner.getName() + " Wins!");
                        cardLayout.show(mainPanel, "WinScreen");
                    } else {
                        JOptionPane.showMessageDialog(frame, winner.getName() + " (bot) wins the game.");
                    }
                } else {
                    // If game isn't over, reset the board and move to next turn
                    rollResultLabel.setText("Roll: --");
                    turnTotalLabel.setText("Turn Total: 0");
                    totalLabel.setText("Total: " + getCurrentPlayer().getScore());
                    updateButtonStates(true, false, true);  // Enable the Roll and Next Player buttons
                    nextPlayerButton.setEnabled(true);
                }
        
                // Disable buttons after turn is ended
                rollDiceButton.setEnabled(false);
                endTurnButton.setEnabled(false);
            });

        // Setup frame
        frame.add(mainPanel);
        frame.setSize(400, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

        nextPlayerButton.addActionListener(e -> {
            referee.nextPlayer();
            updateGameScreen();
        });
    }

    /**
     * Updates the name prompt for player name entry.
     */
    private void updatePrompt() {
        playerPromptLabel.setText("What is player " + (currentPlayerIndex + 1) + "'s name?");
    }

    /**
     * Updates the type selection prompt for each player.
     */
    private void updateTypePrompt() {
        typePromptLabel.setText(playerNames.get(currentPlayerIndex) + ", choose your type:");
    }

    /**
     * Moves to the next player type selection or transitions to the game screen.
     */
    private void nextPlayerType() {
        currentPlayerIndex++;
        if (currentPlayerIndex < selectedNumber) {
            updateTypePrompt();
        } else {
            currentPlayerIndex = 0;
            updateGameScreen();
            cardLayout.show(mainPanel, "GameScreen");
        }
    }

     /**
     * Updates the buttons on the game screen.
     */
    private void updateButtonStates(boolean rollEnabled, boolean endTurnEnabled, boolean nextPlayerEnabled) {
        rollDiceButton.setEnabled(rollEnabled);
        endTurnButton.setEnabled(endTurnEnabled);
        nextPlayerButton.setEnabled(nextPlayerEnabled);
    }

    public Player getCurrentPlayer() {
        return referee.getCurrentPlayer();
    }

    /**
     * Updates the game screen with the current player's information.
     */
    private void updateGameScreen() {
        Player currentPlayer = referee.getCurrentPlayer();
    
        playerNameLabel.setText(currentPlayer.getName() + "'s Turn");
        totalLabel.setText("Total: " + currentPlayer.getScore());
        turnTotalLabel.setText("Turn Total: " + currentPlayer.getTurnTotal());
    
        updateButtonStates(false, false, false);
    
        if (currentPlayer instanceof HumanPlayer) {
            updateButtonStates(true, true, false);
        }
        if (currentPlayer instanceof SevenPlayer) {
            updateButtonStates(true, true, false);
        }
        if (currentPlayer instanceof RandomPlayer) {
            updateButtonStates(true, true, false);
        }
        if (currentPlayer instanceof UniquePlayer) {
            updateButtonStates(true, true, false);
        }
    }
     
     /**
     * Main method to launch the application.
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(GameGUI::new);
    }
}
