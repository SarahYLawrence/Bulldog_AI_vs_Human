# Experment Goal

The goal is to test 

## Bulldog Game Rules

Each player takes turns rolling a six-sided die with the following rules:

- If a 6 is rolled, the turn ends immediately and no points are gained.
- If any other number is rolled, it's added to the turn score.
  - The player can then choose to roll again or end their turn.
- If the player ends the turn, the accumulated turn score is added to their total score.
- The first player to reach 104 points wins the game.

The game implments difrent bot stragies that humans can play agenst.


## Bot Stratges

- HumanPlayer
Objects in the HumanPlayer class will print the cumulative score earned so far in this
turn, and ask the user to choose whether or not to end the turn. The object will use
System.in to read the user’s response from the keyboard.
- RandomPlayer
Objects in the RandomPlayer class will choose to continue or end the current turn
randomly, with a 50% probability for either choice.
- FifteenPlayer
Objects in the FifteenPlayer class will always choose to continue the turn until a
score of at least 15 is reached, at which point the object will choose to end the turn.
(Note that, of course, if a 6 is rolled, the turn ends with a score of 0.)
- UniquePlayer
You will design a unique strategy for the UniquePlayer class that differs from any
strategy described here.
