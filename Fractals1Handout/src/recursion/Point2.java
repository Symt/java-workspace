

package recursion;

public class Point2 {
    public double x, y;
    // fill in the following methods.  This class does
    // not change the x or y values - create new points to return.

    public Point2(double x, double y){
    	this.x = x;
    	this.y = y;
    }
    
    public Point2 scale(double s) {	
    	// Multiply x and y by s
    	return new Point2(x*s, y*s);
    }

    
    public Point2 add(Point2 p) {
        return new Point2 (x + p.x, y + p.y);
    }
    
    public Point2 interp(Point2 p, double t) {
        // This is interpolation
        // return a point between the given point and p.
        // For each coordinate, create a point with
        //    (1-t)*coordinate in current object + t*coordinate in p
    	
    	return (t == 0) ? this : (t == 1) ? p : new Point2((1-t)*x + t*p.x, (1-t)*y + t*p.y);
    	
    }

        
    public double dist(Point2 p) {
    	double y2 = p.y;
    	double x2 = p.x;
    	
    	Double distance = Math.sqrt((y2 - y) * (y2 - y) + (x2 - x) * (x2 - x));
    	return (Double) distance;

    }
    
    public String toString() { 
    	
    	// Return point in the form "(1,2)"
    	
    	return "(" + x + ", " + y + ")";   	
    }
    
 // Return the min of the x and y coords.
    public Point2 min(Point2 p) {  
        return new Point2(Math.min(p.x, x), Math.min(p.y, y));
    }
             
    
    public Point2 max(Point2 p) {
    	return new Point2(Math.max(p.x, x), Math.max(p.y, y));
    }
}
