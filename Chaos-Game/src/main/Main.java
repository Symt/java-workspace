package main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Scanner;

import javax.swing.JFrame;
import javax.swing.Timer;

public class Main extends JFrame {
	private DrawingPane pane;
	private Thread gen;
	private GenPoint genpoint;
	public Main() {
		setSize(800,800);
		setResizable(false);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		pane = new DrawingPane();
		pane.setBackground(Color.BLACK);
		pane.setSize(800, 780);
		int sides;
		Scanner sc = new Scanner(System.in);
		do {
			System.out.print("Enter the number of sides for the shape: ");
			while (!sc.hasNextInt()) {
				System.out.print("Enter the number of sides for the shape: ");
				sc.next();
			}
			sides = sc.nextInt();
		} while (sides <= 2);
		if (sides >= 10) {
			System.out.println("It is highly advised to stay below 10 sides to improve performance while generating hundreds of thousands of points. \nIf you don't really care, ignore this message and proceed");
		}
		genpoint = new GenPoint(sides, getWidth(), getHeight()-20);
		gen = new Thread(genpoint);
		sc.close();
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
		
		Timer t = new Timer(1000, update);
		t.setRepeats(true);
		t.start();
	}
	
	public static void main(String[] args) {
		new Main();
	}
	
	public void paint(Graphics g) {
		super.paint(g);
	}
}
