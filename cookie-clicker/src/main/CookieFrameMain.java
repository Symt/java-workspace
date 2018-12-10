package main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

public class CookieFrameMain extends JFrame {
	BufferedImage cookie;
	private static final long serialVersionUID = 1L;
	PanelWrapper pw;
	boolean click = false;
	int cookies = 0;
	double cps = 0;

	public CookieFrameMain() {
		addMouseListener((MouseListener) new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent arg0) {
				if (inCircle(arg0.getX(), arg0.getY())) {
					click = true;
				}
			}

			@Override
			public void mouseEntered(MouseEvent arg0) {
			}

			@Override
			public void mouseExited(MouseEvent arg0) {
			}

			@Override
			public void mousePressed(MouseEvent arg0) {
			}

			@Override
			public void mouseReleased(MouseEvent arg0) {
			}

		});
		setSize(1440, 822);
		setResizable(false);
		try {
			cookie = ImageIO.read(new File("cookie-2.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		pw = new PanelWrapper(cookie);
		pw.setSize(getWidth() - 20, getHeight());
		pw.setBackground(Color.BLACK);
		add(pw);
		setVisible(true);
		run();
	}

	public static void main(String[] args) {
		new CookieFrameMain();
	}

	public void run() {
		int lastSessionCookies = 0;
		int sessionCookies = 0;
		long startTime = System.currentTimeMillis() - 1000;
		long nextTime = System.currentTimeMillis() + 50;
		long secondTime = System.currentTimeMillis() + 1000;
		while (true) {
			if (nextTime - System.currentTimeMillis() < 0) {
				nextTime = System.currentTimeMillis() + 50;
				if (click) {
					pw.setCircleSize(190, 190, cookies);
					click = false;
					cookies++;
					sessionCookies++;
				} else {
					pw.setCircleSize(200, 200, cookies);
				}
				if (sessionCookies != lastSessionCookies) {
					cps = (double) (sessionCookies
							/ (double) ((System.currentTimeMillis() - startTime) / (double) 1000));
					secondTime = System.currentTimeMillis() + 1000;
				} else if (secondTime - System.currentTimeMillis() < 0) {
					startTime = System.currentTimeMillis() - 1000;
					sessionCookies = 0;
					lastSessionCookies = -1;
					cps = 0;
				}
				lastSessionCookies = sessionCookies;
				pw.setCPS(cps);
				pw.repaint();
			}
		}
	}

	public boolean inCircle(int x, int y) {
		double dist = Math.sqrt(Math.pow((x - (getWidth() / 2)), 2) + Math.pow((y - (getHeight() / 2 + 10)), 2));
		return (dist < pw.width / 2);
	}

	public void paint(Graphics g) {
		super.paint(g);
		pw.repaint();
	}
}
