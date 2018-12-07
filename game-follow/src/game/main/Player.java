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
		return new Rectangle(x, y, 32, 32);
	}
	
	public void tick() {
		x += velX;
		y += velY;
		
		x = Main.clamp(x, 0, Main.WIDTH - 32);
		y = Main.clamp(y, 0, Main.HEIGHT - 56);
		collision();
		
		handler.addObject(new Trail(x, y, ID.Trail, handler, 32, 32, .05f, Color.green));
	}
	
	public void collision() {
		for (GameObject obj : handler.object) {
			if (obj.getID() == ID.BasicEnemy) {
				if (getBounds().intersects(obj.getBounds())) {
					HUD.HEALTH-=2;
				}	
			}
		}
	}

	public void render(Graphics g) {
		
		g.setColor(Color.green);
		g.fillRect(x, y, 32, 32);
		
	}
	
		

}
