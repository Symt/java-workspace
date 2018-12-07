package game.main;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.util.concurrent.ThreadLocalRandom;

public class Main extends Canvas implements Runnable {

	public static final int WIDTH = 640;
	public static final int HEIGHT = WIDTH / 12 * 9;

	private static final long serialVersionUID = 4802830674381896798L;

	private Thread thread;
	private boolean running = false;
	private final int FRAMERATE = 60;
	private Handler handler;
	private HUD hud;

	private int rand(int low, int high) {
		return ThreadLocalRandom.current().nextInt(low, high);
	}

	public Main() {
		handler = new Handler();
		hud = new HUD();
		this.addKeyListener(new KeyInput(handler));
		new Window(WIDTH, HEIGHT, "Game", this);

		handler.addObject(new Player(WIDTH/2-32, HEIGHT/2-32, ID.Player, handler));
		handler.addObject(new BasicEnemy(rand(0, WIDTH), rand(0, HEIGHT), ID.BasicEnemy, handler));
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
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void run() {
		this.requestFocus();
		long lastTime = System.nanoTime();
		double amountOfTicks = (double) FRAMERATE;
		double ns = 1000000000 / amountOfTicks;
		double delta = 0;
		long timer = System.currentTimeMillis();
		int frames = 0;
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
			frames++;

			if (System.currentTimeMillis() - timer > 1000) {
				timer += 1000;
				//System.out.println("FPS: " + frames);
				frames = 0;
			}
		}
		stop();
	}

	private void tick() {
		handler.tick();
		hud.tick();
	}

	private void render() {
		BufferStrategy bs = this.getBufferStrategy();

		if (bs == null) {
			this.createBufferStrategy(3);
			return;
		}

		Graphics g = bs.getDrawGraphics();

		g.setColor(Color.black);
		g.fillRect(0, 0, WIDTH, HEIGHT);

		// Depth stuff
		handler.render(g);
		hud.render(g);

		g.dispose();
		bs.show();
	}
	
	public static int clamp(int var, int min, int max) {
		
		if (var >= max) {
			return max;
		} else if (var <= min) {
			return min;
		}
		return var;
	}

	public static void main(String[] args) {
		new Main();
	}
}
