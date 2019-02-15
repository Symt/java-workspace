import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Scanner;

import javax.swing.JFrame;

public class Main extends JFrame {
	/*
	 * grid values are as follows:
	 * 0: empty
	 * 1: tree
	 * 2: burning
	 */
	private static final long serialVersionUID = 1L; // Auto generated to remove warning in eclipse
	int[][] grid;
	static int headerPadding = 22;
	static double probsCatch;
	int step = 0;
	boolean event = false;

	/*
	 * STUDENT CODE
	 */

	public void fire(double probsCatch) {

	}

	/*
	 * WINDOW CODE
	 */

	public Main(int size) {
		super("Spreading of Fire");
		grid = new int[size][size];
		for (int i = 0; i < grid.length; i++) {
			for (int j = 0; j < grid[i].length; j++) {
				if (i == 0 || j == 0 || i == grid.length - 1 || j == grid[i].length - 1) {
					grid[i][j] = 0;
				} else if (i == grid.length / 2 && j == grid[i].length / 2) {
					grid[i][j] = 2;
				} else {
					grid[i][j] = 1;
				}
			}
		}
		setSize(size * 10 + size * 2 - 2, size * 10 + headerPadding + size * 2 - 2);
		// squares
		setBackground(Color.BLACK);
		setLocationRelativeTo(null);
		addKeyListener(new KeyListener() {

			@Override
			public void keyPressed(KeyEvent arg0) {
				if (arg0.getKeyCode() == KeyEvent.VK_SPACE && !event) {
					event = true;
					fire(probsCatch);
					System.out.println("Step: " + ++step);
					repaint();
					event = false;
				}
			}

			@Override
			public void keyReleased(KeyEvent arg0) {}

			@Override
			public void keyTyped(KeyEvent arg0) {}

		});
		setResizable(false);
		setVisible(true);
	}

	public void paint(Graphics g) {
		int buffer1 = 0;
		int buffer2 = 0;
		for (int i = 0; i < grid.length; i++) {
			for (int j = 0; j < grid[i].length; j++) {
				switch (grid[i][j]) {
				case 0:
					g.setColor(Color.YELLOW);
					break;
				case 1:
					g.setColor(Color.GREEN);
					break;
				case 2:
					g.setColor(Color.RED);
					break;
				default:
					g.setColor(Color.WHITE);
					break;
				}
				g.fillRect(j * 10 + buffer1, i * 10 + buffer2 + headerPadding, 10, 10);
				buffer1 += 2;
			}
			buffer2 += 2;
			buffer1 = 0;
		}
	}

	public static void main(String[] args) {
		System.out.println("Note: Press the spacebar to advance a \"step\"");
		Scanner sc = new Scanner(System.in);
		System.out.print("Enter the size (5, 20, 50, etc): ");
		while (!sc.hasNextInt()) {
			System.out.print("Enter the size (5, 20, 50, etc): ");
			sc.next();
		}
		int size = sc.nextInt();
		System.out.print("Enter the probability of a tree catching on fire (.5, .1, .85, etc): ");
		while (!sc.hasNextDouble()) {
			System.out.print("Enter the probability of a tree catching on fire (.5, .1, .85, etc): ");
			sc.next();
		}
		probsCatch = sc.nextDouble();
		sc.close();
		new Main(((size % 2 == 1) ? size : size + 1) + 2);
	}
}
