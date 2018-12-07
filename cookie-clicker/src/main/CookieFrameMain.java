package main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JFrame;

public class CookieFrameMain extends JFrame {
	private static final long serialVersionUID = 1L;
	PanelWrapper pw = new PanelWrapper();
	boolean click = false;
	int cookies = 0;
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
		pw.setSize(getWidth()-20, getHeight());
		pw.setBackground(Color.BLACK);
		add(pw);
		setVisible(true);
		run();
	}

	public static void main(String[] args) {
		new CookieFrameMain();
	}
	
	public void run() {
		long nextTime = System.currentTimeMillis()+50;
		while (true) {
			if (nextTime - System.currentTimeMillis() < 0) {
				nextTime = System.currentTimeMillis()+50;
				if (click) {
					pw.setCircleSize(190, 190);
					click = false;
					cookies++;
				} else {
					pw.setCircleSize(200, 200);
				}
				
				repaint();
			}
		}
	}
	
	public boolean inCircle(int x, int y) {
		double dist = Math.sqrt(Math.pow((x-(getWidth()/2)), 2) + Math.pow((y-(getHeight()/2+10)), 2));
		return (dist < pw.width/2);
	}
	public void paint(Graphics g) {
		super.paint(g);
		pw.repaint();
	}
}
