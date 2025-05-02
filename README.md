# Bulldog Improvments

## Purpose 
The purpose of this assignment is to improving the existing code base without changing functionality. 

## Instructions 
**Code**
1. Get rid of "104". The score necessary to win the game was chosen as 104. (Why?) The vast majority of Program 1 submissions embedded this value "deep" in the code, often in multiple locations. This is poor style because it makes it difficult to change the winning score of the game. For this improvment, the codebase should contain a constant named something like WINNING_SCORE which is (initially) set to 104. All other classes should reference the constant and not a specific number.
2. Consolidate randomness. Again, the majority of Program 1 submissions repeatedly created random number generators and invoked them on the fly. Random number generators are tricky things and it is best to isolate random number generators from the rest of the codebase whenever possible. Create a new class Dice that contains only one random number generator; the keyword 'static' is your friend, here. The class should have a constructor that takes N, the number of sides of the Dice object. The class should contain a public method roll() which produces a random number from 1 to N. Thus, new Dice(6) would create a standard six-sided die while new Dice(2) would effectively create a coin. Have the AI tool create such a class and then ensure that it is used throughout your code base whenever a random number is needed. Player objects can create their own Dice objects as needed; making them instance variables is probably efficient.
3. Javadoc. Try to get the AI tool to document all of the code in Javadoc style.
4. File structure. ALL of the Program 1 submissions (correctly) had multiple classes in multiple files (generally one class per file.) MANY of the Program 2 submissions had multiple classes in a single file (usually named Prog6.java.) Prompt the AI tool to reorganize the files along the lines of Program 1  
  
**Documentation**  
Your "lab book" should include four sections, one for each improvment. In each case, you should describe your initial prompts and evaluate how well the tool did - and how far you could get before it became "necessary" to finish the improvement by hand. Finally present some general (and preliminary) conclusions about how well AI tools can help with refactoring.


## Result
**Code Results**  
(This project was done wrong I was suposed to use the prior Java swing files but I accidentally used program 2 instead)  
Run: Code -> Prog1.java  
**Written Results**  
Documentation -> Documentation5.txt 






