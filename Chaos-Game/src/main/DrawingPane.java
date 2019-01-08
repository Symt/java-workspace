package main;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.concurrent.ThreadLocalRandom;

import javax.swing.JPanel;

public class DrawingPane extends JPanel {
	double[][] points;
	double[][] plot;
	int nofpoints;

	public void update(double[][] points, double[][] plot, int nofpoints) {
		this.points = points;
		this.plot = plot;
		this.nofpoints = nofpoints;
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		if (points == null || plot == null) {
			return;
		}
		Graphics2D g2d = (Graphics2D) g;
		g2d.setStroke(new BasicStroke(2));
		for (int i = 0; i < nofpoints; i++) {
			g2d.setColor(new Color(ThreadLocalRandom.current().nextInt(0, 255),ThreadLocalRandom.current().nextInt(0, 255),ThreadLocalRandom.current().nextInt(0, 255)));
			g2d.drawOval((int) (plot[i][0]), (int) (plot[i][1]), 2, 2);
		}
	}
}
