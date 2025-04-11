/**
 * ********************************************************
 * Sarah Lawrence
 * Login ID: sarah.lawrence@maine.edu
 * COS 420, spring 2025
 * Programming Assignment 1
 * abstract Player class: The game Bulldog
 * ********************************************************
 */

 import java.util.Scanner;
 import java.util.ArrayList;
 
 public class Prog1 {
 
     /**
      * Displays information about each subclass and what they do.
      */
     private static void playersInfo () {
         System.out.println("Players: (type whats in the parenthesis to pick) \n" +
             "(Human): You choose whether or not to end the turn.\n" +
             "(Fifteen):  Will always choose to continue the turn until a score of at least 15.\n" +
             "(Random): Will choose to continue or end the current turn randomly, with a 50% probability\n" +
             "(Unique): Will flip a coin if heads it can only roll high and won't continue if tails rolls low and continues\n" +
             "(Wimp): Will always choose to end their turn after the first roll");      
     }
 
     /**
      * Creates players based on user input.
      * @param num Number of players
      * @param scanner Scanner object for user input
      * @return ArrayList of Player objects
      */
     private static ArrayList<Player> makePlayers (int num, Scanner scanner) {
         ArrayList<Player> arrayList= new ArrayList<Player>();
         for (int i = 1; i <= num; i++){
             System.out.println("Player " + i + " What player would you like? (Type (Player_Info) to see the options)");
             
             boolean continue_g = true;
             
             while(continue_g){
                 String name = scanner.nextLine();
                 if (name.equals("Player_Info")){
                     playersInfo ();
                 }
                 else if (name.equals("Human")){
                     System.out.println("Player " + i + " has picked to be a human player.");
                     arrayList.add(new HumanPlayer("Player " + i));
                     continue_g = false;
                 }
                 else if (name.equals("Fifteen")){
                     System.out.println("Player " + i + " has picked to be a fifteen player.");
                     arrayList.add(new FifteenPlayer("Player " + i));
                     continue_g = false;
                 }
                 else if (name.equals("Random")){
                     System.out.println("Player " + i + " has picked to be a random player.");
                     arrayList.add(new RandomPlayer("Player " + i));
                     continue_g = false;
                 }
                 else if (name.equals("Unique")){
                     System.out.println("Player " + i + " has picked to be a unique player.");
                     arrayList.add(new UniquePlayer("Player " + i));
                     continue_g = false;
                 }
                 else if (name.equals("Wimp")){
                     System.out.println("Player " + i + " has picked to be a wimp player.");
                     arrayList.add(new WimpPlayer("Player " + i));
                     continue_g = false;
                 }
                 else {
                     System.out.println(" That dosnt work please try again. If you'd like to know the options type (Player)");
                 }
             }
          
         }
         return arrayList;
     }
 
     /**
      * Starts and manages the game loop.
      * @param arrayList List of players in the game
      */
     private static void playGame (ArrayList<Player> arrayList) {
         System.out.println("Let's Play!");
         boolean num_play_correct = true;
         int j = 1;
         int score;
         
         while(num_play_correct){
             
             for (int i = 0; i < arrayList.size(); i++) {
                 if (num_play_correct != false){
                     score = arrayList.get(i).play();
                     arrayList.get(i).setScore(score);
                     // End of game condition
                     if (arrayList.get(i).getScore() >= Player.WINNING_SCORE){
                         System.out.println( "\nPlayer " + arrayList.get(i).getName() + " you win! Score: " + arrayList.get(i).getScore());
                         num_play_correct = false;
                     }
                 }
             }
             if (num_play_correct == true){
                 System.out.println("Round " + j++ + " over!");
             }
         }
     }
 
     /**
      * Main method to run the Bulldog game.
      * @param args Command-line arguments
      */
     public static void main(String[] args) {  
         Scanner scanner = new Scanner(System.in);
         boolean num_play_correct = true;
         ArrayList<Player> arrayList= new ArrayList<Player>();
 
         System.out.println("Welcome to Bulldog!");
                 
         while(num_play_correct){
             System.out.println("How many players will be playing? ");
             String number = scanner.nextLine();
             try {
                 int num = Integer.parseInt(number);
                 num_play_correct = false;
                 
                 arrayList = makePlayers(num,scanner);
                 playGame(arrayList);
                 scanner.close(); 
             }
             catch (Exception e){
                 System.out.println("Not a number please try again.");
             }
         }
     }
 }