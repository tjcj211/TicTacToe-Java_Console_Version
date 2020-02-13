/**
 * TicTacToe class implements the interface
 * @author relkharboutly
 * @contributor Timothy Carta
 * @date 1/30/2020
 */
public class TicTacToe implements ITicTacToe {

	// The game board and the game status
	private static final int ROWS = 3, COLS = 3; // number of rows and columns
	private int[][] board = new int[ROWS][COLS]; // game board in 2D array
	private int HUMAN_PLAYER = 0;
	private int COMPUTER_PLAYER = 1;
	private int currentPlayer;

	/**
	 * clear board and set current player   
	 */
	public TicTacToe(){
		clearBoard();
		currentPlayer = 1;
	}

	@Override
	public void clearBoard() {
		for (int r = 0; r < ROWS; r++) {
			for (int c = 0; c < COLS; c++) {
				board[r][c] = EMPTY;
			}
		}
	}

	@Override
	public void setMove(int player, int location) {
		int row = location / 3; //Finds which row based upon location given
		int col = location % 3; //Finds which column based upon location given
		if (player == 0) {
			if (board[row][col] != NOUGHT && board[row][col] != CROSS) {
				board[row][col] = NOUGHT; //Place an O
				currentPlayer = 1;
			}
		}
		else if (player == 1){
			if (board[row][col] != NOUGHT && board[row][col] != CROSS) {
				board[row][col] = CROSS; //Place an X
				currentPlayer = 0;
			}
		}
	}

	//Check to see if the board is full by looking at all rows and columns
	public boolean isBoardFull() {
		for (int row = 0; row < ROWS; row++) {
			for (int col = 0; col < COLS; col++) {
				if (board[row][col] == EMPTY) {
					return false; //Empty space found
				}
			}
		}
		return true; //Board is full
	}

	@Override
	public int getComputerMove() { //Return the best move for the computer to make
		
		/**
		 * This will check the available spaces to see if the computer can 
		 * block a winning move for the human player
		 */
		if ((board[1][1] == CROSS && board[2][2] == CROSS ||
				board[0][1] == CROSS && board[0][2] == CROSS ||
				board[1][0] == CROSS && board[2][0] == CROSS) &&
				board[0][0] == EMPTY) {
			return 0; //Place the O at location 0
		} else if ((board[0][0] == CROSS && board[0][2] == CROSS ||
				board[1][1] == CROSS && board[2][1] == CROSS) &&
				board[0][1] == EMPTY) {
			return 1; //Place the O at location 1
		} else if ((board[0][0] == CROSS && board[0][1] == CROSS ||
				board[2][0] == CROSS && board[1][1] == CROSS ||
				board[1][2] == CROSS && board[2][2] == CROSS) &&
				board[0][2] == EMPTY) {
			return 2; //Place the O at location 2
		} else if ((board[0][0] == CROSS && board[2][0] == CROSS ||
				board[1][1] == CROSS && board[1][2] == CROSS) &&
				board[1][0] == EMPTY) {
			return 3; //Place the O at location 3
		} else if ((board[0][0] == CROSS && board[2][2] == CROSS ||
				board[0][1] == CROSS && board[2][1] == CROSS ||
				board[0][2] == CROSS && board[2][0] == CROSS ||
				board[1][0] == CROSS && board[1][2] == CROSS) &&
				board[1][1] == EMPTY) {
			return 4; //Place the O at location 4
		} else if ((board[0][2] == CROSS && board[2][2] == CROSS ||
				board[1][0] == CROSS && board[1][1] == CROSS) &&
				board[1][2] == EMPTY) {
			return 5; //Place the O at location 5
		} else if ((board[0][0] == CROSS && board[1][0] == CROSS ||
				board[1][1] == CROSS && board[0][2] == CROSS ||
				board[2][1] == CROSS && board[2][2] == CROSS) && 
				board[2][0] == EMPTY) {
			return 6; //Place the O at location 6
		} else if ((board[0][1] == CROSS && board[1][1] == CROSS ||
				board[2][0] == CROSS && board[2][2] == CROSS) &&
				board[2][1] == EMPTY) {
			return 7; //Place the O at location 7
		} else if ((board[0][0] == CROSS && board[1][1] == CROSS ||
				board[2][0] == CROSS && board[2][1] == CROSS ||
				board[0][2] == CROSS && board[1][2] == CROSS) &&
				board[2][2] == EMPTY) {
			return 8; //Place the O at location 8
		} else {
			int number = (int)(Math.random() * 9); //Pick a random spot on the board
			int r = number /3; //Calculate the row
			int c = number %3; //Calculate the column

			while (board[r][c] != EMPTY && !isBoardFull()) { //Make sure that the piece can be placed
				number = (int)(Math.random() * 8);
				r = number /3; //Calculate the row again
				c = number %3; //Calculate the column again
			}
			return number; //Place the O at a random location 
		}
	}
	public int getCurrentPlayer() { //Return the current player
		return currentPlayer;
	}

	@Override
	public int checkForWinner() {
		if (board[0][0] == NOUGHT && board[1][1] == NOUGHT && board[2][2] == NOUGHT || //DiagonalRight
				board[0][2] == NOUGHT && board[1][1] == NOUGHT && board[2][0] == NOUGHT || //DiagonalLeft
				board[0][0] == NOUGHT && board[0][1] == NOUGHT && board[0][2] == NOUGHT || //TopAcross
				board[1][0] == NOUGHT && board[1][1] == NOUGHT && board[1][2] == NOUGHT || //MidAcross
				board[2][0] == NOUGHT && board[2][1] == NOUGHT && board[2][2] == NOUGHT || //BotAcross
				board[0][0] == NOUGHT && board[1][0] == NOUGHT && board[2][0] == NOUGHT || //LeftDown
				board[0][1] == NOUGHT && board[1][1] == NOUGHT && board[2][1] == NOUGHT || //MidDown
				board[0][2] == NOUGHT && board[1][2] == NOUGHT && board[2][2] == NOUGHT) { //RightDown
			return NOUGHT_WON;
		}
		else if (board[0][0] == CROSS && board[1][1] == CROSS && board[2][2] == CROSS || //DiagonalRight
				board[0][2] == CROSS && board[1][1] == CROSS && board[2][0] == CROSS || //DiagonalLeft
				board[0][0] == CROSS && board[0][1] == CROSS && board[0][2] == CROSS || //TopAcross
				board[1][0] == CROSS && board[1][1] == CROSS && board[1][2] == CROSS || //MidAcross
				board[2][0] == CROSS && board[2][1] == CROSS && board[2][2] == CROSS || //BotAcross
				board[0][0] == CROSS && board[1][0] == CROSS && board[2][0] == CROSS || //LeftDown
				board[0][1] == CROSS && board[1][1] == CROSS && board[2][1] == CROSS || //MidDown
				board[0][2] == CROSS && board[1][2] == CROSS && board[2][2] == CROSS) { //RightDown
			return CROSS_WON;
		}
		else if (isBoardFull()) {
			return TIE; //Game is a tie
		}
		else{
			return PLAYING; //Nobody has won and the game continues
		}

	}

	/**
	 *  Print the game board 
	 */
	public  void printBoard() {
		for (int row = 0; row < ROWS; ++row) {
			for (int col = 0; col < COLS; ++col) {
				printCell(board[row][col]); // print each of the cells
				if (col != COLS - 1) {
					System.out.print("|");   // print vertical partition
				}
			}
			System.out.println();
			if (row != ROWS - 1) {
				System.out.println("-----------"); // print horizontal partition
			}
		}
		System.out.println();
	}

	/**
	 * Print a cell with the specified "content" 
	 * @param content either CROSS, NOUGHT or EMPTY
	 */
	public  void printCell(int content) {
		switch (content) {
		case EMPTY:  System.out.print("   "); break;
		case NOUGHT: System.out.print(" O "); break;
		case CROSS:  System.out.print(" X "); break;
		}
	}

}
