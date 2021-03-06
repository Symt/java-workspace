package main;

import java.awt.BorderLayout;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class Client extends JFrame {
	private static final long serialVersionUID = 1L;
	HostC frame;
	String board = "W-1-W-1-W-1-W-1 1-W-1-W-1-W-1-W W-1-W-1-W-1-W-1 B-W-B-W-B-W-B-W W-B-W-B-W-B-W-B 2-W-2-W-2-W-2-W W-2-W-2-W-2-W-2 2-W-2-W-2-W-2-W";
	String user;
	String serverData;
	boolean time = false;
	boolean end;
	String gameFinish = "EXIT";
	String[][] boardMatrix = new String[8][8];
	String host = "localhost";
	int port = 8081;

	public Client(int port) {
		this.port = port;
		frame = new HostC('1', null, this);
		frame.setSize(8 * 44, 8 * 44 + 21);
		frame.setResizable(false);
		formatBoard();
		frame.setBoard(boardMatrix);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
		frame.setTitle("Client");
		frame.setVisible(true);
		start();
	}

	public void start() {
		try (Socket socket = new Socket(host, port);
				PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
				BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));) {
			serverData = "";
			user = "";
			while ((serverData = in.readLine()) != null) {
				if (serverData.equals("EXIT2")) {
					frame.setVisible(false);
					JFrame loss = new JFrame("You Lose");
					loss.setTitle("You Lose");
					JLabel message = new JLabel("You lose!", SwingConstants.CENTER);
					loss.add(message, BorderLayout.CENTER);
					loss.setSize(300, 300);
					loss.setLocationRelativeTo(null);
					loss.setDefaultCloseOperation(EXIT_ON_CLOSE);
					loss.setVisible(true);
					break;
				} else if (serverData.equals("EXIT1")) {
					frame.setVisible(false);
					JFrame win = new JFrame("You Win");
					win.setTitle("You Win");
					JLabel message = new JLabel("You win!", SwingConstants.CENTER);
					win.add(message, BorderLayout.CENTER);
					win.setSize(300, 300);
					win.setLocationRelativeTo(null);
					win.setDefaultCloseOperation(EXIT_ON_CLOSE);
					win.setVisible(true);
					break;
				}
				board = serverData;
				formatBoard();
				frame.setBoard(boardMatrix);
				frame.repaint();
				time = false;
				while (!time) {
					Thread.sleep(100);
				}
				time = false;
				user = boardString();
				if (end) {
					user = gameFinish;
				}
				if (user != null) {
					out.println(user);
				}
			}
		} catch (UnknownHostException e) {
			System.out.println("Error: Bad Host");
		} catch (IOException e) {
			System.out.println("Error: Failure to obtain I/O for connection");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void formatBoard() {
		String[] rows = board.split(" ");
		for (int i = 0; i < rows.length; i++) {
			boardMatrix[boardMatrix.length - i - 1] = (String[]) reverse(rows[i].split("-"));
		}
	}

	public String boardString() {
		String result = "";
		for (int i = boardMatrix.length - 1; i >= 0; i--) {
			for (int j = boardMatrix[i].length - 1; j >= 0; j--) {
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
		new Client(8081);
	}

	public static <T> T[] reverse(T[] arr) {
		for (int i = 0; i < arr.length / 2; i++) {
			T temp = arr[i];
			arr[i] = arr[arr.length - i - 1];
			arr[arr.length - i - 1] = temp;
		}
		return arr;
	}
}