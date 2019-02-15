

package recursion;

import java.awt.Graphics;
import java.util.ArrayList;


public class Fractal {
    public int depth;
    public int n;
    public Settings settings;
    public Polygon here;
    public ArrayList<Fractal> kids = new ArrayList<Fractal>();
    public Fractal(Settings settings) {
        this.settings = settings;
        this.here = settings.initialPolygon();
        this.depth = 0;
        this.n = 0;
        expand();
    }
    public Fractal(Settings settings, Polygon here, int depth, int n) {
        this.settings = settings;
        this.here = here;
        this.depth = depth;
        this.n = n;
        expand();
    }
    public void expand() {
        // Decide whether to expand the fractal another level.
    }

    public void draw(Graphics g) {
        // Draw the polygon in the fractal as well as all sub-fractals
    }

    public Fractal decorate(Point2 p1, Point2 p2, int i) {
       // Create a sub-fractal along a side of a polygon.  p1 and p2 are the side; i is the
       // side number (used in coloring).
        return null;
    }

}
