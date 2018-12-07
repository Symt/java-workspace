package logic;

public class Moves {
	public static int[][][] rotate(int[][][] cube, int rotation) {
		int[][] top = cube[0];
		int[][] front = cube[1];
		int[][] right = cube[2];
		int[][] back = cube[3];
		int[][] left = cube[4];
		int[][] bottom = cube[5];
		int[][] temp = new int[3][3];
		int[][] temp2 = new int[3][3];
		if (rotation == 1) { // x
			temp = front;
			front = bottom;
			temp2 = top;
			top = temp;
			temp = back;
			back = Moves.shift(Moves.shift(temp2, true), true);
			bottom = Moves.shift(Moves.shift(temp, true), true);
			shift(cube[2], true);
			shift(cube[4], false);
		} else if (rotation == 2) { // x'
			temp = front;
			front = top;
			temp2 = bottom;
			bottom = temp;
			temp = back;
			back = Moves.shift(Moves.shift(temp2, true), true);
			top = Moves.shift(Moves.shift(temp, true), true);;
			shift(cube[2], false);
			shift(cube[4], true);
		} else if (rotation == 3) { // y
			temp = front;
			front = right;
			temp2 = left;
			left = temp;
			temp = back;
			back = temp2;
			right = temp;
			shift(cube[0], true);
			shift(cube[5], false);
		} else if (rotation == 4) { // y'
			temp = front;
			front = left;
			temp2 = right;
			right = temp;
			temp = back;
			back = temp2;
			left = temp;
			shift(cube[0], false);
			shift(cube[5], true);
		} else if (rotation == 5) { // z
			temp = top;
			top = Moves.shift(left, true);
			temp2 = right;
			right = Moves.shift(temp, true);
			temp = bottom;
			bottom = Moves.shift(temp2, true);
			left = Moves.shift(temp, true);
			shift(cube[1], true);
			shift(cube[3], false);
		} else if (rotation == 6) { // z'
			temp = top;
			top = Moves.shift(right, false);
			temp2 = left;
			left = Moves.shift(temp, false);
			temp = bottom;
			bottom = Moves.shift(temp2, false);
			right = Moves.shift(temp, false);
			shift(cube[1], false);
			shift(cube[3], true);
		} else {
			throw new IllegalArgumentException("Rotation is out of range (0 < rotation <= 6)");
		}

		int[][][] result = { top, front, right, back, left, bottom };
		return result;
	}

	public static int[][][] right(int[][][] cube, boolean prime) {
		int a, b, c, d, e, f, g, h, i, j, k, l;
		a = cube[1][2][2];
		b = cube[1][1][2];
		c = cube[1][0][2];
		d = cube[0][2][2];
		e = cube[0][1][2];
		f = cube[0][0][2];
		g = cube[3][2][0];
		h = cube[3][1][0];
		i = cube[3][0][0];
		j = cube[5][2][2];
		k = cube[5][1][2];
		l = cube[5][0][2];
		if (!prime) {
			cube[1][2][2] = j;
			cube[1][1][2] = k;
			cube[1][0][2] = l;
			cube[0][2][2] = a;
			cube[0][1][2] = b;
			cube[0][0][2] = c;
			cube[3][2][0] = f;
			cube[3][1][0] = e;
			cube[3][0][0] = d;
			cube[5][2][2] = i;
			cube[5][1][2] = h;
			cube[5][0][2] = g;
			cube[2] = shift(cube[2], true);
		} else {
			cube[1][2][2] = d;
			cube[1][1][2] = e;
			cube[1][0][2] = f;
			cube[0][2][2] = i;
			cube[0][1][2] = h;
			cube[0][0][2] = g;
			cube[3][2][0] = l;
			cube[3][1][0] = k;
			cube[3][0][0] = j;
			cube[5][2][2] = a;
			cube[5][1][2] = b;
			cube[5][0][2] = c;
			cube[2] = shift(cube[2], false);
		}
		return cube;
	}

	public static int[][][] left(int[][][] cube, boolean prime) {
		int a, b, c, d, e, f, g, h, i, j, k, l;
		a = cube[1][2][0];
		b = cube[1][1][0];
		c = cube[1][0][0];
		d = cube[0][2][0];
		e = cube[0][1][0];
		f = cube[0][0][0];
		g = cube[3][2][2];
		h = cube[3][1][2];
		i = cube[3][0][2];
		j = cube[5][2][0];
		k = cube[5][1][0];
		l = cube[5][0][0];
		if (prime) {
			cube[1][2][0] = j;
			cube[1][1][0] = k;
			cube[1][0][0] = l;
			cube[0][2][0] = a;
			cube[0][1][0] = b;
			cube[0][0][0] = c;
			cube[3][2][2] = f;
			cube[3][1][2] = e;
			cube[3][0][2] = d;
			cube[5][2][0] = i;
			cube[5][1][0] = h;
			cube[5][0][0] = g;
			cube[4] = shift(cube[4], false);
		} else {
			cube[1][2][0] = d;
			cube[1][1][0] = e;
			cube[1][0][0] = f;
			cube[0][2][0] = i;
			cube[0][1][0] = h;
			cube[0][0][0] = g;
			cube[3][2][2] = l;
			cube[3][1][2] = k;
			cube[3][0][2] = j;
			cube[5][2][0] = a;
			cube[5][1][0] = b;
			cube[5][0][0] = c;
			cube[4] = shift(cube[4], true);
		}
		return cube;
	}

	public static int[][][] up(int[][][] cube, boolean prime) {
		int[] front = cube[1][0];
		int[] right = cube[2][0];
		int[] back = cube[3][0];
		int[] left = cube[4][0];

		if (!prime) {
			cube[1][0] = right;
			cube[2][0] = back;
			cube[3][0] = left;
			cube[4][0] = front;
			cube[0] = shift(cube[0], true);
		} else {
			cube[1][0] = left;
			cube[4][0] = back;
			cube[3][0] = right;
			cube[2][0] = front;
			cube[0] = shift(cube[0], false);
		}
		return cube;
	}

	public static int[][][] down(int[][][] cube, boolean prime) {
		int[] front = cube[1][2];
		int[] right = cube[2][2];
		int[] back = cube[3][2];
		int[] left = cube[4][2];

		if (prime) {
			cube[1][2] = right;
			cube[2][2] = back;
			cube[3][2] = left;
			cube[4][2] = front;
			cube[5] = shift(cube[5], false);
		} else {
			cube[1][2] = left;
			cube[4][2] = back;
			cube[3][2] = right;
			cube[2][2] = front;
			cube[5] = shift(cube[5], true);
		}
		return cube;
	}

	public static int[][][] front(int[][][] cube, boolean prime) {
		int a, b, c, d, e, f, g, h, i, j, k, l;
		a = cube[4][0][2];
		b = cube[4][1][2];
		c = cube[4][2][2];
		d = cube[0][2][0];
		e = cube[0][2][1];
		f = cube[0][2][2];
		g = cube[2][2][0];
		h = cube[2][1][0];
		i = cube[2][0][0];
		j = cube[5][0][0];
		k = cube[5][0][1];
		l = cube[5][0][2];
		if (prime) {
			cube[5][0][2] = c;
			cube[5][0][1] = b;
			cube[5][0][0] = a;
			cube[4][2][2] = d;
			cube[4][1][2] = e;
			cube[4][0][2] = f;
			cube[0][2][2] = g;
			cube[0][2][1] = h;
			cube[0][2][0] = i;
			cube[2][0][0] = l;
			cube[2][1][0] = k;
			cube[2][2][0] = j;
			cube[1] = shift(cube[1], false);
		} else {
			cube[0][2][2] = a;
			cube[0][2][1] = b;
			cube[0][2][0] = c;
			cube[2][0][0] = d;
			cube[2][1][0] = e;
			cube[2][2][0] = f;
			cube[5][0][2] = i;
			cube[5][0][1] = h;
			cube[5][0][0] = g;
			cube[4][2][2] = l;
			cube[4][1][2] = k;
			cube[4][0][2] = j;
			cube[1] = shift(cube[1], true);
		}
		return cube;
	}

	public static int[][][] back(int[][][] cube, boolean prime) {
		int a, b, c, d, e, f, g, h, i, j, k, l;
		a = cube[4][2][0];
		b = cube[4][1][0];
		c = cube[4][0][0];
		d = cube[0][0][0];
		e = cube[0][0][1];
		f = cube[0][0][2];
		g = cube[2][2][2];
		h = cube[2][1][2];
		i = cube[2][0][2];
		j = cube[5][2][2];
		k = cube[5][2][1];
		l = cube[5][2][0];
		if (!prime) {
			cube[5][2][2] = a;
			cube[5][2][1] = b;
			cube[5][2][0] = c;
			cube[4][2][0] = d;
			cube[4][1][0] = e;
			cube[4][0][0] = f;
			cube[0][0][0] = g;
			cube[0][0][1] = h;
			cube[0][0][2] = i;
			cube[2][0][2] = j;
			cube[2][1][2] = k;
			cube[2][2][2] = l;
			cube[3] = shift(cube[3], true);
		} else {
			cube[0][0][0] = a;
			cube[0][0][1] = b;
			cube[0][0][2] = c;
			cube[2][0][2] = d;
			cube[2][1][2] = e;
			cube[2][2][2] = f;
			cube[5][2][2] = g;
			cube[5][2][1] = h;
			cube[5][2][0] = i;
			cube[4][2][0] = j;
			cube[4][1][0] = k;
			cube[4][0][0] = l;
			cube[3] = shift(cube[3], false);
		}
		return cube;
	}
	public static int[][][] middle(int[][][] cube, boolean prime) {
		int a, b, c, d, e, f, g, h, i, j, k, l;
		a = cube[1][2][1];
		b = cube[1][1][1];
		c = cube[1][0][1];
		d = cube[0][2][1];
		e = cube[0][1][1];
		f = cube[0][0][1];
		g = cube[3][2][1];
		h = cube[3][1][1];
		i = cube[3][0][1];
		j = cube[5][2][1];
		k = cube[5][1][1];
		l = cube[5][0][1];
		if (prime) {
			cube[1][2][1] = j;
			cube[1][1][1] = k;
			cube[1][0][1] = l;
			cube[0][2][1] = a;
			cube[0][1][1] = b;
			cube[0][0][1] = c;
			cube[3][2][1] = f;
			cube[3][1][1] = e;
			cube[3][0][1] = d;
			cube[5][2][1] = i;
			cube[5][1][1] = h;
			cube[5][0][1] = g;
		} else {
			cube[1][2][1] = d;
			cube[1][1][1] = e;
			cube[1][0][1] = f;
			cube[0][2][1] = i;
			cube[0][1][1] = h;
			cube[0][0][1] = g;
			cube[3][2][1] = l;
			cube[3][1][1] = k;
			cube[3][0][1] = j;
			cube[5][2][1] = a;
			cube[5][1][1] = b;
			cube[5][0][1] = c;
		}
		return cube;
	}

	public static int[][] shift(int[][] side, boolean cw) {
		int a, b, c, d, e, f, g, h;
		a = side[0][0];
		b = side[0][1];
		c = side[0][2];
		d = side[1][2];
		e = side[2][2];
		f = side[2][1];
		g = side[2][0];
		h = side[1][0];
		if (cw) {
			side[0][0] = g;
			side[0][1] = h;
			side[0][2] = a;
			side[1][2] = b;
			side[2][2] = c;
			side[2][1] = d;
			side[2][0] = e;
			side[1][0] = f;
		} else {
			side[0][0] = c;
			side[0][1] = d;
			side[0][2] = e;
			side[1][2] = f;
			side[2][2] = g;
			side[2][1] = h;
			side[2][0] = a;
			side[1][0] = b;
		}
		return side;
	}
}