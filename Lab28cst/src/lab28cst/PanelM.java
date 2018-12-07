package lab28cst;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

public class PanelM extends JPanel {
	BufferedImage ladder = null;
	BufferedImage floor = null;
	BufferedImage barrel = null;
	BufferedImage hammer = null;
	BufferedImage sprite = null;
	String[] background;
	Player player;
	public PanelM(String[] background, Player player) {
		this.background = background;
		this.player = player;
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		for (int r = 0; r < 36; r++) {
			for (int c = 0; c < 50; c++) {
				switch (background[r].charAt(c)) {
				case '.':
					drawSpace(g, r, c);
					break;
				case '#':
					drawLadder(g, r, c);
					drawSpace(g, r, c);
					break;
				case '-':
					drawFloor(g, r, c);
					drawSpace(g, r, c);
					break;
				case '0':
					drawBarrel(g, r, c);
					drawSpace(g, r, c);
					break;
				case 'T':
					drawHammer(g, r, c);
					drawSpace(g, r, c);
					break;
				default:
					drawUnknown(g, r, c);
				}
			}
		}
		g.drawImage(sprite, player.getX(), player.getY(), 20, 20, null);
	}
	
	private void drawFloor(Graphics g, int r, int c) {
		g.drawImage(floor, c * 10 * 2, r * 10 * 2, 20, 20, null);
	}

	private void drawLadder(Graphics g, int r, int c) {
		g.drawImage(ladder, c * 10 * 2, r * 10 * 2, 20, 20, null);
	}

	private void drawBarrel(Graphics g, int r, int c) {
		g.drawImage(barrel, c * 10 * 2, r * 10 * 2, 20, 20, null);
	}

	private void drawHammer(Graphics g, int r, int c) {
		g.drawImage(hammer, c * 10 * 2, r * 10 * 2, 20, 20, null);
	}

	private void drawUnknown(Graphics g, int r, int c) {
		g.setColor(Color.red);
		g.fillRect(c * 10 * 2, r * 10 * 2, 20, 20);
	}

	private void drawSpace(Graphics g, int r, int c) {
		g.setColor(Color.black);
		g.fillRect(c * 10 * 2, r * 10 * 2, 20, 20);
	}
}
