package game.main;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.concurrent.ThreadLocalRandom;

public abstract class GameObject {

	protected int x, y;
	protected ID id;
	protected int velX, velY;
	
	public GameObject(int x, int y, ID id) {
		this.x = x;
		this.y = y;
		this.id = id;
	}
	
	public abstract void tick();
	public abstract void render(Graphics g);
	public abstract Rectangle getBounds();
	
	public void setX(int x) { this.x = x; }
	public void setY(int y) { this.y = y; }
	public int getX() { return x; }
	public int getY() { return y; }
	public void setID(ID id) { this.id = id; }
	public ID getID() { return id; }
	public void setVelX(int velX) { this.velX = velX; }
	public void setVelY(int velY) { this.velY = velY; }
	public int getVelX() { return velX; }
	public int getVelY() { return velY; }
	public int rand(int low, int high) {
		return ThreadLocalRandom.current().nextInt(low, high);
	}
	public double rand(double low, double high) {
		return ThreadLocalRandom.current().nextDouble() * (high - low) + low;
	}
}
