package pong;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyInput extends KeyAdapter {
	private boolean[] keyDown = new boolean[4];
	private Paddle player;
	private Paddle player2;
	
	public KeyInput(Paddle player, Paddle player2) {
		keyDown[0] = false;
		keyDown[1] = false;
		keyDown[2] = false;
		keyDown[3] = false;
		this.player = player;
		this.player2 = player2;
	}
	
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		if (key == KeyEvent.VK_W) { // Up
			if (keyDown[0] == false) {
				keyDown[0] = true;
				player.yv = -10;
			}
		} else if (key == KeyEvent.VK_S) { // Down
			if (keyDown[1] == false)
			{
				keyDown[1] = true;
				player.yv = 10;
			}
		}
		if (key == KeyEvent.VK_UP) {
			if (keyDown[2] == false) {
				keyDown[2] = true;
				player2.yv = -10;
			}
		}
		else if (key == KeyEvent.VK_DOWN) {
			if (keyDown[3] == false) {
				keyDown[3] = true;
				player2.yv = 10;
			}
		}
	}
	
	public void keyReleased(KeyEvent e) {
		int key = e.getKeyCode();
		if (key == KeyEvent.VK_W) { // Up
			if (keyDown[0] == true) {
				keyDown[0] = false;
				player.yv = 0;
			}
		} else if (key == KeyEvent.VK_S) { // Down
			if (keyDown[1] == true) {
				keyDown[1] = false;
				player.yv = 0;
			}
		}
		if (key == KeyEvent.VK_UP) {
			if (keyDown[2] == true) {
				keyDown[2] = false;
				player2.yv = 0;
			}
		}
		else if (key == KeyEvent.VK_DOWN) {
				if (keyDown[3] == true) {
					keyDown[3] = false;
					player2.yv = 0;
				}
			}
		}
	}
