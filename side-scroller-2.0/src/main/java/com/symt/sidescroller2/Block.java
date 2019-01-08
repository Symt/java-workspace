package main.java.com.symt.sidescroller2;

import java.awt.Rectangle;

public class Block {
	int x, y, width, height;
	public Block(int x, int y, int width, int height) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}
	
	public void update(int xv, int yv) {
		x -= xv;
		y -= yv;
	}
	
	public Rectangle getBounds() {
		return new Rectangle(x, y, width, height);
	}
}
