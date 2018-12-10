package main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.math.RoundingMode;
import java.text.DecimalFormat;

import javax.swing.JPanel;

public class PanelWrapper extends JPanel {
	private static final long serialVersionUID = 1L;
	int width, height, totalCookies;
	BufferedImage cookie;
	String string, string2;
	double cps;
	DecimalFormat df = new DecimalFormat("#.###");

	public PanelWrapper(BufferedImage cookie) {
		width = 200;
		height = 200;
		this.cookie = cookie;
		totalCookies = 0;
		cps = 0.0;
		df.setRoundingMode(RoundingMode.CEILING);
	}

	public void setCircleSize(int w, int h, int cookies) {
		width = w;
		height = h;
		totalCookies = cookies;
	}
	
	public void setCPS(double cps) {
		this.cps = cps;
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(cookie, getWidth() / 2 - (width / 2), getHeight() / 2 - (height / 2), width, height, null);
		g.setColor(Color.WHITE);
		g.setFont(new Font("TrebuchetMS", Font.PLAIN, 32)); 
		string = "" + totalCookies;
		string2 = "CPS: " + df.format(cps);
		g.drawString(string, getWidth() / 2 - g.getFontMetrics().stringWidth(string)/2, getHeight() / 2 + 300);
		g.drawString(string2, getWidth() / 2 - g.getFontMetrics().stringWidth(string2)/2, getHeight() / 2 - 300);
	}
}
