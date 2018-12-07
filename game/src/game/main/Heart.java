package game.main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Heart extends GameObject {

	Handler handler;
	
	public Heart(int x, int y, ID id, Handler handler) {
		super(x, y, id);
		
		this.handler = handler;
	}

	public void tick() {
		
	}

	public void render(Graphics g) {
		g.setColor(Color.magenta);
		drawHeart(g, x, y, 28, 28);
	}

	public Rectangle getBounds() {
		return new Rectangle(x, y, 28, 28);
	}
	
	public void drawHeart(Graphics g, int x, int y, int width, int height) {
	    int[] triangleX = {
	            x - 2*width/18,
	            x + width + 2*width/18,
	            (x - 2*width/18 + x + width + 2*width/18)/2};
	    int[] triangleY = { 
	            y + height - 2*height/3, 
	            y + height - 2*height/3, 
	            y + height };
	    g.fillOval(
	            x - width/12,
	            y, 
	            width/2 + width/6, 
	            height/2); 
	    g.fillOval(
	            x + width/2 - width/12,
	            y,
	            width/2 + width/6,
	            height/2);
	    g.fillPolygon(triangleX, triangleY, triangleX.length);
	}

}
