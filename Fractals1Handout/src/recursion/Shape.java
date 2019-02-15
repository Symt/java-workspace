

package recursion;

import java.util.ArrayList;

// This class describes shapes (a polygon) as a sequence of points.
// This class is complete as written
public class Shape {
    public ArrayList<Point2> pts;
    public Shape(ArrayList<Point2> pts) {
        this.pts = pts;
    }
    // Code to create regular polygons (triangles, squares, pentagons, ...)
    public static Shape regularShape(int sides) {
        ArrayList<Point2> p = new ArrayList<Point2>();
        double dy=0;
        for (int i = 0; i < sides; i++) {
            double theta = (i+.5)*2*Math.PI/sides-Math.PI/2;
            double x = Math.cos(theta);
            double y = Math.sin(theta);
            if (i == 0) dy = -y;
            p.add(new Point2(x,y+dy));
        }
        return new Shape(p);
    }
}
