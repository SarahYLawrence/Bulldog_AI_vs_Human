# Models and Mimickry

## Purpose 
The purpose of this assignment is to gain experience with the principles of separation inherent in the Model-View-Controller design pattern. 

## Instructions 
**Code**
1. Build a Model. You are to design a class that encapsulates an ArrayList of Players. This class should enable a client to add a Player, to find out the name or score of a Player (by giving the index of that Player in the list) and to set the score of a Player (by providing the index of that Player and the new score value.) Your first attempt at writing this code should make use of an AI tool, but feel free to make modifications by hand if the tool gets close to what you need and prompting it For revisions/enhancements/corrections would be noticeably harder than making those modifications by hand. Your class should also have other data and methods to allow it to function in an "MVC world." Mimicing the class example is a good idea here. "
2. Build a Viewer. This class encapsulates the idea of a Viewer for the Model - or in terms of our game, a Scoreboard. In terms of the example from lecture, this class is similar to the BarViewer and TextViewer classes. It differs from those classes, however in that it shows multiple items (one name and one score per Player) rather than a single piece of data. To demonstrated that your program works, you will need to write a main method that populates a "game roster" (including both names and scores) and then display the values; your main program should tben put up an OK-only JOptionPane dialog (to cause a pause) before changing the score of a Player. If all works as it should, clicking OK in the dialog will cause the Model to change, triggering a (visible) change in the Viewer/Scoreboard. A with Step 1, at least your initial attempt here must use your AI tool; it might be best if you repeated use of that tool until you got "close" to the final version of the class.
3. Integration into Bulldog. Change Bulldog to use your Model rather than a specific ArrayList of Players. {This is probably an easy task for the AI tool.) Then integrate the Scoreboard into your Bulldog GUI. Note that even if everything works, when you play two non-human Players against each other, the updates will happen so fast that you will probably only see the final score. BUT, if you play, say, a FifteenPlayer against a HumanPlayer, then the pauses while the HumanPlayer decides whether or not to continue will enable you to see changes at a more leisurely pace.
4. Javadoc. All of your code should be documented in Javadoc style. For authorship credit, include something like, "Written by David Levine, with the assistance of Robby the Robot." Yes, it's probably easier to add that line by hand than to prompt for it - especially if you do so at the end of the assignment.  
  
**Documentation**  
Your "lab book" should include three sections, one for each step above (ignoring Step 4.) In each case, you should describe your initial prompts and evaluate how well the tool did - and how far you could get before it became "necessary" to finish the improvement by hand. When appropriate, you should include screen snipshots. Include at least one snipshot from Step 3, more if it helps the exposition. Finally present some general (and preliminary) conclusions about how well AI tools can help with design patterns and/or code mimickry.

## Result
**Code Results**  
Run: Code -> BulldogGameGUI.java  
**Written Results**  
Documentation -> Documentation6.txt 







