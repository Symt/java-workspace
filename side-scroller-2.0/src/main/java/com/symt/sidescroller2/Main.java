package main.java.com.symt.sidescroller2;

import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.concurrent.ThreadLocalRandom;

import javax.swing.JFrame;

public class Main extends JFrame {
	private static final long serialVersionUID = 1L;
	boolean done = false;
	boolean spaceDown = false;
	PanelWrapper pw;
	Player player;
	Handler handler = new Handler();;
	
	public Main() {
		
		addKeyListener(new KeyListener() {

			@Override
			public void keyPressed(KeyEvent arg0) {
				if (arg0.getKeyCode() == KeyEvent.VK_SPACE && !spaceDown) {
					spaceDown = true;
					player.jump();
				}
			}

			@Override
			public void keyReleased(KeyEvent arg0) {
				if (arg0.getKeyCode() == KeyEvent.VK_SPACE && spaceDown) {
					spaceDown = false;
				}
			}

			@Override
			public void keyTyped(KeyEvent arg0) {}
		
		});
		
		setSize(800,600);
		pw = new PanelWrapper(this.getWidth(), this.getHeight()-20, handler);
		player = new Player(Color.blue, handler, 50, 0, this.getWidth(), this.getHeight()-20, 30, 30);
		pw.setSize(this.getWidth(), this.getHeight()-20);
		pw.setBackground(new Color(0x87CEEB));
		pw.updatePlayer(player, handler);
		add(pw);
		setTitle("Side Scroller");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		player.y = pw.getHeight()-50;
		setResizable(false);
		setLocationRelativeTo(null);
		setVisible(true);
		
		repaint();
		render();
	}
	
	
	public void render() {
		int fps = 60;
		double timeNow = System.currentTimeMillis() + 1000d/fps;
		long now;
		while (!done) { 
			now = System.currentTimeMillis();
			if (timeNow - now <= 0) {
				if (handler.size() == 0) {
					handler.add(new Block(pw.getWidth(), pw.getHeight()-random(100, 150), 50, 200));
				} else if (handler.get(0).x  + handler.get(0).width < pw.getWidth()/2 && handler.size() == 1) {
					handler.add(new Block(pw.getWidth(), pw.getHeight()-random(100, 150), 50, 200));
				}
				if (handler.get(0).width + handler.get(0).x < 0) {
					handler.remove(0);
				}
				timeNow = now + 1000d/fps;
				player.handler = handler;
				player.tick();
				pw.updatePlayer(player, handler);
				pw.repaint();
			}
		}
	}
	
	public static void main(String args[]) {
		new Main();
	}
	public static int random(int low, int high) {
		return ThreadLocalRandom.current().nextInt(low, high);
	}
}
