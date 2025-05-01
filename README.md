# AI and Human Experment 

## Goal
The goal is to evaluate weather AI is better at assisting humans with coding or harming. This expremnet was condicted with the game bulldog. The bulldog game over each experment was added too to challange the team work of the AI and human as the code gained more complexity.  

## Technologies
- JUnit - For unit testing 

## Getting Started
- In the experment VS code: Java version 22 was used
- Recomended: Java versions 8,11,or 17 (better for Junit & Java swing)

## Installation
1. Clone the repository:
   ```bash
   git clone https://github.com/SarahYLawrence/Bulldog_AI_vs_Human.git
   
## Visual Studio Code Setup
- VS code install: https://code.visualstudio.com/
- Open VS Code, and go to the Extensions view (four boxes on the left hand side)
- Install the "Extension Pack for Java"
- preform Installation step 

## About
### Bulldog Game
**Bulldog Rules:**
Each player takes turns rolling a six-sided die with the following rules:

- If a 6 is rolled, the turn ends immediately and no points are gained.
- If any other number is rolled, it's added to the turn score.
  - The player can then choose to roll again or end their turn.
- If the player ends the turn, the accumulated turn score is added to their total score.
- The first player to reach 104 points wins the game.

**Bulldog Bot Stratges**
The game has diffrent player/bot strategies that can be played.
- **HumanPlayer:**
Objects in the HumanPlayer class will print the cumulative score earned so far in this
turn, and ask the user to choose whether or not to end the turn. The object will use
System.in to read the user’s response from the keyboard.
- **RandomPlayer:**
Objects in the RandomPlayer class will choose to continue or end the current turn
randomly, with a 50% probability for either choice.
- **FifteenPlayer:**
Objects in the FifteenPlayer class will always choose to continue the turn until a
- **UniquePlayer:**
You will design a unique strategy for the UniquePlayer class that differs from any
strategy described here.

### Experments:
This project includes a series of experiments exploring human and AI assistance:

**Program One** – Tests only the human's coding abilities.

**Program Two** – Tests only AI's coding abilities.

**Program Three** – Compares AI and human coding abilities.

**Program Four** – Human with no prior knowledge uses AI to implement a Java Swing interface.

**Program Five** – Human uses AI assistance to improve the Bulldog game.

**Program Six** – Human uses AI to implement the Model-View-Controller (MVC) design pattern.

**Program Seven** – Human uses AI to implement the Singleton design pattern.

**Program Eight** – Human uses AI to implement the Strategy design pattern.
