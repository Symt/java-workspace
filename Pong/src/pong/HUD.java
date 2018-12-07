package pong;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

public class HUD {
	
	Paddle player;
	Paddle player2; 
	int width = 0;
	String text = "Sam & Charlie";
	
	public HUD(Paddle player, Paddle player2) {
		
		this.player = player;
		this.player2 = player2;
		
	}

	public void tick() {
		// Doesn't matter
	}
	
	public void render(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		
		g2d.setColor(Color.white);
		g2d.drawString(String.valueOf(player2.score), Main.fieldWidth-100, 45);
		g2d.drawString(String.valueOf(player.score), 100, 45);
		width = (width == 0) ? g.getFontMetrics().stringWidth(text) : width;
		g2d.drawString(text, Main.fieldWidth/2-width/2, 45);
	}

}
