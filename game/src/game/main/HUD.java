package game.main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

public class HUD {
	
	public static int HEALTH = 100;
	private int level = 1;
	private double score;
	
	public void tick() {
		HEALTH = Main.clamp(HEALTH, 0, 100);
	}
	
	public void updateLevel(int level) {
		this.level = level;
	}
	
	public void updateScore(long time) {
		this.score = time / 100d;
	}
	
	public void render(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		
		g.setColor(Color.gray);
		g.fillRect(15, 15, 200, 32);
		
		g.setColor(Color.green);
		g.fillRect(15, 15, HEALTH*2, 32);
		
		g.setColor(Color.white);
		g.drawRect(15, 15, 200, 32);
		
		g2d.setColor(Color.white);
		g2d.drawString("Level: " + level, Main.WIDTH-100, 15);
		
		g2d.setColor(Color.white);
		g2d.drawString("Score: " + String.valueOf(score), Main.WIDTH-100, 45);
	}

}
