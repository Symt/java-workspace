package main;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

public class PanelC extends JPanel {
	private static final long serialVersionUID = 1L;
	HostC hostc;

	public PanelC(HostC hostc) {
		this.hostc = hostc;
	}

	public void paintComponent(Graphics g) {
		String[][] board = hostc.board;
		super.paintComponent(g);
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[i].length; j++) {
				if (board[i][j].equals("B") || board[i][j].equals("W")) {
					switch (board[i][j]) {
					case "B":
						g.setColor(Color.black);
						break;
					case "W":
						g.setColor(Color.lightGray);
						break;
					}
					g.fillRect(44 * j, 44 * i, 44, 44);
				}
			}
		}
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[i].length; j++) {
				if (!board[i][j].contains("B") && !board[i][j].contains("W")) {
					g.setColor(Color.black);
					g.fillRect(44 * j, 44 * i, 44, 44);
					switch (board[i][j]) {
					case "1":
						g.setColor(Color.red);
						break;
					case "2":
						g.setColor(Color.white);
						break;
					case "3":
						g.setColor(Color.orange);
						break;
					case "4":
						g.setColor(Color.green);
						break;
					case "1s":
						g.setColor(Color.yellow);
						g.fillRect(44 * j, 44 * i, 44, 44);
						g.setColor(Color.red);
						break;
					case "2s":
						g.setColor(Color.yellow);
						g.fillRect(44 * j, 44 * i, 44, 44);
						g.setColor(Color.white);
						break;
					case "3s":
						g.setColor(Color.yellow);
						g.fillRect(44 * j, 44 * i, 44, 44);
						g.setColor(Color.orange);
						break;
					case "4s":
						g.setColor(Color.yellow);
						g.fillRect(44 * j, 44 * i, 44, 44);
						g.setColor(Color.green);
						break;
					}
					g.fillOval(44 * j + 2, 44 * i + 2, 40, 40);
				}
			}
		}
	}
}
