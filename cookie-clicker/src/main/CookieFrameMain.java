package main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class CookieFrameMain extends JFrame {
	BufferedImage cookie;
	private static final long serialVersionUID = 1L;
	PanelWrapper pw;
	boolean click = false;
	int cookies = 0;
	double cps = 0;
	double basecps = 0;
	JFrame shop = new JFrame("Shop");

	public CookieFrameMain() {
		setIconImage(new ImageIcon("icon.png").getImage());
		shop.setTitle("Shop");
		shop.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		shop.setSize(1440, 822);
		shop.setResizable(false);
		shop.setBackground(new Color(34, 34, 34));
		shop.setLayout(new GridLayout(3, 6));
		JButton[] shopItems = { new JButton("Slave ( " + 100 + " )"), new JButton("Indentured Servant ( " + 1000 + " )"),
				new JButton("Sharecropper ( " + 10000 + " )"), new JButton("Overseer ( " + 100000 + " )") };

		for (int i = 0; i < shopItems.length; i++) {
			shopItems[i].setFocusable(false);
			shopItems[i].addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					String sourceText = ((JButton) e.getSource()).getText();
					String[] sourceInfo = sourceText.replaceAll("\\( (.*?) \\)", "-$1").replaceAll(" ", "").split("-");
					int cookieCost = Integer.valueOf(sourceInfo[1]);
					String cookieName = sourceInfo[0];

					switch (cookieName.toLowerCase()) {
					case "slave":
						if (cookies >= cookieCost) {
							cookies -= cookieCost;
							basecps += 1;
							cookieCost += cookieCost/4;
						}
						((JButton) e.getSource()).setText("Slave ( " + cookieCost + " )");
						break;
					case "indenturedservant":
						if (cookies >= cookieCost) {
							cookies -= cookieCost;
							basecps += 10;
							cookieCost += cookieCost/8;
						}
						((JButton) e.getSource()).setText("Indentured Servant ( " + cookieCost + " )");
						break;
					case "sharecropper":
						if (cookies >= cookieCost) {
							cookies -= cookieCost;
							basecps += 100;
							cookieCost += cookieCost/16;
						}
						((JButton) e.getSource()).setText("Sharecropper ( " + cookieCost + " )");
						break;
					case "overseer":
						if (cookies >= cookieCost) {
							cookies -= cookieCost;
							basecps += 1000;
							cookieCost += cookieCost/32;
						}
						((JButton) e.getSource()).setText("Overseer ( " + cookieCost + " )");
						break;
					}
				}
			});
		}
		boolean done = false;
		for (int i = 0; i < 7; i++) {
			shop.add(new JPanel());
			if (i == 6 && !done) {
				for (JButton k : shopItems)
					shop.add(k);
				i = 0;
				done = true;
			}
		}

		shop.addWindowListener(new java.awt.event.WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent windowEvent) {
				shop.setVisible(false);
				setVisible(true);
			}
		});
		addMouseListener(new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent arg0) {
				if (inCircle(arg0.getX(), arg0.getY())) {
					click = true;
				} else if (inShopButton(arg0.getX(), arg0.getY())) {
					setVisible(false);
					shop.setVisible(true);
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
		pw.setBackground(new Color(34, 34, 34));
		setDefaultCloseOperation(EXIT_ON_CLOSE);
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
		long time = System.currentTimeMillis() + 1000;
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
				pw.setCPS(cps, basecps);
				pw.repaint();
			}
			if (time - System.currentTimeMillis() < 0) {
				time = System.currentTimeMillis() + 1000;
				cookies += basecps;
			}
		}
	}

	public boolean inCircle(int x, int y) {
		double dist = Math.sqrt(Math.pow((x - (getWidth() / 2)), 2) + Math.pow((y - (getHeight() / 2 + 10)), 2));
		return (dist < pw.width / 2);
	}

	public boolean inShopButton(int x, int y) {
		return (x > getWidth() / 4 && x < getWidth() / 4 + 100 && y < getHeight() / 2 + 48 && y > getHeight() / 2 - 16);
	}

	public void paint(Graphics g) {
		super.paint(g);
		pw.repaint();
	}
}
