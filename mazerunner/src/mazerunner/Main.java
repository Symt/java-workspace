package mazerunner;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.filechooser.FileNameExtensionFilter;

public class Main extends JFrame {
	private static final long serialVersionUID = 1L;
	File file;
	String path;
	String contents;
	String[] split = new String[0];
	int[][] maze = new int[0][0];
	Point startPos = new Point(-1, -1);
	Point finishPos = new Point(-1, -1);
	public static final int wall = 0; // A wall in the maze
	public static final int open = 1; // Open space
	public static final int onpath = 3; // A place along the path from the start to the finish (extra credit only)
	public static final int start = 4; // The start of the maze
	public static final int end = 5; // The end of the maze

	public Main() {
		setSize(600, 700);
		String[] choice = { "Stack", "Queue" };
		JComboBox<String> cb = new JComboBox<String>(choice);
		JButton fileAdd = new JButton("Select File");
		JPanel panel = new JPanel();
		panel.setPreferredSize(new Dimension(getWidth(), 100));
		panel.add(cb, BorderLayout.SOUTH);
		panel.add(fileAdd, BorderLayout.NORTH);
		add(panel, BorderLayout.PAGE_END);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);

		fileAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser fileChooser = new JFileChooser();
				fileChooser.setFileFilter(new FileNameExtensionFilter("Data Files", "dat", "txt", "data"));
				int val = fileChooser.showOpenDialog(fileChooser);
				if (val == JFileChooser.APPROVE_OPTION) {
					file = fileChooser.getSelectedFile();
					path = file.getPath();
					try {
						contents = readFile(path, Charset.defaultCharset());
						split = contents.split("\n");
						maze = new int[split.length][split[0].length()];
						for (int i = 0; i < split.length; i++) {
							for (int k = 0; k < split[i].length(); k++) {
								try {
									switch (split[i].charAt(k)) {
									case '*':
										maze[i][k] = 0;
										break;
									case ' ':
										maze[i][k] = 1;
										break;
									case 'S':
										maze[i][k] = 4;
										break;
									case 'F':
										maze[i][k] = 5;
										break;
									default:
										throw new ArrayIndexOutOfBoundsException(); // Why not...
									}
								} catch (ArrayIndexOutOfBoundsException err) {
									System.out.println("Bad data file");
									System.exit(1);
								}
							}
						}
						int length = -1;
						for (int i = 0; i < split.length; i++) {
							if (length == -1) {
								length = split[i].length();
							}
							if (length != split[i].length()) {
								System.out.println("Bad Data File");
								System.exit(1);
							}
						}
						int startCount = 0;
						int finishCount = 0;
						for (int i = 0; i < maze.length; i++) {
							for (int k = 0; k < maze[i].length; k++) {
								if (maze[i][k] == 5) {
									finishPos = new Point(i, k);
									finishCount++;
								} else if (maze[i][k] == 4) {
									startPos = new Point(i, k);
									startCount++;
								}
							}
						}
						if (startCount != 1 || finishCount != 1) {
							System.out.println("Bad data file");
							System.exit(1);
						}
						if (cb.getSelectedItem().toString().equals("Stack")) {
							stackSolution();
						} else if (cb.getSelectedItem().toString().equals("Queue")) {
							queueSolution();
						} else {
							throw new Exception("No option for JComboBox (Exiting)");
						}
						repaint();
						setSize(new Dimension(split[0].length() * 12, split.length * 12 + 100 + 25));
						panel.setPreferredSize(new Dimension(getWidth(), 100));
						setLocationRelativeTo(null);
					} catch (Exception err) {
						err.printStackTrace();
						System.exit(1);
					}
				}
			}
		});
	}

	public void paint(Graphics g) {
		super.paint(g);
		for (int i = 0; i < maze.length; i++) {
			for (int j = 0; j < maze[i].length; j++) {
				if (maze[i][j] == 0) {
					g.setColor(Color.BLACK);
				} else if (maze[i][j] == 1) {
					g.setColor(Color.WHITE);
				} else if (maze[i][j] == 5) {
					g.setColor(Color.YELLOW);
				} else if (maze[i][j] == 4) {
					g.setColor(Color.BLUE);
				} else if (maze[i][j] == 3) {
					g.setColor(Color.GREEN);
				} else {
					throw new IllegalArgumentException("Bad data file");
				}
				g.fillRect(j * 12, i * 12 + 20, 12, 12);
			}
		}
	}

	public static void main(String... args) {
		new Main();
	}

	public String readFile(String path, Charset encoding) throws IOException {
		return new String(Files.readAllBytes(Paths.get(path)), encoding);
	}

	public void stackSolution() {
		Stack stack = new Stack();
		ArrayList<Point> locations = new ArrayList<Point>();
		stack.add(startPos);
		locations.add(startPos);
		int[][] moves = { { -1, 0 }, { 0, 1 }, { 1, 0 }, { 0, -1 } };
		while (!stack.isEmpty() && !stack.get().equals(finishPos)) {
			Point temp = stack.get();
			for (int x = 0; x < moves.length; x++) {
				if (maze[temp.x + moves[x][0]][temp.y + moves[x][1]] == 5) {
					return;
				}
				if (maze[temp.x + moves[x][0]][temp.y + moves[x][1]] == 1
						&& locations.indexOf(new Point(temp.x + moves[x][0], temp.y + moves[x][1])) == -1) {

					maze[temp.x + moves[x][0]][temp.y + moves[x][1]] = 3;
					stack.add(new Point(temp.x + moves[x][0], temp.y + moves[x][1]));
					locations.add(new Point(temp.x + moves[x][0], temp.y + moves[x][1]));
					break;
				}
			}
			if (stack.get().equals(temp)) {
				Point temp2 = stack.remove();
				maze[temp2.x][temp2.y] = 0;
			}
		}
	}

	public void queueSolution() {
		Queue queue = new Queue();
		int[][] moves = { { -1, 0 }, { 0, 1 }, { 1, 0 }, { 0, -1 } };
		queue.add(startPos);
		while (!queue.isEmpty() && !queue.get().equals(finishPos)) {
			Point temp = queue.remove();
			for (int x = 0; x < moves.length; x++) {
				if (maze[temp.x+moves[x][0]][temp.y+moves[x][1]] == 5) {
					return; // Found end
				}
				if (maze[temp.x + moves[x][0]][temp.y + moves[x][1]] == 1) {
					queue.add(new Point(temp.x + moves[x][0], temp.y + moves[x][1]));
					maze[temp.x + moves[x][0]][temp.y + moves[x][1]] = 3;
				}
			}
		}
	}

}