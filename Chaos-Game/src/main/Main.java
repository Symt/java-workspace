package main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Scanner;

import javax.swing.JFrame;
import javax.swing.Timer;

public class Main extends JFrame {
	private static final long serialVersionUID = 1L;
	private DrawingPane pane;
	private Thread gen;
	private GenPoint genpoint;

	public Main(int sides, int max) {
		setSize(600, 600);
		setResizable(false);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		pane = new DrawingPane();
		pane.setBackground(Color.BLACK);
		pane.setSize(600, 580);
		genpoint = new GenPoint(sides, getWidth(), getHeight() - 20, max);
		gen = new Thread(genpoint);
		gen.start();
		add(pane);
		setLocationRelativeTo(null);
		setVisible(true);
		pane.update(genpoint.shape, genpoint.points, genpoint.nofpoints);
		render();
	}

	public void render() {
		ActionListener update = new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				pane.update(genpoint.shape, genpoint.points, genpoint.nofpoints);
				pane.paintComponent(pane.getGraphics());
			}
		};

		Timer t = new Timer(10000, update);
		t.setRepeats(true);
		t.start();
	}

	public static void main(String[] args) {
		int sides, max;
		Scanner sc = new Scanner(System.in);
		do {
			System.out.print("Enter the number of sides for the shape: ");
			while (!sc.hasNextInt()) {
				System.out.print("Enter the number of sides for the shape: ");
				sc.next();
			}
			sides = sc.nextInt();
		} while (sides <= 2);
		do {
			System.out.print("Enter the max number of points ( x >= 100 ): ");
			while (!sc.hasNextInt()) {
				System.out.print("Enter the max number of points ( x >= 100 ): ");
				sc.next();
			}
			max = sc.nextInt();
		} while (max < 100);
		
		if (max > 10000000) {
			System.out.println("For the sake of keeping this computer working, please choose a number less than (and including) 10,000,000");
			System.exit(1);
		}
		sc.close();
		new Main(sides, max);
	}

	public void paint(Graphics g) {
		super.paint(g);
	}
}
