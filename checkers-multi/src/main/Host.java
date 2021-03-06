package main;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class Host extends JFrame {

	private static final long serialVersionUID = 1L;
	ServerSocket serverSocket;
	boolean running = false;
	String board = "W-1-W-1-W-1-W-1 1-W-1-W-1-W-1-W W-1-W-1-W-1-W-1 B-W-B-W-B-W-B-W W-B-W-B-W-B-W-B 2-W-2-W-2-W-2-W W-2-W-2-W-2-W-2 2-W-2-W-2-W-2-W";
	String[][] boardMatrix = new String[8][8];
	HostC secondary;
	int port;
	String outputLine;
	boolean end = false;
	boolean time = false;
	String gameFinish = "EXIT";
	public Host(int port) {
		this.port = port;
		secondary = new HostC('2', this, null);
		formatBoard();
		secondary.setBoard(boardMatrix);
		secondary.setTitle("Client");
		secondary.setSize(8 * 44, 8 * 44 + 21);
		secondary.setResizable(false);
		secondary.setLocationRelativeTo(null);
		secondary.setDefaultCloseOperation(EXIT_ON_CLOSE);

		new JFrame("Host");
		setTitle("Host Server");
		JButton b = new JButton();
		b.setText("Start");

		b.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				secondary.setVisible(true);
				secondary.revalidate();
				secondary.repaint();
			}
		});
		JButton b2 = new JButton();
		b2.setText("End");
		b2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		add(b, BorderLayout.NORTH);
		add(b2, BorderLayout.SOUTH);
		setSize(200, 100);
		setResizable(false);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setVisible(true);
		start();
	}

	public void start() {
		try {
			serverSocket = new ServerSocket(port);
			Socket clientSocket = serverSocket.accept();
			PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
			BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
			String inputLine;
			outputLine = boardString();
			out.println(outputLine);
			while ((inputLine = in.readLine()) != null) {
				if (inputLine.equals("EXIT1")) {
					setVisible(false);
					secondary.setVisible(false);
					JFrame loss = new JFrame("You Lose");
					loss.setTitle("You Lose");
					JLabel message = new JLabel("You lose!", SwingConstants.CENTER);
					loss.add(message, BorderLayout.CENTER);
					loss.setSize(300, 300);
					loss.setLocationRelativeTo(null);
					loss.setDefaultCloseOperation(EXIT_ON_CLOSE);
					loss.setVisible(true);
				} else if (inputLine.equals("EXIT2")) {
					setVisible(false);
					secondary.setVisible(false);
					JFrame win = new JFrame("You Win");
					win.setTitle("You Win");
					JLabel message = new JLabel("You win!", SwingConstants.CENTER);
					win.add(message, BorderLayout.CENTER);
					win.setSize(300, 300);
					win.setLocationRelativeTo(null);
					win.setDefaultCloseOperation(EXIT_ON_CLOSE);
					win.setVisible(true);
				}
				board = inputLine;
				formatBoard();
				secondary.setBoard(boardMatrix);
				secondary.repaint();
				time = false;
				while (!time) {
					Thread.sleep(100);
				}
				time = false;
				outputLine = boardString();
				if (end) {
					outputLine = gameFinish;
				}
				if (outputLine != null) {
					out.println(outputLine);
				}
			}
		} catch (IOException e) {
			System.out.println("Exception caught while listening on port");
			System.out.println(e.getMessage());
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void formatBoard() {
		String[] rows = board.split(" ");
		for (int i = 0; i < rows.length; i++) {
			boardMatrix[i] = rows[i].split("-");
		}
	}

	public String boardString() {
		String result = "";
		for (int i = 0; i < boardMatrix.length; i++) {
			for (int j = 0; j < boardMatrix[i].length; j++) {
				result += boardMatrix[i][j] + "-";
			}
			result = result.substring(0, result.length() - 1);
			result += " ";
		}
		result = result.substring(0, result.length() - 1);
		return result;
	}

	public void updateBoard(String[][] board1) {
		boardMatrix = board1;
		board = boardString();
		if (!board.contains("1") && !board.contains("3") && (board.contains("2") || board.contains("4"))) {
			gameFinish = gameFinish + "2";
			time = true;
			end = true;
		} else if (!board.contains("2") && !board.contains("4") && (board.contains("1") || board.contains("3"))) {
			gameFinish = gameFinish + "1";
			time = true;
			end = true;
		}
	}
	public static void main(String[] args) {
		new Host(8081);
	}
}