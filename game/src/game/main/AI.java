package game.main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.ArrayList;

public class AI extends GameObject {
	
	Handler handler;
	ArrayList<Integer> moveX;
	ArrayList<Integer> moveY;

	public AI(int x, int y, ID id, Handler handler) {
		super(x, y, id);
		
		this.handler = handler;
		
		moveX = new ArrayList<Integer>();
		moveY = new ArrayList<Integer>();
	}

	public void tick() {
		
	}

	public Rectangle getBounds() {
		return new Rectangle(x, y, 28, 28);
	}
	
	public void collision() {
		for (int i = 0; i < handler.object.size(); i++) {
			GameObject obj = handler.object.get(i);
			if (obj.getID() == ID.BasicEnemy) {
				if (getBounds().intersects(obj.getBounds())) {
					HUD.HEALTH -= 10;
					handler.removeObject(obj);
				}
				continue;
			}
			if (obj.getID() == ID.Heart) {
				if (getBounds().intersects(obj.getBounds())) {
					HUD.HEALTH += 50;
					handler.removeObject(obj);
					Levels.presentHeart = false;
				}
			}
		}
	}

	public void render(Graphics g) {

		g.setColor(Color.orange);
		g.fillRect(x, y, 28, 28);

	}

	private void calculateNextMove() {
		/*
		 * 2 things should go into calculating the next move.
		 * 		1. Pseudorandom Velocities
		 * 		2. Modified velocities to favor going towards the heart and away from enemies
		 */
	}
}
