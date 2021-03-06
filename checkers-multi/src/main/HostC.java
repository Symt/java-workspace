package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Arrays;

import javax.swing.JFrame;

public class HostC extends JFrame {
	int count = 0;
	private static final long serialVersionUID = 1L;
	int width, height;
	String[][] board = new String[8][8];
	int[] selected = { -1, -1 };
	PanelC panelc;
	char selectable = '0';
	Host h;
	Client c;

	public HostC(char type, Host host, Client client) {
		if ((host != null && client != null) || (host == null && client == null)) {
			new IllegalArgumentException("Host OR Client must be null, and the other must not be.");
		}
		if (host != null) {
			h = host;
		} else if (client != null) {
			c = client;
		}
		if (type == '1') {
			selectable = '1';
		} else if (type == '2') {
			selectable = '2';
		} else {
			System.out.println("BAD TYPE - BAD STUFF WILL HAPPEN");
		}
		panelc = new PanelC(this);
		width = 8 * 44;
		height = 8 * 44 + 21;
		panelc.setPreferredSize(new Dimension(width, height));
		add(panelc);
		setBackground(Color.white);
		this.addMouseListener(new MouseListener() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int x = e.getX();
				int y = e.getY();
				boolean done = false;
				for (int i = 65, index1 = 0; i <= height + 44 && index1 < 8; i += 44) {
					if (done) {
						break;
					}
					for (int j = 44, index2 = 0; j <= width + 44 && index2 < 8; j += 44) {
						if (x < j && y < i) {
							selected[0] = index2;
							selected[1] = index1;
							done = true;
							break;
						}
						index2++;
					}
					index1++;
				}
				if (board[selected[1]][selected[0]].length() == 2
						&& board[selected[1]][selected[0]].substring(1, 2).equals("s")) {
					board[selected[1]][selected[0]] = board[selected[1]][selected[0]].substring(0, 1);
				} else if (board[selected[1]][selected[0]].equals("W") || board[selected[1]][selected[0]].equals("B")) {
					possible_jump(selected[1], selected[0]);
				} else {
					if (selectable == '1' && (board[selected[1]][selected[0]].equals("1")
							|| board[selected[1]][selected[0]].equals("3"))) {
						for (int i = 0; i < board.length; i++) {
							board[i] = Arrays.stream(board[i]).map(s -> s.replace("1s", "1"))
									.toArray(size -> new String[size]);
							board[i] = Arrays.stream(board[i]).map(s -> s.replace("3s", "3"))
									.toArray(size -> new String[size]);
						}
						board[selected[1]][selected[0]] = board[selected[1]][selected[0]] + "s";
					} else if (selectable == '2' && (board[selected[1]][selected[0]].equals("2")
							|| board[selected[1]][selected[0]].equals("4"))) {
						for (int i = 0; i < board.length; i++) {
							board[i] = Arrays.stream(board[i]).map(s -> s.replace("2s", "2"))
									.toArray(size -> new String[size]);
							board[i] = Arrays.stream(board[i]).map(s -> s.replace("4s", "4"))
									.toArray(size -> new String[size]);
						}
						board[selected[1]][selected[0]] = board[selected[1]][selected[0]] + "s";
					}
				}
				if (selectable == '2') {
					h.updateBoard(board);
				} else if (selectable == '1') {
					c.updateBoard(board);
				}
				repaint();
			}

			@Override
			public void mouseEntered(MouseEvent e) {
			}

			@Override
			public void mouseExited(MouseEvent e) {
			}

			@Override
			public void mousePressed(MouseEvent e) {
			}

			@Override
			public void mouseReleased(MouseEvent e) {
			}
		});
	}

	public void possible_jump(int x, int y) {
		int sx = -1;
		int sy = -1;
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[i].length; j++) {
				if (((board[i][j].equals("1s") || board[i][j].equals("3s")) && selectable == '1')
						|| ((board[i][j].equals("2s") || board[i][j].equals("4s")) && selectable == '2')) {
					sx = i;
					sy = j;
					break;
				}
			}
		}
		if (board[x][y].equals("B")) {
			if (selectable == '1') {
				if (sx - 2 == x && sy + 2 == y
						&& (board[sx - 1][sy + 1].equals("2") || board[sx - 1][sy + 1].equals("4"))) {
					board[x][y] = (board[sx][sy].equals("1s")) ? "1" : "3";
					board[sx - 1][sy + 1] = "B";
					board[sx][sy] = "B";
					isJumpPossible(x, y, board[x][y]);
					c.time = true;
				} else if (sx - 2 == x && sy - 2 == y
						&& (board[sx - 1][sy - 1].equals("2") || board[sx - 1][sy - 1].equals("4"))) {
					board[x][y] = (board[sx][sy].equals("1s")) ? "1" : "3";
					board[sx - 1][sy - 1] = "B";
					board[sx][sy] = "B";
					isJumpPossible(x, y, board[x][y]);
					c.time = true;
				} else if (((sx - 1 == x && sy - 1 == y) || (sx - 1 == x && sy + 1 == y))) {
					board[x][y] = (board[sx][sy].equals("1s")) ? "1" : "3";
					board[sx][sy] = "B";
					c.time = true;
				} else if (sx + 2 == x && sy - 2 == y
						&& (board[sx + 1][sy - 1].equals("2") || board[sx + 1][sy - 1].equals("4"))
						&& board[sx][sy].equals("3s")) {
					board[sx - 1][sy + 1] = "B";
					board[sx][sy] = "B";
					board[x][y] = "3";
					isJumpPossible(x, y, board[x][y]);
					c.time = true;
				} else if (sx + 2 == x && sy + 2 == y
						&& (board[sx + 1][sy + 1].equals("2") || board[sx + 1][sy + 1].equals("4"))
						&& board[sx][sy].equals("3s")) {
					board[sx + 1][sy + 1] = "B";
					board[sx][sy] = "B";
					board[x][y] = "3";
					isJumpPossible(x, y, board[x][y]);
					c.time = true;
				} else if (((sx + 1 == x && sy + 1 == y) || (sx + 1 == x && sy - 1 == y))
						&& board[sx][sy].equals("3s")) {
					board[x][y] = (board[sx][sy].equals("1s")) ? "1" : "3";
					board[sx][sy] = "B";
					c.time = true;
				} else {
					return;
				}
				if (x == 0) {
					board[x][y] = "3";
				}
			} else if (selectable == '2') {
				if (sx - 2 == x && sy + 2 == y
						&& (board[sx - 1][sy + 1].equals("1") || board[sx - 1][sy + 1].equals("3"))) {
					board[x][y] = (board[sx][sy].equals("2s")) ? "2" : "4";
					board[sx - 1][sy + 1] = "B";
					board[sx][sy] = "B";
					isJumpPossible(x, y, board[x][y]);
					h.time = true;
				} else if (sx - 2 == x && sy - 2 == y
						&& (board[sx - 1][sy - 1].equals("1") || board[sx - 1][sy - 1].equals("3"))) {
					board[x][y] = (board[sx][sy].equals("2s")) ? "2" : "4";
					board[sx - 1][sy - 1] = "B";
					board[sx][sy] = "B";
					isJumpPossible(x, y, board[x][y]);
					h.time = true;
				} else if (((sx - 1 == x && sy - 1 == y) || (sx - 1 == x && sy + 1 == y))) {
					board[x][y] = (board[sx][sy].equals("2s")) ? "2" : "4";
					board[sx][sy] = "B";
					h.time = true;
				} else if (sx + 2 == x && sy - 2 == y
						&& (board[sx + 1][sy - 1].equals("1") || board[sx + 1][sy - 1].equals("3"))
						&& board[sx][sy].equals("4s")) {
					board[sx - 1][sy + 1] = "B";
					board[sx][sy] = "B";
					board[x][y] = "4";
					isJumpPossible(x, y, board[x][y]);
					h.time = true;
				} else if (sx + 2 == x && sy + 2 == y
						&& (board[sx + 1][sy + 1].equals("1") || board[sx + 1][sy + 1].equals("3"))
						&& board[sx][sy].equals("4s")) {
					board[sx + 1][sy + 1] = "B";
					board[sx][sy] = "B";
					board[x][y] = "4";
					isJumpPossible(x, y, board[x][y]);
					h.time = true;
				} else if (((sx + 1 == x && sy + 1 == y) || (sx + 1 == x && sy - 1 == y))
						&& board[sx][sy].equals("4s")) {
					board[x][y] = (board[sx][sy].equals("2s")) ? "2" : "4";
					board[sx][sy] = "B";
					h.time = true;
				} else {
					return;
				}
				if (x == 0) {
					board[x][y] = "4";
				}
			}
		}
	}

	public void isJumpPossible(int x, int y, String type) {
		String[] counter = new String[2];
		String[] king = { "3", "4" };
		if (type == "1" || type == "3") {
			counter = new String[] { "2", "4" };
		} else {
			counter = new String[] { "1", "3" };
		}
		boolean found;
		do {
			found = false;
			if (x - 2 >= 0 && y + 2 < 8) {
				if (Arrays.asList(counter).contains(board[x - 1][y + 1]) && board[x - 2][y + 2].equals("B")) {
					board[x - 2][y + 2] = type;
					board[x][y] = "B";
					board[x - 1][y + 1] = "B";
					found = true;
					x -= 2;
					y += 2;
					if (!Arrays.asList(king).contains(type) && x == 0) {
						board[x][y] = (type.equals("1")) ? "3" : "4";
					}
				}
			} else if (x - 2 >= 0 && y - 2 >= 0) {
				if (Arrays.asList(counter).contains(board[x - 1][y - 1]) && board[x - 2][y - 2].equals("B")) {
					board[x - 2][y - 2] = type;
					board[x][y] = "B";
					board[x - 1][y - 1] = "B";
					found = true;
					x -= 2;
					y -= 2;
					if (!Arrays.asList(king).contains(type) && x == 0) {
						board[x][y] = (type.equals("1")) ? "3" : "4";
					}
				}
			} else if (x + 2 < 8 && y - 2 >= 0 && Arrays.asList(king).contains(type)) {
				if (Arrays.asList(counter).contains(board[x + 1][y - 1]) && board[x + 2][y - 2].equals("B")) {
					board[x + 2][y - 2] = type;
					board[x][y] = "B";
					board[x + 1][y - 1] = "B";
					found = true;
					x += 2;
					y -= 2;
				}
			} else if (x + 2 < 8 && y + 2 < 8 && Arrays.asList(king).contains(type)) {
				if (Arrays.asList(counter).contains(board[x + 1][y + 1]) && board[x + 2][y + 2].equals("B")) {
					board[x + 2][y + 2] = type;
					board[x][y] = "B";
					board[x + 1][y + 1] = "B";
					found = true;
					x += 2;
					y += 2;
				}
			}

		} while (found);
	}

	public void paint(Graphics g) {
		panelc.repaint();
	}

	public void setBoard(String[][] board) {
		this.board = board;
	}
}
