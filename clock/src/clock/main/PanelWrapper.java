package clock.main;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.geom.Arc2D;

import javax.swing.JPanel;

public class PanelWrapper extends JPanel {
	int w, h;
	double[] angle;
	Color[] c = new Color[] { Color.BLUE, Color.GREEN, Color.ORANGE, Color.RED };
	public PanelWrapper(int w, int h) {
		this.w = w;
		angle = new double[] {0};
		this.h = h;
	}
	public void setAngle(double[] angle) {
		this.angle = angle;
	}
	public void paintComponent(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		super.paintComponent(g);
		g2d.setStroke(new BasicStroke(5));
		for (int i = 0; i < angle.length; i++) {
			g2d.setColor(c[i]);
			g2d.drawLine(w/2, h/2, (int)(w/2+Math.cos(angle[i])*100*(i+1)/angle.length), (int)(h/2+Math.sin(angle[i])*100*(i+1)/angle.length));
		}
		g2d.setColor(Color.WHITE);
		g2d.setStroke(new BasicStroke(6));
		g2d.drawOval(w/2-100, h/2-100, 200, 200);
	}
}
