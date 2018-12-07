package main;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

import logic.Moves;

public class Main {

	int[][] front = new int[3][3];
	int[][] top = new int[3][3];
	int[][] bottom = new int[3][3];
	int[][] right = new int[3][3];
	int[][] back = new int[3][3];
	int[][] left = new int[3][3];
	int[][][] cube = new int[6][3][3];
	String[] moves = {"R", "R'", "U", "U'", "L", "L'", "D", "D'", "F", "F'", "B", "B'"};
	public static String[] scramble = new String[30];
	public static int[][][] solved;
	public static Map<String, String[]> algorithms = new HashMap<String, String[]>();
	// TODO: Get all the other algorithms for oll and pll
	Window window;

	public Main() {
		String[] tPerm = { "R", "U", "R'", "U'", "R'", "F", "R", "R", "U'", "R'", "U'", "R", "U", "R'", "F'" };
		algorithms.put("tperm", tPerm);
		for (int i = 0; i < 3; i++) {
			Arrays.fill(front[i], 2);
			Arrays.fill(top[i], 5);
			Arrays.fill(bottom[i], 0);
			Arrays.fill(left[i], 3);
			Arrays.fill(right[i], 1);
			Arrays.fill(back[i], 4);
		}
		cube[0] = top;
		cube[1] = front;
		cube[2] = right;
		cube[3] = back;
		cube[4] = left;
		cube[5] = bottom;
		solved = cube;
		window = new Window(cube);
	}
	
	public int random(int start, int end) {
		return ThreadLocalRandom.current().nextInt(start, end);
	}

	public static void main(String[] args) {
		new Main();
	}

	/**
	 * Old display method. It was used display the colors before the GUI was made;
	 * @deprecated
	 * 
	 */
	public void display() {
		System.out.println("--top--\n" + Arrays.toString(cube[0][0]) + "\n" + Arrays.toString(cube[0][1]) + "\n"
				+ Arrays.toString(cube[0][2]));
		System.out.println("--front--\n" + Arrays.toString(cube[1][0]) + "\n" + Arrays.toString(cube[1][1]) + "\n"
				+ Arrays.toString(cube[1][2]));
		System.out.println("--right--\n" + Arrays.toString(cube[2][0]) + "\n" + Arrays.toString(cube[2][1]) + "\n"
				+ Arrays.toString(cube[2][2]));
		System.out.println("--back--\n" + Arrays.toString(cube[3][0]) + "\n" + Arrays.toString(cube[3][1]) + "\n"
				+ Arrays.toString(cube[3][2]));
		System.out.println("--left--\n" + Arrays.toString(cube[4][0]) + "\n" + Arrays.toString(cube[4][1]) + "\n"
				+ Arrays.toString(cube[4][2]));
		System.out.println("--bottom--\n" + Arrays.toString(cube[5][0]) + "\n" + Arrays.toString(cube[5][1]) + "\n"
				+ Arrays.toString(cube[5][2]));
	}
}
