package main;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

public class DrawingPane extends JPanel {
	private static final long serialVersionUID = 1L;
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
		g.setColor(Color.GREEN);
		for (int i = 0; i < nofpoints; i++) {
			g.drawOval((int) (plot[i][0]), (int) (plot[i][1]), 2, 2);
		}
	}
}
