package main;

import java.util.*;

public class Lab29ast {
	public static void main(String args[]) {
		System.out.println("\nLab 29a 80/100 Point Version\n");
		Scanner input = new Scanner(System.in);
		System.out.print("Enter random starting seed  ===>>  ");
		int seed = input.nextInt();

		Maze maze = new Maze(seed);
		maze.displayMaze();
		maze.solveMaze();
		maze.fixMat();
		maze.displayMaze();
		maze.mazeSolution();

		input.close();
	}
}

class Maze {

	private char mat[][]; // 2d character array that stores the maze display
	private Coord currentMove; // object that stores current maze position
	private Stack<ArrayList<Integer>> visitStack; // stack that stores location that have been visited
	int[][] moves = { { -1, 0 }, { -1, -1 }, { 0, -1 }, { 1, -1 }, { 1, 0 }, { 1, 1 }, { 0, 1 }, { -1, 1 } };
	ArrayList<ArrayList<Integer>> moveList = new ArrayList<ArrayList<Integer>>();
	ArrayList<ArrayList<Integer>> farthest = new ArrayList<ArrayList<Integer>>();

	class Coord
	// Coord is a class that stores a single maze location.
	{
		private int rPos;
		private int cPos;

		public Coord(int r, int c) {
			rPos = r;
			cPos = c;
		}

		public boolean isFree() {
			return (rPos == 0 && cPos == 0);
		}

		public void setPos(int r, int c) {
			rPos += r;
			cPos += c;
		}

		public String display() {
			return "{" + cPos + ", " + rPos + "}";
		}
	}

	public Maze(int seed)
	// constructor which generates the random maze, random starting location
	// and initializes Maze class values. If the random value equals 0 the maze
	// store an 'X' otherwise it store an 'O' in the maze.
	{
		Random random = new Random(seed);
		int startRow, startCol;
		mat = new char[12][12];
		for (int r = 0; r < 12; r++)
			for (int c = 0; c < 12; c++) {
				if (r == 0 || c == 0 || r == 11 || c == 11)
					mat[r][c] = 'X';
				else {
					int rndInt = random.nextInt(2);
					if (rndInt == 0)
						mat[r][c] = 'X';
					else
						mat[r][c] = 'O';
				}
			}
		mat[0][0] = 'O';
		startRow = random.nextInt(12);
		startCol = 11;
		mat[startRow][startCol] = '.';
		visitStack = new Stack<ArrayList<Integer>>();
		currentMove = new Coord(startRow, startCol);
		ArrayList<Integer> temp = new ArrayList<Integer>();
		temp.add(currentMove.rPos);
		temp.add(currentMove.cPos);
		visitStack.push(temp);
		farthest.add(temp);
	}

	public void fixMat() {
		if (!(currentMove.rPos == 0 && currentMove.cPos == 0)) {
			for (int i = 0; i < farthest.size(); i++) {
				mat[farthest.get(i).get(0)][farthest.get(i).get(1)] = '.';
			}
		}
	}

	void displayMaze()
	// displays the current maze configuration
	{
		System.out.println("\nRANDOM MAZE DISPLAY\n");
		for (int r = 0; r < 12; r++) {
			for (int c = 0; c < 12; c++)
				System.out.print(mat[r][c] + "  ");
			System.out.println();
		}
		System.out.println();
		pause();
	}

	public void solveMaze()
	// This methods solves the maze with private helper method <getMove>.
	// A loop is needed to repeat getting new moves until either a maze solution
	// is found or it is determined that there is no way out off the maze.
	{
		System.out.println("\n>>>>>   WORKING  ....  SOLVING MAZE   <<<<<\n");
		while (!currentMove.isFree() && !visitStack.isEmpty()) {
			if (!visitStack.isEmpty() && !getMove()) {
				ArrayList<Integer> temp = new ArrayList<Integer>();
				temp = visitStack.pop();
				if (visitStack.isEmpty()) {
					continue;
				}
				currentMove.rPos = visitStack.peek().get(0);
				currentMove.cPos = visitStack.peek().get(1);
				mat[temp.get(0)][temp.get(1)] = 'O';
				continue;
			}
		}
	}

	public void mazeSolution()
	// Short method to display the result of the maze solution
	{
		if (currentMove.isFree())
			System.out.println("\nTHE MAZE HAS A SOLUTION.\n");
		else
			System.out.println("\nTHE MAZE HAS NO SOLUTION.\n");
	}

	private boolean inBounds(int r, int c)
	// This method determines if a coordinate position is inbounds or not
	{
		return (r < 12 && c < 12 && r >= 0 && c >= 0);
	}

	private boolean getMove()
	// This method checks eight possible positions in a counter-clock wise manner
	// starting with the (-1,0) position. If a position is found the method returns
	// true and the currentMove coordinates are altered to the new position
	{
		for (int[] i : moves) {
			ArrayList<Integer> test = new ArrayList<Integer>();
			test.add(currentMove.rPos + i[0]);
			test.add(currentMove.cPos + i[1]);
			if (inBounds(currentMove.rPos + i[0], currentMove.cPos + i[1])
					&& mat[currentMove.rPos + i[0]][currentMove.cPos + i[1]] == 'O' && !moveList.contains(test)) {
				currentMove.setPos(i[0], i[1]);
				moveList.add(test);
				mat[currentMove.rPos][currentMove.cPos] = '.';
				visitStack.push(test);
				if (visitStack.size() >= farthest.size()) {
					farthest.add(test);
				}
				return true;
			}
		}
		return false;
	}

	private void pause() {
		// Scanner input = new Scanner(System.in);
		// String dummy;
		System.out.println("Pausing (Disabled)");
		// System.out.print("\nPress <Enter> to continue ===>> ");
		// dummy = input.nextLine();
	}

}