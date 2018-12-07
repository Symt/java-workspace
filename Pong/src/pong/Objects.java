package pong;

import java.awt.Graphics;
import java.awt.Rectangle;

public abstract class Objects {

	protected int x, y, xv, yv;
	
	public Objects(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public abstract void tick();
	public abstract void render(Graphics g);
	public abstract Rectangle getBounds();
}
