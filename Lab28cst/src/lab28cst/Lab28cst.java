package lab28cst;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

public class Lab28cst {
	public static void main(String[] args) throws IOException {
		GfxApp gfx = new GfxApp();
		gfx.setSize(10 * 2 * 50, 2 * 10 * 35 + 10);
		gfx.setLocationRelativeTo(null);
		gfx.addWindowListener((WindowListener) new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});

		gfx.readFile();
		gfx.setVisible(true);
		gfx.start();
	}
}

@SuppressWarnings("serial")
class GfxApp extends JFrame {
	int numRows = 36; // 35 Rows are displayed. The top row
						// (row 0) is hidden behind the title bar.
	int numCols = 50;
	String[] background = new String[36];
	String fileName;
	BufferedImage ladder = null;
	BufferedImage floor = null;
	BufferedImage barrel = null;
	BufferedImage hammer = null;
	BufferedImage sprite = null;
	Player player;
	PanelM m;

	public GfxApp() {
		player = new Player(400, 400);
		this.addKeyListener(new KeyInput(player));
	}

	public void start() {
		run();
	}

	public void readFile() {
		File inFile = null;
		try {
			ladder = ImageIO.read(new File("ladder.png"));
			floor = ImageIO.read(new File("floor.png"));
			barrel = ImageIO.read(new File("barrel.png"));
			hammer = ImageIO.read(new File("hammer.png"));
			sprite = ImageIO.read(new File("sprite.png"));
			inFile = new File("image.dat");
		} catch (IOException e) {
			// Failure
		}
		int i = 0;
		if (inFile.exists()) {
			try {
				@SuppressWarnings("resource")
				BufferedReader inStream = new BufferedReader(new FileReader(inFile));

				String inString;
				while ((inString = inStream.readLine()) != null) {

					background[i] = inString;
					i++;
					if (inString.length() != 50) {
						throw new StringIndexOutOfBoundsException();
					}
				}
				inStream.close();
			} catch (Exception e) {
				System.out.println("Invalid Data File");
				System.exit(66);
			}
		} else {
			System.out.println("Data File Missing");
			System.exit(66);
		}
		m = new PanelM(background, player);
		m.setPreferredSize(new Dimension(getWidth(), getHeight()-20));
		add(m);
	}

	public void render() {	
		repaint();
	}

	public void run() {
		this.requestFocus();
		long lastTime = System.nanoTime();
		double amountOfTicks = (double) 60;
		double ns = 1000000000 / amountOfTicks;
		double delta = 0;
		long now;
		boolean running = true;

		while (running) {
			now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			while (delta >= 1) {
				player.tick();
				delta--;
			}
			render();
		}
	}
	public void paint(Graphics g) {
		m.repaint();
	}
}

class KeyInput extends KeyAdapter {

	private boolean[] keyDown = new boolean[4];
	Player player;
	int key;
	int key2;
	int vel = 2;
	int nvel = -2;

	public KeyInput(Player player) {
		this.player = player;
		keyDown[0] = false;
		keyDown[1] = false;
		keyDown[2] = false;
		keyDown[3] = false;
	}

	public void keyPressed(KeyEvent e) {
		key = e.getKeyCode();
		if (key == KeyEvent.VK_W) {
			if (!keyDown[1]) {
				player.setVelY(nvel);
				keyDown[0] = true;
			}
		}
		if (key == KeyEvent.VK_S) {
			if (!keyDown[0]) {
				player.setVelY(vel);
				keyDown[1] = true;
			}
		}
		if (key == KeyEvent.VK_A) {
			if (!keyDown[3]) {
				player.setVelX(nvel);
				keyDown[2] = true;
			}
		}
		if (key == KeyEvent.VK_D) {
			if (!keyDown[2]) {
				player.setVelX(vel);
				keyDown[3] = true;
			}
		}
		if (key == KeyEvent.VK_ESCAPE)
			System.exit(0);

	}

	public void keyReleased(KeyEvent e) {
		key2 = e.getKeyCode();
		if (key2 == KeyEvent.VK_W)
			keyDown[0] = false;
		if (key2 == KeyEvent.VK_S)
			keyDown[1] = false;
		if (key2 == KeyEvent.VK_A)
			keyDown[2] = false;
		if (key2 == KeyEvent.VK_D)
			keyDown[3] = false;

		if (!keyDown[0] && !keyDown[1])
			player.setVelY(0);
		if (!keyDown[2] && !keyDown[3])
			player.setVelX(0);
	}
}

class Player {
	private int xPixel;
	private int yPixel;
	private int xvel;
	private int yvel;

	public Player(int p1, int p2) {
		xPixel = p1;
		yPixel = p2;
		xvel = 0;
		yvel = 0;
	}

	public void tick() {
		xPixel += xvel;
		yPixel += yvel;
	}

	public void setVelX(int xvel) {
		this.xvel = xvel;
	}

	public void setVelY(int yvel) {
		this.yvel = yvel;
	}

	public int getX() {
		return xPixel;
	}

	public int getY() {
		return yPixel;
	}
}
