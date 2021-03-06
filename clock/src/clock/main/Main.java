package clock.main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.Calendar;
import java.util.Date;

import javax.swing.JFrame;

public class Main extends JFrame {
	private static final long serialVersionUID = 1L;
	PanelWrapper pw = new PanelWrapper(500, 500);

	public Main() {
		setSize(500, 500);
		setBackground(Color.black);
		setLocationRelativeTo(null);
		setResizable(false);
		pw.setSize(500, 480);
		pw.setPreferredSize(new Dimension(500, 480));
		pw.setBackground(Color.black);
		add(pw);
		setVisible(true);
		run();
	}

	public void paint(Graphics g) {
		super.paint(g);
		pw.repaint();
	}

	public static void main(String[] args) {
		new Main();
	}

	public void run() {
		Calendar c = Calendar.getInstance();
		long endTime = System.currentTimeMillis();
		while (true) {
			if (System.currentTimeMillis() - endTime >= 1000 / 120) {
				c.setTime(new Date());
				pw.setAngle(new double[] { (c.get(Calendar.HOUR) / (double) 6 * Math.PI) - (Math.PI / 2),
						(c.get(Calendar.MINUTE) / (double) 30 * Math.PI) - (Math.PI / 2),
						(c.get(Calendar.SECOND) / (double) 30 * Math.PI) - (Math.PI / 2),
						(c.get(Calendar.MILLISECOND) / (double) 500 * Math.PI) - (Math.PI / 2) });
				repaint();
				endTime = System.currentTimeMillis();
			}
		}
	}
}
