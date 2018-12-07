package main;

import java.awt.Dimension;

import javax.swing.*;

public class Window {
	JFrame frame;
	public Window(Dimension size) {
		frame = new JFrame();
		frame.setSize(size);
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	public void setLocation(int x, int y) {
		frame.setLocation(x, y);
	}
	
	public void display() {
		frame.setVisible(true);
	}
	public void setX(int x) {
		frame.setLocation(x, frame.getY());
	}
}