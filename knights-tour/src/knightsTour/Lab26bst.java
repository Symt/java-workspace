package knightsTour;

//Lab26bst.java

//This is the student version of the knight's tour lab assignment.
import java.util.Scanner;

public class Lab26bst {
	public static void main(String args[]) {
		System.out.println("\nLab26b 90/100 Point Version\n");
		Knight knight = new Knight();
		knight.getStart();
		knight.solveTour();
		knight.displayBoard();
	}
}

class Knight {
	private int board[][]; // stores the sequence of knight moves
	private int startRow; // row location where the knight starts
	private int startCol; // col location where the knight starts
	private int rowPos; // current row position of the knight
	private int colPos; // current col position of the knight
	private int moves; // number of location visited by the knight
	private int[][] possibleMoves = { { 2, 1 }, { 1, 2 }, { -1, 2 }, { -2, 1 }, { -2, -1 }, { -1, -2 }, { 1, -2 },
			{ 2, -1 } }; // stores all possible moves
	final private int ACCESS[][] = {{0,0,0,0,0,0,0,0,0,0,0,0},
	 			{0,0,0,0,0,0,0,0,0,0,0,0},
	 			{0,0,2,3,4,4,4,4,3,2,0,0},
	 			{0,0,3,4,6,6,6,6,4,3,0,0},
	 			{0,0,4,6,8,8,8,8,6,4,0,0},
	 			{0,0,4,6,8,8,8,8,6,4,0,0},
	 			{0,0,4,6,8,8,8,8,6,4,0,0},
	 			{0,0,4,6,8,8,8,8,6,4,0,0},
	 			{0,0,3,4,6,6,6,6,4,3,0,0},
	 			{0,0,2,3,4,4,4,4,3,2,0,0},
	 			{0,0,0,0,0,0,0,0,0,0,0,0},
	 			{0,0,0,0,0,0,0,0,0,0,0,0}};
	
	/**
	 * Initializes board
	 */
	public Knight()
	{
		board = new int[8][8];
	}

	/**
	 * Asks for and validates the start location for the board. This works for both the 90% and 100%
	 */
	public void getStart()
	{
		Scanner sc = new Scanner(System.in); // scanner
		int row = 0, col = 0; // initialize

		/*
		 * Checks if integer
		 */
		String preValidate = "";
		while (!(validate(preValidate).intValue() >= 0 && validate(preValidate).intValue() <= 7
				&& validate(preValidate) instanceof Integer)) {
			System.out.print("Enter Row Start: ");
			preValidate = sc.nextLine();
			row = validate(preValidate).intValue();
		}
		preValidate = "";
		while (!(validate(preValidate).intValue() >= 0 && validate(preValidate).intValue() <= 7
				&& validate(preValidate) instanceof Integer)) {
			System.out.print("Enter Column Start: ");
			preValidate = sc.nextLine();
			col = validate(preValidate).intValue();
		}

		startRow = row;
		startCol = col;
		rowPos = startRow;
		colPos = startCol;
		sc.close(); // I close my scanners
	}

	/**
	 * Iterates over each item and prints it out
	 */
	public void displayBoard() {
		for (int i = 0; i < board.length; i++) {
			for (int k = 0; k < board[i].length; k++) {
				System.out.print(padX(board[i][k]) + " ");
			}
			System.out.println();
		}
		System.out.println();
		System.out.println("The knight has toured " + ((moves != 64) ? moves-1 : 64)  + " locations.");
	}

	/**
	 * Checks if 2 numbers are in range of 0 - 8
	 * @param x next row
	 * @param y next col
	 * @return boolean - If in range or not
	 */
	private boolean boringCheck(int x, int y) {
		if (x >= 0 && y >= 0 && x < 8 && y < 8) {
			return board[x][y] == 0;
		}
		return false;
	}

	
	/**
	 * Recursive backtracking for 90% method. Corners are the only ones that run in a decent time. All others take forever
	 * @param row previous row + possibleMoves[i][0]
	 * @param col previous col + possibleMoves[i][0]
	 * @param m moves - uses separate variable because bugs (see below)
	 * @return 
	 */
	private boolean getMove(int row, int col, int m)
	{
		moves = m;
		int nextR, nextC;
		if (m == 64) {
			for (int i = 0; i < possibleMoves.length; i++) { // goes over every possible knight move and checks if it works
				nextR = row + possibleMoves[i][0];
				nextC = col + possibleMoves[i][1];
				if (boringCheck(nextR, nextC)) { // Out of bounds check
					board[nextR][nextC] = 64; // If it isn't, sets it to max because m == 64
					return true;
				}
			}
		}
		for (int i = 0; i < possibleMoves.length; i++) { // goes over every possible knight move and checks if it works
			nextR = row + possibleMoves[i][0];
			nextC = col + possibleMoves[i][1];
			if (boringCheck(nextR, nextC)) { // Out of bounds check
				board[nextR][nextC] = m; // Setting the item
				if (getMove(nextR, nextC, m + 1)) { // Recursion -- Keeps going down the path until it hits a dead end or finds solution
					// Because of the way backtracking works, using a separate variable (m) is necessary. It has to be local, not global
					rowPos = nextR;
					colPos = nextC;
					return true;
				} else {
					board[nextR][nextC] = 0; // If the path that it follows doesn't check out, it'll go back a step and try again
				}
			}
		}
		return false;
	}

	private boolean getMove100()
	{
		int[][] moveCount =  new int[8][3];
		int tempR; // Used for cleaning up a condition
		int tempC; // ^ ^ ^ ^ ^ ^ ^ ^ ^ ^ ^ ^ ^ ^ ^ ^
		int lowest = Integer.MAX_VALUE; // Useless number, just needs to be bigger than 8
		moves++;
		int[] currentMove = new int[2];
		if (moves == 64) {
			return true;
		}
		for (int i = 0; i < possibleMoves.length; i++) { // goes over every possible knight move and checks if it works
			tempR = possibleMoves[i][0] + rowPos;
			tempC = possibleMoves[i][1] + colPos;
			if (boringCheck(tempR, tempC)) { // Checks if it is in range
				moveCount[i][0] = ACCESS[possibleMoves[i][0] + rowPos + 2][possibleMoves[i][1] + colPos + 2]; 
				// the +2 is there to offset by 2 (first 2 items of every row is 0 and first 2 rows are 0)
				moveCount[i][1] = possibleMoves[i][0];
				moveCount[i][2] = possibleMoves[i][1];
			} else {
				moveCount[i][0] = -1;
			}
		}

		for (int i = 0; i < moveCount.length; i++) {
			try {
				if (moveCount[i][0] <= lowest && moveCount[i][0] != -1 && board[rowPos+moveCount[i][1]][colPos+moveCount[i][2]] == 0) {
					/* moveCount[i][0] <= lowest --> checks if lower than lower
					 * moveCount[i][0] != -1 --> checks if ACCESS is 0 (sets as -1)
					 * board[rowPos+moveCount[i][1]][colPos+moveCount[i][2]] == 0 --> checks to make sure item isn't set
					 */
					currentMove[0] = moveCount[i][1];
					currentMove[1] = moveCount[i][2];
					lowest = moveCount[i][0];
				}
			} catch (ArrayIndexOutOfBoundsException e) {
				// Normally would use "continue;" - Not necessary because at the end of loop
			}
		}
		if (lowest == Integer.MAX_VALUE) { return false; }
		// The only time this would be the same is if it didn't find a place to jump to
		
		// Updates items and sets board
		rowPos += currentMove[0];
		colPos += currentMove[1];
		board[rowPos][colPos] = moves;
		if (!getMove100()) {
			return false;
		};
		return false;
	}

	/**
	 * Primary method for solving the knight's tour. VERSION is used to depict which version the solution is going to be derived from
	 */
	public void solveTour()
	{
		int VERSION = 90;
		moves = 1;
		board[startRow][startCol] = 1;
		if (VERSION == 90) {
			getMove(startRow, startCol, 2);
		} else if (VERSION == 100) {
			getMove100();
		}
	}

	/**
	 * Pads a given number to have a leading 0 if x < 10
	 * @param x number to pad
	 * @return padded String
	 */
	public static String padX(int x) {
		return String.format("%02d", x);
	}

	/**
	 * Validates strings and converts to Integer
	 * 
	 * @param test
	 * @return
	 */
	public static Integer validate(String test) {
		Integer validated;
		try {
			validated = Integer.parseInt(test) - 1;
		} catch (Exception e) {
			validated = -1;
		}
		return validated;
	}
}