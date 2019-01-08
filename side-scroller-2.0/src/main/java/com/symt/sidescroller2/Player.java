package main.java.com.symt.sidescroller2;

import java.awt.Color;
import java.awt.Rectangle;

public class Player {
	Color color;
	int x, y, frameWidth, frameHeight, width, height;
	public static int xv, yv;
	boolean jumping, falling;
	public Handler handler;

	public Player(Color color, Handler handler, int x, int y, int width, int height, int pwidth, int pheight) {
		this.color = color;
		this.handler = handler;
		this.x = x;
		this.y = y;
		falling = false;
		jumping = false;
		frameHeight = height;
		frameWidth = width;
		this.width = pwidth;
		this.height = pheight;
		xv = 5;
	}

	public void tick() {

		if (yv <= -20 && jumping) {
			falling = true; 
		}
		System.out.println(yv);
		gravity();
		collision();
	}

	private void collision() {
		if (y + height > frameHeight - 20) {
			yv = 0;
			y = frameHeight - 20 - height;
			jumping = false;
		} else if (y + height > handler.get(0).y && y < handler.get(0).y && handler.get(0).x < x + width) {
			yv = 0;
			y = handler.get(0).y - height;
			jumping = false;
		} else if (handler.get(0).x + handler.get(0).width <= x) {
			yv = 1;
			jumping = true;
		} else if (handler.get(0).getBounds().intersects(getBounds()) && handler.get(0).y < y + width) {
			for (int i = 0; i < handler.size(); i++) {
				handler.get(i).x += ((x + width) - handler.get(0).x) - xv;
			}
			xv = 0;
		} else if (handler.get(0).y > y + height && xv == 0) {
			xv = 5;
		} else {
			y += yv;
		}
		handler.tick();

	}

	public Rectangle getBounds() {
		return new Rectangle(x, y, width, height);
	}

	private void gravity() {
		if (jumping) {
			if (falling && yv < 20) {
				yv += 3;
				if (yv >= 14) {
					yv += 2;
				} else if (yv >= 17) {
					yv += 1;
				}
			} else if (jumping && yv > -20 && yv <= 0) {
				yv -= 3;
				if (yv <= -14) {
					yv -= 2;
				} else if (yv <= -17) {
					yv -= 1;
				}
			}
		} else {
			yv = 0;
		}
	}

	public void jump() {
		if (!jumping) {
			jumping = true;
			falling = false; 
			yv = 0;
		}
	}
}
