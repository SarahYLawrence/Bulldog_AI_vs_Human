# Random Singleness 

## Purpose 
The purpose of this assignment is to give you experience with the Strategy design pattern, and to further study how well AI tools can complete certain programming tasks.

## Instructions 
**Code**
1. Create a (small?) class to encapsulate the status of the game. An object of this class was shown in the pseudo-code of the play() method and had the name gameStatus. This should be a relatively simple class.
2. Refactor the play method in the Player class. The previously discussed pseudo-code was an attempt to refactor the various play() methods by moving common (duplicated) code from the concrete Player classes into the abstract class Player. This refactoring also involved creating a new method that provided each Player's decision about whether or not to continue rolling in this round. Testing (even informally) this change will involve writing at least one implementation of the continue method in a concrete Player class. [Most programmers would suggest that the HumanPlayer class would be a poor choice for this initial test.]
3. Complete the continue methods in the other classes. Once again, consider completing the HumanPlayer after the other ones. For every ...Player class, include the (labeled) code for the continue method with your lab book.
4. Javadoc. Ensure that all of your code should be documented in Javadoc style. For authorship credit, include something like, "Written by David Levine, with the assistance of Robby the Robot." {Substitute the name of the AI tool you used for 'Robby the Robot.' Yes, it's probably easier to add that line by hand than to prompt for it - especially if you do so at the end of the assignment.

**Documentation**  
Your "lab book" should include the discussion described at the beginning of the assignment section as well as all of the code snippets for the continue methods.

## Result
**Code Results**  
Run: Code -> GameGUI.java  
**Written Results**  
Documentation -> Documentation8.txt 

