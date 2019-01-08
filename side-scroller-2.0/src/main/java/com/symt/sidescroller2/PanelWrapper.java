package main.java.com.symt.sidescroller2;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

public class PanelWrapper extends JPanel {
	private static final long serialVersionUID = 1L;
	Player p;
	int w, h;
	Handler handler;
	
	public PanelWrapper(int width, int height, Handler handler) {
		w = width;
		h = height;
		this.handler = handler;
	}
	public void updatePlayer(Player p, Handler h) {
		this.p = p;
		handler = h;
	}
	
	public void paint(Graphics g) {
		g.setColor(p.color);
		g.fillRect(p.x, p.y, p.width, p.height);
		g.setColor(Color.GREEN);
		g.fillRect(0, h-20, w, h);
		g.setColor(Color.BLACK);
		for (int i = 0; i < handler.size(); i++) {
			g.fillRect(handler.get(i).x, handler.get(i).y, handler.get(i).width, handler.get(i).height);
		}
	}
}
