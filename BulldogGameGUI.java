import java.awt.*;
import javax.swing.*;
import java.util.*;

/**
 * A Swing-based game setup with multiple screens for player selection,
 * name entry, player type selection, and the main game screen.
 */
public class BulldogGameGUI {
    private JFrame frame;
    private JPanel mainPanel;
    private CardLayout cardLayout;
    private JList<Integer> numberList;
    private int selectedNumber = -1; // Number of players
    private ArrayList<String> playerNames = new ArrayList<>();
    private ArrayList<Player> players = new ArrayList<>(); // Store players
    private Map<String, String> playerTypes = new HashMap<>();
    private int currentPlayerIndex = 0;

    // Player game data
    private int[] playerScores;
    private int[] playerTurns;
    private int[] turnTotals;  // To track the sum of rolls for the current turn

    // Components for the second screen (Name entry)
    private JLabel playerPromptLabel;
    private JTextField nameField;
    private JButton enterButton;

    // Components for the third screen (Player type selection)
    private JLabel typePromptLabel;
    private JButton wimpButton, randomButton, humanButton, fifteenButton, uniqueButton;

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
    
    /**
    * Constructor to initialize the game setup and UI components.
    */
    public BulldogGameGUI() {
        frame = new JFrame("Game Setup");
        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);
        random = new Random();

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
        fifteenButton = new JButton("Fifteen");
        uniqueButton = new JButton("Unique");
        
        JPanel buttonPanel = new JPanel(new GridLayout(3, 2));
        buttonPanel.add(humanButton);
        buttonPanel.add(wimpButton);
        buttonPanel.add(randomButton);
        buttonPanel.add(fifteenButton);
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

        // ========== Add screens to CardLayout ==========
        mainPanel.add(welcomeScreen, "WelcomeScreen");
        mainPanel.add(secondScreen, "SecondScreen");
        mainPanel.add(thirdScreen, "ThirdScreen");
        mainPanel.add(gameScreen, "GameScreen");

        // ========== Button Actions ==========
        nextButton.addActionListener(e -> {
            selectedNumber = numberList.getSelectedValue() != null ? numberList.getSelectedValue() : -1;
            if (selectedNumber == -1) {
                JOptionPane.showMessageDialog(frame, "Please select a number before proceeding.");
            } else {
                playerNames.clear();
                playerTypes.clear();
                playerScores = new int[selectedNumber];
                playerTurns = new int[selectedNumber];
                turnTotals = new int[selectedNumber];  // Initialize turnTotals array
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
            players.add(new HumanPlayer(playerNames.get(currentPlayerIndex)));
            nextPlayerType();
        });
        wimpButton.addActionListener(e -> {
            // Define logic for Wimp player (you can implement as Random or custom type)
            players.add(new RandomPlayer(playerNames.get(currentPlayerIndex)));
            nextPlayerType();
        });
    
        randomButton.addActionListener(e -> {
            players.add(new RandomPlayer(playerNames.get(currentPlayerIndex)));
            nextPlayerType();
        });
    
        fifteenButton.addActionListener(e -> {
            players.add(new FifteenPlayer(playerNames.get(currentPlayerIndex)));
            nextPlayerType();
        });
    
        uniqueButton.addActionListener(e -> {
            players.add(new UniquePlayer(playerNames.get(currentPlayerIndex)));
            nextPlayerType();
        });
        
        rollDiceButton.addActionListener(e -> {
            int roll = random.nextInt(6) + 1; // Roll 1 to 6
            turnTotals[currentPlayerIndex] += roll;
            rollResultLabel.setText("Roll: " + roll);
            turnTotalLabel.setText("Turn Total: " + turnTotals[currentPlayerIndex]); // Update Turn Total
        });

        endTurnButton.addActionListener(e -> {
            Player currentPlayer = players.get(currentPlayerIndex);
        
            // If the player rolled a 6, their turn ends immediately and they score nothing
            if (turnTotals[currentPlayerIndex] == 6) {
                // Reset turn total to 0 and update the UI
                turnTotals[currentPlayerIndex] = 0;
                turnTotalLabel.setText("Turn Total: 0");
                
                // Disable buttons after rolling a 6
                rollDiceButton.setEnabled(false);
                endTurnButton.setEnabled(false);
        
                // Show message and proceed to next player
                JOptionPane.showMessageDialog(frame, "You rolled a 6! Your turn ends now. Proceeding to next player.");
                
                // Mark the turn as completed
                playerTurns[currentPlayerIndex] = 1;  // Turn ended
            } else {
                // Add the accumulated turn score to the player's overall score
                playerScores[currentPlayerIndex] += turnTotals[currentPlayerIndex];
                totalLabel.setText("Total: " + playerScores[currentPlayerIndex]);
        
                // Reset the turn total and update the UI
                turnTotals[currentPlayerIndex] = 0;
                turnTotalLabel.setText("Turn Total: 0");
        
                // Disable Roll Dice and End Turn buttons
                rollDiceButton.setEnabled(false);
                endTurnButton.setEnabled(false);
        
                // Mark the turn as completed
                playerTurns[currentPlayerIndex] = 1;  // Turn ended
            }
            
            // Enable the Next Player button
            nextPlayerButton.setEnabled(true);
        });
        nextPlayerButton.addActionListener(e -> {
            // Only proceed if the current player's turn is marked as ended
            if (playerTurns[currentPlayerIndex] == 1) {
                // Move to the next player
                currentPlayerIndex = (currentPlayerIndex + 1) % selectedNumber;
                updateGameScreen();
                
                // Enable Roll Dice and End Turn buttons for the next player
                rollDiceButton.setEnabled(true);
                endTurnButton.setEnabled(true);
                turnTotalLabel.setText("Turn Total: 0");
        
                // Reset the player's turn status
                playerTurns[currentPlayerIndex] = 0;
        
                // Update game screen for the next player
                updateGameScreen();
            } else {
                // If the player hasn't ended their turn, show a message
                JOptionPane.showMessageDialog(frame, "Please end your turn before proceeding.");
            }
        });
        // Setup frame
        frame.add(mainPanel);
        frame.setSize(400, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
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

    private void startNonHumanTurn(Player currentPlayer) {
        new Thread(() -> {
            int turnRollTotal = 0;
            String rollResultText = "Rolls: ";
            int roll;
    
            // Simulate dice rolls for non-human players (e.g., RandomPlayer)
            while (turnRollTotal < 6) {
                roll = random.nextInt(6) + 1; // Roll between 1 and 6
                turnRollTotal += roll;
                rollResultText += roll + " ";
                if (roll == 6) {
                    break; // Stop rolling if a 6 is rolled
                }
                try {
                    Thread.sleep(1000); // Wait for 1 second before the next roll (simulate rolling time)
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
    
            // Update turn totals and display the result
            turnTotals[currentPlayerIndex] = turnRollTotal;
            rollResultLabel.setText(rollResultText); // Display the rolled values
            turnTotalLabel.setText("Turn Total: " + turnRollTotal);
    
            // Automatically end the turn and display total
            currentPlayer.endTurn();
            totalLabel.setText("Total: " + currentPlayer.getScore());
    
            // Mark the turn as completed
            playerTurns[currentPlayerIndex] = 1; // Indicate turn is over
            updateGameScreen(); // Refresh the screen
        }).start();
    }
    
    /**
     * Updates the game screen with the current player's information.
     */
    private void updateGameScreen() {
        playerNameLabel.setText(playerNames.get(currentPlayerIndex) + "'s Turn");
        totalLabel.setText("Total: " + playerScores[currentPlayerIndex]);
        turnTotalLabel.setText("Turn Total: " + turnTotals[currentPlayerIndex]); // Display Turn Total
    
        Player currentPlayer = players.get(currentPlayerIndex);
        
        // Show Roll Dice and End Turn buttons only for Human players
        if (currentPlayer instanceof HumanPlayer) {
            rollDiceButton.setEnabled(true);
            endTurnButton.setEnabled(true);
            nextPlayerButton.setEnabled(false); // Disable next player button until the turn ends
        } else {
            // Automatically handle non-human turns (e.g., RandomPlayer)
            rollDiceButton.setEnabled(false);
            endTurnButton.setEnabled(false);
            nextPlayerButton.setEnabled(true); // Allow next player to be selected after the automated turn
            startNonHumanTurn(currentPlayer);
        }
    }    

     /**
     * Main method to launch the application.
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(BulldogGameGUI::new);
    }
}