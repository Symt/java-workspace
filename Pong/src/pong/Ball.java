package pong;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Ball extends Objects {

	int width, height;
	int yv = 0;
	int xv = 10;
	Paddle player, player2;
	double angle;
	double amount = 7;
	int bounces = 1;
	
	public void collision() {
		if (getBounds().intersects(player.getBounds())) {
			xv *= -1;
			yv *= Main.random(1, 2);
		} else if (getBounds().intersects(player2.getBounds())) {
			xv *= -1;
			yv *= Main.random(1, 2);
		} else {
			return;
		}
		bounces++;
	}
	
	public Ball(int x, int y, int width, int height, Paddle player, Paddle player2) {
		super(x, y);
		this.height = height;
		this.width = width;
		this.player = player;
		this.player2 = player2;
		updateLocations();
	}

	@Override
	public void tick() {
		if (bounces >= 5) {
			bounces = 0;
			amount++;
		}
		swapX();
		x += xv;
		swapY();
		y += yv;
		collision();
	}
	
	private void swapX() {
		if (x > Main.fieldWidth) {
			player.score++;
		} else if (x < 0) {
			player2.score++;
		} else {
			return;
		}
		updateLocations();
	}
	
	private void updateLocations() {
		amount = 10;
		x = Main.fieldWidth/2-width/2;
		y = Main.fieldHeight/2-height/2;
		player.x = Main.leftPaddlex;
		player.y = Main.fieldHeight/2-Main.paddleHeight/2;
		player2.x = Main.rightPaddlex;
		player2.y = Main.fieldHeight/2-Main.paddleHeight/2;
		angle = Math.toRadians(Math.random() * 360);
		xv = 0;
		yv = (int)(amount * Math.sin(angle));
		while (xv <= 3 && xv >= -3) {
			xv = (int)(amount * Math.cos(angle));
			angle = Math.toRadians(Math.random() * 360);
		}
	}
	
	private void swapY() {
		if (y > Main.fieldHeight-height*3/2 || y < 0) {
			yv *= -1;
		}
	}

	@Override
	public void render(Graphics g) {
		g.setColor(Color.WHITE);
		g.drawOval(x, y, width, height);
	}

	@Override
	public Rectangle getBounds() {
		return new Rectangle(x, y, width, height);
	}

}
