// Lab33ast.java
// This is the student Version of the Lab33a assignment.
package main;

import java.awt.Font;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Lab33ast {
	public static void main(String args[]) {
		GfxApp gfx = new GfxApp();
		gfx.setSize(1000, 750);
		gfx.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
		gfx.setVisible(true);
	}
}

class GfxApp extends Frame {

	private int td = 200; // time delay to slow down graphics display

	public void paint(Graphics g) {
		g.setFont(new Font("ARIAL", Font.BOLD, 28));
		g.drawString("LAB 33A 80/100 POINT VERSION", 300, 50);
		g.setFont(new Font("ARIAL", Font.BOLD, 20));
		g.drawString("DRAWING A LINKED LIST AS A STACK", 50, 215);
		g.drawString("DRAWING A LINKED LIST AS A QUEUE",50,415);
		drawStack(g);
		drawQueue(g);
	}

	public void drawStack(Graphics g) {
		GfxNode temp = null;
		GfxNode node1 = new GfxNode(g, 200 + 300, 200, 'P', 0, td);
		node1.enterData(g, 'F', 0);
		temp = node1;
		node1 = new GfxNode(g, 250 + 300, 200, ' ', 0, td);
		node1.enterData(g, 'E', 0);
		temp.drawLink(g, node1, 0);
		temp = node1;
		node1 = new GfxNode(g, 300 + 300, 200, ' ', 0, td);
		node1.enterData(g, 'D', 0);
		temp.drawLink(g, node1, 0);
		temp = node1;
		node1 = new GfxNode(g, 350 + 300, 200, ' ', 0, td);
		node1.enterData(g, 'C', 0);
		temp.drawLink(g, node1, 0);
		temp = node1;
		node1 = new GfxNode(g, 400 + 300, 200, ' ', 0, td);
		node1.enterData(g, 'B', 0);
		temp.drawLink(g, node1, 0);
		temp = node1;
		node1 = new GfxNode(g, 450 + 300, 200, ' ', 0, td);
		node1.enterData(g, 'A', 0);
		node1.drawNull(g, 9);
		temp.drawLink(g, node1, 0);
		temp = node1;
	}

	public void drawQueue(Graphics g) {
		GfxNode temp = null;
		GfxNode node1 = new GfxNode(g, 200 + 300, 400, 'F', 0, td);
		node1.enterData(g, 'A', 0);
		temp = node1;
		node1 = new GfxNode(g, 250 + 300, 400, ' ', 0, td);
		node1.enterData(g, 'B', 0);
		temp.drawLink(g, node1, 0);
		temp = node1;
		node1 = new GfxNode(g, 300 + 300, 400, ' ', 0, td);
		node1.enterData(g, 'C', 0);
		temp.drawLink(g, node1, 0);
		temp = node1;
		node1 = new GfxNode(g, 350 + 300, 400, ' ', 0, td);
		node1.enterData(g, 'D', 0);
		temp.drawLink(g, node1, 0);
		temp = node1;
		node1 = new GfxNode(g, 400 + 300, 400, ' ', 0, td);
		node1.enterData(g, 'E', 0);
		temp.drawLink(g, node1, 0);
		temp = node1;
		node1 = new GfxNode(g, 450 + 300, 400, 'P', 0, td);
		node1.enterData(g, 'F', 0);
		node1.drawNull(g, 9);
		temp.drawLink(g, node1, 0);
		temp = node1;
	}

}