
package recursion;

import java.awt.Color;

// This class is used to store the settings that define the picture.
// No changed are needed here.

public interface Settings {
    public Polygon initialPolygon();
    public boolean addFractal(Fractal f);
    public Color getColor(Fractal f, int i);
    public Shape getShape(Fractal f, int i);
    public boolean insideEdge(Fractal f);
    public Point2 getOrigin(Fractal f, Point2 p1, Point2 p2);
    public Point2 getFirstPoint(Fractal f, Point2 p1, Point2 p2);

}
