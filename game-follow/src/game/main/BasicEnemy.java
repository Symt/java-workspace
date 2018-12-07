package game.main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class BasicEnemy extends GameObject {

	
	private Handler handler;
	
	public BasicEnemy(int x, int y, ID id, Handler handler) {
		super(x, y, id);
		this.handler = handler;
		
		velX = 5;
		velY = 5;
	}
	public Rectangle getBounds() {
		return new Rectangle(x, y, 16, 16);
	}

	public void tick() {
		x += velX * rand(.5, 1.5);
		y += velY * rand(.5, 1.5);
		
		if (y <= 0 || y >= Main.HEIGHT - 32) { 
			velY *= -1;
		}
		if (x <= 0 || x >= Main.WIDTH - 16) { 
			velX *= -1;
		}	
		
		handler.addObject(new Trail(x, y, ID.Trail, handler, 16, 16, .1f, Color.red));
	}

	public void render(Graphics g) {
		
		g.setColor(Color.red);
		g.fillRect(x, y, 16, 16);
	}

	
	
}
