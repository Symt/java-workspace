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
import java.math.BigInteger;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class Main extends JFrame {
	BufferedImage cookie;
	private static final long serialVersionUID = 1L;
	PanelWrapper pw;
	boolean click = false;
	double cps = 0;
	double basecps = 0;
	int multiplier = 1;
	int totalCookies = 0;
	JFrame shop;
	JFrame powerup;
	JLabel cookieLabel;
	int slavesPerMinute = 0;
	BigInteger cookies = BigInteger.valueOf(0);

	public Main() {
		setIconImage(new ImageIcon("icon.png").getImage());
		shop = initShop();
		addMouseListener(new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent arg0) {
				if (inCircle(arg0.getX(), arg0.getY())) {
					click = true;
				} else if (inShopButton(arg0.getX(), arg0.getY())) {
					setVisible(false);
					shop.setVisible(true);
				} else if (inPowerupButton(arg0.getX(), arg0.getY())) {
					// setVisible(false);
					// powerup.setVisible(true);
				}
			}

			@Override
			public void mouseEntered(MouseEvent arg0) {}

			@Override
			public void mouseExited(MouseEvent arg0) {}

			@Override
			public void mousePressed(MouseEvent arg0) {}

			@Override
			public void mouseReleased(MouseEvent arg0) {}

		});
		setSize(1440, 822);
		setResizable(false);
		try {
			cookie = ImageIO.read(new File("./cookie-2.png"));
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
		new Main();
	}

	public void run() {
		int lastSessionCookies = 0;
		int sessionCookies = 0;
		long startTime = System.currentTimeMillis() - 1000;
		long nextTime = System.currentTimeMillis() + 50;
		long secondTime = System.currentTimeMillis() + 1000;
		long time = System.currentTimeMillis() + 1000;
		long minuteTime = System.currentTimeMillis() + 64000;
		while (true) {
			if (nextTime - System.currentTimeMillis() < 0) {
				nextTime = System.currentTimeMillis() + 50;
				if (click) {
					pw.setCircleSize(190, 190, cookies);
					click = false;
					cookies = cookies.add(BigInteger.valueOf(multiplier));
					sessionCookies += multiplier;
					totalCookies++;
					if (totalCookies % 100 == 0) {
						multiplier += 1;
					}
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
				cookies = cookies.add(BigInteger.valueOf((long) basecps));
			}
			if (minuteTime - System.currentTimeMillis() < 0) {
				minuteTime = System.currentTimeMillis() + 64000;
				basecps += slavesPerMinute * 3;
			}
			cookieLabel.setText(" " + cookies);
		}
	}

	public boolean inCircle(int x, int y) {
		double dist = Math.sqrt(Math.pow((x - (getWidth() / 2)), 2) + Math.pow((y - (getHeight() / 2 + 10)), 2));
		return (dist < pw.width / 2);
	}

	public boolean inShopButton(int x, int y) {
		return (x > getWidth() / 4 && x < getWidth() / 4 + 120 && y < getHeight() / 2 + 48 && y > getHeight() / 2 + 16);
	}

	public boolean inPowerupButton(int x, int y) {
		return (x > getWidth() / 4 * 3 - 120 && x < getWidth() / 4 * 3 && y < getHeight() / 2 + 48
				&& y > getHeight() / 2 + 16);
	}

	public void paint(Graphics g) {
		super.paint(g);
		pw.repaint();
	}

	/*
	 * ~~~ Deep Dark Code ~~~
	 * 
	 * YOU HAVE BEEN WARNED
	 * 
	 * TURN BACK NOW
	 * 
	 * ~~~ ~~~~ ~~~~ ~~~~ ~~~
	 */

	public JFrame initShop() {
		JFrame shop = new JFrame("Shop");
		shop.setTitle("Shop");
		shop.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		shop.setSize(1440, 822);
		shop.setResizable(false);
		shop.setBackground(new Color(34, 34, 34));
		shop.setLayout(new GridLayout(4, 6));
		/*
		 * Note: These items are not to offend anyone in any way. This was a nice joke
		 * among a group of friends. The purpose is to show how everything on this list
		 * was legal AND acceptable-ish in the 1800s.
		 * 
		 * Child: Lack of child labor laws until 1938.
		 * Slave: Slavery wasn't abolished until 1865.
		 * Indentured Servant: Not abolished until 1917.
		 * Sharecropper: Sharecropping lasted until the 1950s.
		 * Overseer: Overseers were a part of slavery, so they weren't gone until 1865.
		 * Enforcer: Weren't a thing in context, just needed something else to put.
		 * Slave Catcher: Goal was to catch runaway slaves... also used until the abolition of slavery.
		 * Sex Slave: Not uncommon among slaveholders in the South.
		 */
		JButton[] shopItems = { new JButton("Child ( " + 10 + " )"), new JButton("Slave ( " + 100 + " )"),
				new JButton("Indentured Servant ( " + 1000 + " )"), new JButton("Sharecropper ( " + 10000 + " )"),
				new JButton("Overseer ( " + 100000 + " )"), new JButton("Enforcer ( " + 1000000 + " )"),
				new JButton("Slave Catcher ( " + 10000000 + " )"), new JButton("Sex Slave ( " + 100000000 + " )") };

		for (int i = 0; i < shopItems.length; i++) {
			shopItems[i].setFocusable(false);
			shopItems[i].addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					String sourceText = ((JButton) e.getSource()).getText();
					String[] sourceInfo = sourceText.replaceAll("\\( (.*?) \\)", "-$1").replaceAll(" ", "").split("-");
					BigInteger cookieCost = new BigInteger(sourceInfo[1]);
					String cookieName = sourceInfo[0];

					switch (cookieName.toLowerCase()) {
					case "child":
						if (cookies.compareTo(cookieCost) >= 0) {
							cookies = cookies.subtract(cookieCost);
							basecps += 1;
							cookieCost = cookieCost.add(cookieCost.divide(BigInteger.valueOf(2)));
						}
						((JButton) e.getSource()).setText("Child ( " + cookieCost + " )");
						break;
					case "slave":
						if (cookies.compareTo(cookieCost) >= 0) {
							cookies = cookies.subtract(cookieCost);
							basecps += 3;
							cookieCost = cookieCost.add(cookieCost.divide(BigInteger.valueOf(4)));
						}
						((JButton) e.getSource()).setText("Slave ( " + cookieCost + " )");
						break;
					case "indenturedservant":
						if (cookies.compareTo(cookieCost) >= 0) {
							cookies = cookies.subtract(cookieCost);
							basecps += 9;
							cookieCost = cookieCost.add(cookieCost.divide(BigInteger.valueOf(8)));
						}
						((JButton) e.getSource()).setText("Indentured Servant ( " + cookieCost + " )");
						break;
					case "sharecropper":
						if (cookies.compareTo(cookieCost) >= 0) {
							cookies = cookies.subtract(cookieCost);
							basecps += 27;
							cookieCost = cookieCost.add(cookieCost.divide(BigInteger.valueOf(16)));
						}
						((JButton) e.getSource()).setText("Sharecropper ( " + cookieCost + " )");
						break;
					case "overseer":
						if (cookies.compareTo(cookieCost) >= 0) {
							cookies = cookies.subtract(cookieCost);
							basecps += 81;
							cookieCost = cookieCost.add(cookieCost.divide(BigInteger.valueOf(32)));
						}
						((JButton) e.getSource()).setText("Overseer ( " + cookieCost + " )");
						break;
					case "enforcer":
						if (cookies.compareTo(cookieCost) >= 0) {
							cookies = cookies.subtract(cookieCost);
							basecps += 243;
							cookieCost = cookieCost.add(cookieCost.divide(BigInteger.valueOf(32)));
						}
						((JButton) e.getSource()).setText("Enforcer ( " + cookieCost + " )");
						break;
					case "slavecatcher":
						if (cookies.compareTo(cookieCost) >= 0) {
							cookies = cookies.subtract(cookieCost);
							slavesPerMinute += 1;
							cookieCost = cookieCost.add(cookieCost.divide(BigInteger.valueOf(32)));
						}
						((JButton) e.getSource()).setText("Slave Catcher ( " + cookieCost + " )");
						break;
					case "sexslave":
						if (cookies.compareTo(cookieCost) >= 0) {
							cookies = cookies.subtract(cookieCost);
							slavesPerMinute += 5;
							cookieCost = cookieCost.add(cookieCost.divide(BigInteger.valueOf(32)));
						}
						((JButton) e.getSource()).setText("Sex Slave ( " + cookieCost + " )");
						break;
					}

				}
			});
		}
		JLabel l = new JLabel("Cookies: ", SwingConstants.RIGHT);
		cookieLabel = new JLabel(" " + cookies, SwingConstants.LEFT);
		/* Row 1 */
		shop.add(new JPanel());
		shop.add(new JPanel());
		shop.add(l);
		shop.add(cookieLabel);
		shop.add(new JPanel());
		shop.add(new JPanel());
		/* Row 2 */
		shop.add(new JPanel());
		shop.add(shopItems[0]);
		shop.add(shopItems[1]);
		shop.add(shopItems[2]);
		shop.add(shopItems[3]);
		shop.add(new JPanel());
		/* Row 3 */
		shop.add(new JPanel());
		shop.add(shopItems[4]);
		shop.add(shopItems[5]);
		shop.add(shopItems[6]);
		shop.add(shopItems[7]);
		shop.add(new JPanel());
		/* Row 4 */
		shop.add(new JPanel());
		shop.add(new JPanel());
		shop.add(new JPanel());
		shop.add(new JPanel());
		shop.add(new JPanel());
		shop.add(new JPanel());

		shop.addWindowListener(new java.awt.event.WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent windowEvent) {
				shop.setVisible(false);
				setVisible(true);
			}
		});

		return shop;
	}
}
