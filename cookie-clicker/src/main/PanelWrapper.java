package main;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

public class PanelWrapper extends JPanel {
	private static final long serialVersionUID = 1L;
	int width, height;
	public PanelWrapper() {
		width = 200;
		height = 200;
	}
	
	public void setCircleSize(int w, int h) {
		width = w;
		height = h;
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;
		g2d.setColor(Color.WHITE);
		g2d.setStroke(new BasicStroke(2));
		g2d.drawOval(getWidth()/2-(width/2), getHeight()/2-(height/2), width, height);
	}
}
