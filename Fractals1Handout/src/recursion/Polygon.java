package recursion;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Arrays;

public class Polygon {

    // A polygon is a sequence of points (vertices) and a color.
    // To use the fillPolygon method, you need to fill in the integer arrays
    // of x and y coordinates.
    // instance variables for display

    public ArrayList<Point2> pts;
    public Color c;
    public double zoomfac = 1;
    public double panx = 0;
    public double pany = 0;

    public Polygon(Shape s, Point2 origin, Point2 first, Color c) {
        // Create a polygon based on a shape whose origin is at the given
        // point and whose first point is given - this stretches and rotates
        // the underlying shape
        pts = new ArrayList<Point2>();
        // Insert code here to calculate the points
        double dp, ds, scale, dx, dy, sin, cos;
        Point2 PP = new Point2(0, 0);
        Point2 SP;
        for (int i = 0; i < s.pts.size(); i++) {
        	dp = dist(origin, first);
        	ds = dist(PP, first);
        	scale = dp/ds;
        	dx = first.x - origin.x;
        	dy = first.y - origin.y;
        	sin = dy/dp;
        	cos = dx/dp;
        	SP = s.pts.get(i);
        	PP.x = scale*(SP.x*cos-SP.y*sin) + origin.x;
        	PP.y = scale*(SP.x*sin+SP.y*cos) + origin.y;
        	pts.add(PP);
        	PP = new Point2(0, 0);
        }
        this.c = c;
    }
    
    public double dist(Point2 a, Point2 b) {
    	return Math.sqrt(Math.pow(b.y - a.y, 2) + Math.pow(b.x - a.x, 2));
    }

    public void draw(Graphics g) {
    	int[] xp = new int[pts.size()];
		int[] yp = new int[pts.size()];
		int np = 0;

		g.setColor(c);

		// The code below will need to change once panning and zooming has been added.
		for (Point2 p : pts) {
			xp[np] = (int) ((p.x-panx)*zoomfac);
			yp[np] = (int) ((p.y-pany)*zoomfac);
			np++;
		}
		g.fillPolygon(xp, yp, np);
    }

    public ArrayList<Polygon> decorate(Shape s) {
    	ArrayList<Polygon> polyArray = new ArrayList<Polygon>();
        for (int i = 0; i < pts.size(); i++) {
            Point2 midPoint = pts.get(i).interp(pts.get((i + 1) % pts.size()), 0.5);
            Polygon poly = new Polygon(s, midPoint, pts.get((i + 1) % pts.size()), Color.YELLOW);
            polyArray.add(poly);

        }
    	return polyArray; 
    	
    }
      
}
