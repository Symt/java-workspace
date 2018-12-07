package game.main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Player extends GameObject {

	Handler handler;

	public Player(int x, int y, ID id, Handler handler) {
		super(x, y, id);

		this.handler = handler;
	}

	public Rectangle getBounds() {
		return new Rectangle(x, y, 28, 28);
	}

	public void tick() {

		if ((velX != 0 && velY == 0) || velY != 0 && velX == 0) {
			if (!noMore) {
				x += velX;
				y += velY;
			}
		} else {
			if (!noMore) {
				x += velX * rand(.5, 1.5);
				y += velY * rand(.5, 1.5);
			}
		}

		x = Main.clamp(x, 0, Main.WIDTH - 32);
		y = Main.clamp(y, 0, Main.HEIGHT - 56);
		collision();

		if (!Main.conservePower) {
			handler.addObject(new Trail(x, y, ID.Trail, handler, 28, 28, .05f, Color.blue));
		}
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

		g.setColor(Color.blue);
		g.fillRect(x, y, 28, 28);

	}
}
