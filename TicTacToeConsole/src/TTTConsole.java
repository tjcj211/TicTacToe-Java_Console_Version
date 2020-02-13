import java.util.Scanner;
/**
 * Tic-Tac-Toe: Two-player console, non-graphics
 * @author relkharboutly
 * @contributor Timothy Carta
 * @date 1/30/2020
 */
public class TTTConsole  {
                                                     
      public static Scanner in = new Scanner(System.in); // the input Scanner
 
   public static TicTacToe TTTboard = new TicTacToe();
   /** The entry main method (the program starts here) */
   public static void main(String[] args) {
	   int currentState = TicTacToe.PLAYING;
	   String userInput = "w";
	   //game loop
	   do {
         TTTboard.printBoard();
         // Print message if game-over
         currentState = TTTboard.checkForWinner();
         
         if (currentState == ITicTacToe.CROSS_WON) {
            System.out.println("'X' won! Bye!");
         } else if (currentState == ITicTacToe.NOUGHT_WON) {
            System.out.println("'O' won! Bye!");
         } else if (currentState == ITicTacToe.TIE) {
            System.out.println("It's a TIE! Bye!");
         }
         
         /**
          * get player input here and call setMove(). user should input a number between 0-8
          */
         if (!userInput.toLowerCase().equals("q")) { //Makes sure that the user is not exiting
        	 if (TTTboard.getCurrentPlayer() == 1) {
        		 userInput = in.next();
        		 TTTboard.setMove(1, Integer.valueOf(userInput));
        	 } else {
        		 int location = TTTboard.getComputerMove();
        		 TTTboard.setMove(0,location);
        	 }
         }
         
      } while ((currentState == ITicTacToe.PLAYING) && (!userInput.equals("q"))); // repeat if not game-over
   }
 
     
}