package main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

import javax.swing.JFrame;



public class Lab30ast
{    
	public static void main(String args[])  
	{
		GfxApp gfx = new GfxApp();
		gfx.setSize(800,600);
		gfx.addWindowListener(new WindowAdapter() { public void windowClosing(WindowEvent e) { System.exit(0);}});
		gfx.setLocationRelativeTo(null);
		gfx.setBackground(Color.white);
		gfx.setVisible(true);
	}
}




class GfxApp extends JFrame {
	private static final long serialVersionUID = 1L;
	private int circleCount, circleSize;
	
	public GfxApp() {
		circleCount = 50;
		circleSize  = 30;
	}
			

	class Coord {
		private int xPos;
		private int yPos;

		public Coord(int x, int y)  {
			xPos = x;
			yPos = y;
		}
		public String toString() {
			return "[" + xPos + ", " + yPos + "]";
		}
	}
   	
	public void paint(Graphics g) {
		int incX = 5;
		int incY = 5;
		int xp = 400;
		int yp = 300;
		int timeDelay = 10;
		int oldx, oldy;
		Circle c = new Circle(g,circleSize,incX,incY,timeDelay);
		Queue queue = new Queue();
		for (int k = 1; k <= 2000; k++) {
			queue.add(new Coord(xp, yp));
			c.color = Color.red;
			c.drawCircle(g);
			xp = c.getTLX();
			yp = c.getTLY();
			c.hitEdge();
			if (queue.size() > circleCount) {
				Coord temp2 = (Coord) queue.remove();
				oldx = c.getTLX();
				oldy = c.getTLY();
				c.setTLX(temp2.xPos);
				c.setTLY(temp2.yPos);
				c.color = Color.white;
				c.drawCircle(g);
				c.setTLX(oldx);
				c.setTLY(oldy);
			}
		}
	} 
}
      
      

class Circle {
	private int tlX;		// top-left X coordinate
	private int tlY;		// top-left Y coordinate
	private int incX;		// increment movement of X coordinate
	private int incY;		// increment movement of Y coordinate
	private boolean addX;	// flag to determine add/subtract of increment for X
	private boolean addY;	// flag to determine add/subtract of increment for Y
	private int size;		// diameter of the circle
	public Color color = Color.blue;
	private int timeDelay;	// time delay until next circle is drawn
	
	public Circle(Graphics g, int s, int x, int y, int td) {
		incX = x;
		incY = y;
		size = s;
		addX = true;
		addY = false;
		tlX = 400;
		tlY = 300;
		timeDelay = td;
	}
   
	public void delay(int n) {
		long startDelay = System.currentTimeMillis();
		long endDelay = 0;
		while (endDelay - startDelay < n)
			endDelay = System.currentTimeMillis();	
	}
	
	public void drawCircle(Graphics g) {
		g.setColor(color);
		g.drawOval(tlX,tlY,size,size);
		delay(timeDelay);
		if (addX)
			tlX+=incX;
		else
			tlX-=incX;
		if (addY)
			tlY+=incY;
		else
			tlY-=incY;
	}
   
   	 
	public void newData() {
		incX = (int) Math.round(Math.random() * 7 + 5);
		incY = (int) Math.round(Math.random() * 7 + 5);
	}

	public void hitEdge() {
		boolean flag = false;
		if (tlX < incX)
		{
			addX = true;
			flag = true;
		}
		if (tlX > 800 - (30 + incX)) 
		{
			addX = false;
			flag = true;
		}
		if (tlY < incY + 30) // The +30 is due to the fact that the title bar covers the top 30 pixels of the window
		{
			addY = true;
			flag = true;
		}
		if (tlY > 600 - (30 + incY)) 
		{
			addY = false;
			flag = true;
		}
		if (flag)
			newData();
	}
	
	public int getTLX() {
		return tlX;
	}
	public int getTLY() {
		return tlY;
	}
	public void setTLX(int x) {
		tlX = x;
	}
	public void setTLY(int y) {
		tlY = y;
	}
}


@SuppressWarnings({"rawtypes", "unchecked"})
class Queue {
	ArrayList queue;
	public Queue() {
		queue = new ArrayList<>();
	}
	
	public void add(Object i) {
		queue.add(i);
	}
	
	public Object remove() {
		return queue.remove(0);
	}
	
	public Object peek() {
		return queue.get(0);
	}
	
	public boolean element(Object i) { 
		return queue.contains(i);
	}
	
	public boolean isEmpty() {
		return queue.isEmpty();
	}
	
	public String toString() {
		return queue.toString();
	}
	public int size() {
		return queue.size();
	}
}

