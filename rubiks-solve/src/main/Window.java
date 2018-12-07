package main;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;

import logic.Moves;

public class Window extends JFrame implements KeyListener {

	double Z = 60 * Math.PI / 180;
	boolean going = false;
	private static final long serialVersionUID = 1L;
	private int[][][] cube;
	boolean shift = false;
	boolean[] moves = new boolean[10];
	JFrame popup;
	boolean popped = false;
	int push = 0;
	int[] xvert = { 100, 100, 200, 200 };
	int[] yvert = { 100, 200, 200, 100 };

	public Window(int[][][] the_cube) {
		cube = the_cube;
		String[] choices = { "TPerm" };
		// setSize(385, 80);
		setSize(600, 600);
		JComboBox<String> cb = new JComboBox<String>(choices);
		popup = new JFrame();
		JButton algo = new JButton("Do Algorithm");
		JButton reset = new JButton("Reset Cube");
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		popup.setSize(400, 200);
		JPanel panel = new JPanel();
		panel.add(cb, BorderLayout.CENTER);
		panel.add(algo, BorderLayout.CENTER);
		panel.add(reset, BorderLayout.SOUTH);
		popup.add(panel);
		popup.setLocationRelativeTo(null);
		popup.setVisible(false);
		setBackground(Color.black);
		setVisible(true);
		this.addKeyListener(this);

		algo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (cb.getSelectedItem().toString().equals("TPerm")) {
					moveSeries(Main.algorithms.get("tperm"));
				}
				popped = false;
				popup.setVisible(false);
				repaint();
			}
		});

		reset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cube = Main.solved;
				popped = false;
				popup.setVisible(false);
				repaint();
			}
		});
	}

	public void paint(Graphics g) {
		int pconst = getWidth()/2-17*3-63/2;
		int pconst2 = getHeight()/2-100;
		int push2 = 0;
		int push3 = 0;
		for (int j = 0; j < cube[1].length; j++) {
			for (int k = 0; k < cube[1][j].length; k++) {
				switch (cube[1][k][j]) {
				case 0:
					g.setColor(Color.WHITE);
					break;
				case 1:
					g.setColor(Color.GREEN);
					break;
				case 2:
					g.setColor(Color.RED);
					break;
				case 3:
					g.setColor(Color.BLUE);
					break;
				case 4:
					g.setColor(new Color(255, 140, 0));
					break;
				case 5:
					g.setColor(Color.YELLOW);
					break;
				}
				g.fillRect(j*20 + pconst + 63 + push3, k*20 + 20 + push2 + pconst2 + 8, 20, 20);
				push2+=5;
			}
			push3+=5;
			push2=0;
		}
		push3 = 0;
		for (int j = 0; j < cube[4].length; j++) {
			for (int k = 0; k < cube[4][j].length; k++) {
				switch (cube[4][k][j]) {
				case 0:
					g.setColor(Color.WHITE);
					break;
				case 1:
					g.setColor(Color.GREEN);
					break;
				case 2:
					g.setColor(Color.RED);
					break;
				case 3:
					g.setColor(Color.BLUE);
					break;
				case 4:
					g.setColor(new Color(255, 140, 0));
					break;
				case 5:
					g.setColor(Color.YELLOW);
					break;
				}
				int[] x = { 20 * j + pconst, 20 * j + pconst + (int) (20 * Math.cos(Math.PI / 180 * 30)),
						20 * j + pconst + (int) (20 * Math.cos(Math.PI / 180 * 30)), 20 * j + pconst };
				int[] y = { 20 * k + pconst2 + push + push2,
						20 * k + pconst2 + (int) (20 * Math.sin(Math.PI / 180 * 30)) + push + push2,
						20 * k + pconst2 + (int) (20 * Math.sin(Math.PI / 180 * 30)) + 20 + push + push2,
						20 * k + pconst2 + 20 + push + push2 };
				push2 += 5;
				g.fillPolygon(x, y, 4);
			}
			push += (int) (20 * Math.sin(Math.PI / 180 * 30));
			push2 = 0;
		}
		push = 0;
		for (int j = 0; j < cube[0].length; j++) {
			for (int k = 0; k < cube[0][j].length; k++) {
				switch (cube[0][k][j]) {
				case 0:
					g.setColor(Color.WHITE);
					break;
				case 1:
					g.setColor(Color.GREEN);
					break;
				case 2:
					g.setColor(Color.RED);
					break;
				case 3:
					g.setColor(Color.BLUE);
					break;
				case 4:
					g.setColor(new Color(255, 140, 0));
					break;
				case 5:
					g.setColor(Color.YELLOW);
					break;
				}
				int[] x = { 
						20 * j + pconst + 3 + push + push2 + 17*k, 
						20 * j + pconst + 23 + push + push2 + 17*k, 
						20 * j + pconst + 23 + (int)(20*Math.cos(Math.PI/180*30)) + push + push2 + 17*k,
						20 * j + pconst + 3 + (int)(20*Math.cos(Math.PI/180*30)) + push + push2 + 17*k, 
				};
				int[] y = { 
						20 * k + pconst2 - 3 - 9*k, 
						20 * k + pconst2 - 3 - 9*k,
						20 * k + pconst2 + 5 - 9*k, 
						20 * k + pconst2 + 5 - 9*k,
				};
				push2 += 5;
				g.fillPolygon(x, y, 4);
			}
			push += 5;
			push2 = 0;
		}
		push = 0;
		
		pconst2 = getHeight()/2+100;
		for (int j = 0; j < cube[2].length; j++) {
			for (int k = 0; k < cube[2][j].length; k++) {
				switch (cube[2][2-k][2-j]) {
				case 0:
					g.setColor(Color.WHITE);
					break;
				case 1:
					g.setColor(Color.GREEN);
					break;
				case 2:
					g.setColor(Color.RED);
					break;
				case 3:
					g.setColor(Color.BLUE);
					break;
				case 4:
					g.setColor(new Color(255, 140, 0));
					break;
				case 5:
					g.setColor(Color.YELLOW);
					break;
				}
				g.fillRect(j*20 + pconst + 63 + push3, k*20 + 20 + push2 + pconst2 + 8, 20, 20);
				push2+=5;
			}
			push3+=5;
			push2=0;
		}
		push3 = 0;
		for (int j = 0; j < cube[3].length; j++) {
			for (int k = 0; k < cube[3][j].length; k++) {
				switch (cube[3][2-k][2-j]) {
				case 0:
					g.setColor(Color.WHITE);
					break;
				case 1:
					g.setColor(Color.GREEN);
					break;
				case 2:
					g.setColor(Color.RED);
					break;
				case 3:
					g.setColor(Color.BLUE);
					break;
				case 4:
					g.setColor(new Color(255, 140, 0));
					break;
				case 5:
					g.setColor(Color.YELLOW);
					break;
				}
				int[] x = { 20 * j + pconst, 20 * j + pconst + (int) (20 * Math.cos(Math.PI / 180 * 30)),
						20 * j + pconst + (int) (20 * Math.cos(Math.PI / 180 * 30)), 20 * j + pconst };
				int[] y = { 20 * k + pconst2 + push + push2,
						20 * k + pconst2 + (int) (20 * Math.sin(Math.PI / 180 * 30)) + push + push2,
						20 * k + pconst2 + (int) (20 * Math.sin(Math.PI / 180 * 30)) + 20 + push + push2,
						20 * k + pconst2 + 20 + push + push2 };
				push2 += 5;
				g.fillPolygon(x, y, 4);
			}
			push += (int) (20 * Math.sin(Math.PI / 180 * 30));
			push2 = 0;
		}
		push = 0;
		for (int j = 0; j < cube[5].length; j++) {
			for (int k = 0; k < cube[5][j].length; k++) {
				switch (cube[5][2-j][k]) {
				case 0:
					g.setColor(Color.WHITE);
					break;
				case 1:
					g.setColor(Color.GREEN);
					break;
				case 2:
					g.setColor(Color.RED);
					break;
				case 3:
					g.setColor(Color.BLUE);
					break;
				case 4:
					g.setColor(new Color(255, 140, 0));
					break;
				case 5:
					g.setColor(Color.YELLOW);
					break;
				}
				int[] x = { 
						20 * j + pconst + 3 + push + push2 + 17*k, 
						20 * j + pconst + 23 + push + push2 + 17*k, 
						20 * j + pconst + 23 + (int)(20*Math.cos(Math.PI/180*30)) + push + push2 + 17*k,
						20 * j + pconst + 3 + (int)(20*Math.cos(Math.PI/180*30)) + push + push2 + 17*k, 
				};
				int[] y = { 
						20 * k + pconst2 - 3 - 9*k, 
						20 * k + pconst2 - 3 - 9*k,
						20 * k + pconst2 + 5 - 9*k, 
						20 * k + pconst2 + 5 - 9*k,
				};
				push2 += 5;
				g.fillPolygon(x, y, 4);
			}
			push += 5;
			push2 = 0;
		}
		push = 0;
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_SHIFT) {
			shift = true;
		} else if (e.getKeyCode() == KeyEvent.VK_R && !moves[0] && !popped && !going) {
			going = true;
			moves[0] = true;
			if (shift) {
				cube = Moves.right(cube, true);
			} else {
				cube = Moves.right(cube, false);
			}
			repaint();
		} else if (e.getKeyCode() == KeyEvent.VK_L && !moves[1] && !popped && !going) {
			going = true;
			moves[1] = true;
			if (shift) {
				cube = Moves.left(cube, true);
			} else {
				cube = Moves.left(cube, false);
			}
			repaint();
		} else if (e.getKeyCode() == KeyEvent.VK_U && !moves[2] && !popped && !going) {
			going = true;
			moves[2] = true;
			if (shift) {
				cube = Moves.up(cube, true);
			} else {
				cube = Moves.up(cube, false);
			}
			repaint();
		} else if (e.getKeyCode() == KeyEvent.VK_D && !moves[3] && !popped && !going) {
			going = true;
			moves[3] = true;
			if (shift) {
				cube = Moves.down(cube, true);
			} else {
				cube = Moves.down(cube, false);
			}
			repaint();
		} else if (e.getKeyCode() == KeyEvent.VK_F && !moves[4] && !popped && !going) {
			going = true;
			moves[4] = true;
			if (shift) {
				cube = Moves.front(cube, true);
			} else {
				cube = Moves.front(cube, false);
			}
			repaint();
		} else if (e.getKeyCode() == KeyEvent.VK_B && !moves[5] && !popped && !going) {
			going = true;
			moves[5] = true;
			if (shift) {
				cube = Moves.back(cube, true);
			} else {
				cube = Moves.back(cube, false);
			}
			repaint();
		} else if (e.getKeyCode() == KeyEvent.VK_M && !moves[6] && !popped && !going) {
			going = true;
			moves[6] = true;
			if (shift) {
				cube = Moves.middle(cube, true);
			} else {
				cube = Moves.middle(cube, false);
			}
			repaint();
		} else if (e.getKeyCode() == KeyEvent.VK_X && !moves[7] && !popped && !going) {
			going = true;
			moves[7] = true;
			if (shift) {
				cube = Moves.rotate(cube, 2);
			} else {
				cube = Moves.rotate(cube, 1);
			}
			repaint();
		} else if (e.getKeyCode() == KeyEvent.VK_Y && !moves[8] && !popped && !going) {
			going = true;
			moves[8] = true;
			if (shift) {
				cube = Moves.rotate(cube, 4);
			} else {
				cube = Moves.rotate(cube, 3);
			}
			repaint();
		} else if (e.getKeyCode() == KeyEvent.VK_Z && !moves[9] && !popped && !going) {
			going = true;
			moves[9] = true;
			if (shift) {
				cube = Moves.rotate(cube, 6);
			} else {
				cube = Moves.rotate(cube, 5);
			}
			repaint();
		} else if (e.getKeyCode() == KeyEvent.VK_P && !popped && !going) {
			going = true;
			popped = true;
			popup.setVisible(true);
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_SHIFT) {
			shift = false;
		} else if (e.getKeyCode() == KeyEvent.VK_R && moves[0]) {
			moves[0] = false;
		} else if (e.getKeyCode() == KeyEvent.VK_L && moves[1]) {
			moves[1] = false;
		} else if (e.getKeyCode() == KeyEvent.VK_U && moves[2]) {
			moves[2] = false;
		} else if (e.getKeyCode() == KeyEvent.VK_D && moves[3]) {
			moves[3] = false;
		} else if (e.getKeyCode() == KeyEvent.VK_F && moves[4]) {
			moves[4] = false;
		} else if (e.getKeyCode() == KeyEvent.VK_B && moves[5]) {
			moves[5] = false;
		} else if (e.getKeyCode() == KeyEvent.VK_M && moves[6]) {
			moves[6] = false;
		} else if (e.getKeyCode() == KeyEvent.VK_X && moves[7]) {
			moves[7] = false;
		} else if (e.getKeyCode() == KeyEvent.VK_Y && moves[8]) {
			moves[8] = false;
		} else if (e.getKeyCode() == KeyEvent.VK_Z && moves[9]) {
			moves[9] = false;
		} else {
			return;
		}
		going = false;
	}

	@Override
	public void keyTyped(KeyEvent e) {
	}

	public void moveSeries(String[] algo) {
		for (String i : algo) {
			switch (i) {
			case "R":
				cube = Moves.right(cube, false);
				break;
			case "R'":
				cube = Moves.right(cube, true);
				break;
			case "L":
				cube = Moves.left(cube, false);
				break;
			case "L'":
				cube = Moves.left(cube, true);
				break;
			case "U":
				cube = Moves.up(cube, false);
				break;
			case "U'":
				cube = Moves.up(cube, true);
				break;
			case "D":
				cube = Moves.down(cube, false);
				break;
			case "D'":
				cube = Moves.down(cube, true);
				break;
			case "F":
				cube = Moves.front(cube, false);
				break;
			case "F'":
				cube = Moves.front(cube, true);
				break;
			case "B":
				cube = Moves.back(cube, false);
				break;
			case "B'":
				cube = Moves.back(cube, true);
				break;
			case "M":
				cube = Moves.middle(cube, false);
				break;
			case "M'":
				cube = Moves.middle(cube, true);
				break;
			}
		}
	}
}
