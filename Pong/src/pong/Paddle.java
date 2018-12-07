package pong;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Paddle extends Objects {
	
	public int score = 0;

	public Paddle(int x, int y) {
		super(x, y);
	}

	@Override
	public void tick() {
		swapY();
	}
	public void swapY() {
		y += yv;
		if (y <= -5) {
			y -= yv;
		} else if (y >= (Main.fieldHeight-Main.paddleHeight)) {
			y -= yv;
		} 
		
	}
	
	@Override
	public void render(Graphics g) {
		g.setColor(Color.GREEN);
		
		g.fillRect(x, y, Main.paddleWidth, Main.paddleHeight);
	}

	@Override
	public Rectangle getBounds() {
		return new Rectangle(x, y, Main.paddleWidth, Main.paddleHeight);
	}

}
