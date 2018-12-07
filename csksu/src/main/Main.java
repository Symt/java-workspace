package main;

import java.awt.Dimension;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.Toolkit;
import java.util.Scanner;

public class Main {
	Window player1, player2, ball;
	double bvx = 0, bvy = 0;
	int velocity = 0;
	double angle = 0;
	int ballx, bally, width, height;

	public Main() {
		Scanner sc = new Scanner(System.in);
		System.out.print("Enter ball velocity: ");
		velocity = sc.nextInt();
		System.out.print("Enter velocity angle: ");
		angle = sc.nextDouble();
		player1 = new Window(new Dimension(80, 300));
		ball = new Window(new Dimension(80, 80));
		player2 = new Window(new Dimension(80, 300));
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		height = (int) screenSize.getHeight();
		width = (int) screenSize.getWidth();
		player1.setLocation(0, height / 2 - player1.frame.getHeight() / 2);
		player2.setLocation(width - player2.frame.getWidth(), height / 2 - player2.frame.getHeight() / 2);
		ball.setLocation(width / 2 - ball.frame.getHeight() / 2, height / 2 - ball.frame.getHeight() / 2);
		player1.display();
		player2.display();
		ball.display();
		sc.close();
		render();
	}

	public static void main(String... args) {
		new Main();
	}

	private void render() {
		ballx = width / 2;
		bally = height / 2;
		Point mouseinfo;
		bvx = (double) velocity * Math.sin(angle * Math.PI / 180);
		bvy = (double) velocity * Math.cos(angle * Math.PI / 180);
		float time = 0;
		float startTime = System.nanoTime();
		for (;;) {
			time = System.nanoTime();
			if (time - startTime > 20000000) {
				startTime = System.nanoTime();
			} else {
				continue;
			}
			mouseinfo = MouseInfo.getPointerInfo().getLocation();
			player1.setLocation(0, (int) mouseinfo.getY() - player1.frame.getHeight() / 2);
			ballx += bvx;
			bally += bvy;
			ball.setLocation(ballx, bally);
			collision();
			player2Move();
		}
	}

	private void collision() {
		if (overlaps(ball, player1)) {
			bvx *= -1;
			ballx = player1.frame.getX() + player1.frame.getWidth() + 1;
		}
		if (ball.frame.getY() <= 0) {
			bvy *= -1;
		}
		if (ball.frame.getY() + ball.frame.getHeight() >= height) {
			bvy *= -1;
			bally = height - ball.frame.getHeight();
		}
		if (ball.frame.getX() + ball.frame.getWidth() >= width - player2.frame.getWidth()) {
			bvx *= -1;
		}
	}

	private boolean overlaps(Window a, Window b) {
		int x1 = a.frame.getX();
		int y1 = a.frame.getY();
		int w1 = a.frame.getWidth();
		int h1 = a.frame.getHeight();
		int x2 = b.frame.getX();
		int y2 = b.frame.getY();
		int w2 = b.frame.getWidth();
		int h2 = b.frame.getHeight();
		return x1 < x2 + w2 && x1 + w1 > x2 && y1 < y2 + h2 && y1 + h1 > y2;
	}

	private void player2Move() {
		player2.setLocation(width - player2.frame.getWidth(), ball.frame.getY() - player2.frame.getHeight() / 2);
	}
}
