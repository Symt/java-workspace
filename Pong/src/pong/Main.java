package pong;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.util.concurrent.ThreadLocalRandom;

public class Main extends Canvas implements Runnable {
	private static final long serialVersionUID = 1L;
	Thread thread;
	public static boolean running = false;
	Window window;
	public static boolean gone = false;
	public static final int paddleWidth = 7;
	public static final int paddleHeight = 100;
	public static final int fieldWidth = 800;
	public static final int fieldHeight = 600;
	public static final int leftPaddlex = 15;
	public static final int rightPaddlex = fieldWidth - 15 - paddleWidth/2;
	Ball ball;
	Paddle paddle;
	Paddle paddle2;
	HUD hud;

	public Main() {
		window = new Window(this);
		
		paddle = new Paddle(leftPaddlex, fieldHeight/2-paddleHeight/2);
		paddle2 = new Paddle(rightPaddlex, fieldHeight/2-paddleHeight/2);
		hud = new HUD(paddle, paddle2);
		ball = new Ball(400, 300, 15, 15, paddle, paddle2);
		
		this.addKeyListener(new KeyInput(paddle, paddle2));
		this.addMouseListener(new MouseInput());
	}
	
	public static int random(int a, int b) {
		return ThreadLocalRandom.current().nextInt(a, b);
	}
	
	public synchronized void start() {
		thread = new Thread(this);
		running = true;
		thread.start();
	}

	public synchronized void stop() {
		try {
			thread.join();
			running = false;
			System.exit(0);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void run() {
		this.requestFocus();
		long lastTime = System.nanoTime();
		double amountOfTicks = (double) 60;
		double ns = 1000000000 / amountOfTicks;
		double delta = 0;
		long timer = System.currentTimeMillis();
		long now;

		while (running) {
			now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			
			while (delta >= 1) {
				tick();
				delta--;
			}

			if (running) {
				render();
			}

			if (System.currentTimeMillis() - timer > 1000) {
				timer += 1000;
			}
		}
		System.exit(0);
	}

	private void tick() {
		if (gone) {
			paddle.tick();
			paddle2.tick();
			ball.tick();
		}
	}

	private void render() {
		BufferStrategy bs = this.getBufferStrategy();

		if (bs == null) {
			this.createBufferStrategy(3);
			return;
		}

		Graphics g = bs.getDrawGraphics();

		g.setColor(Color.BLACK);
		g.fillRect(0, 0, 800, 600);

		ball.render(g);
		paddle.render(g);
		paddle2.render(g);
		hud.render(g);
		
		gone = true;
		
		g.dispose();
		bs.show();
	}

	public static void main(String[] args) {
		new Main();
	}
}
