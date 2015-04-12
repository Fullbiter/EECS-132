/**
 * Triangle objects represent triangle in 2D space.
 *
 * @author   Kevin Nash (kjn33)
 * @version  2015.4.12
 */
import java.awt.Graphics;

public class Triangle extends Polygon {
    
    /**
     * Constructs a rectangle using three vertex points
     * @param  point1  the first vertex
     * @param  point2  the second vertex
     * @param  point3  the third vertex
     */
    public Triangle(Point point1, Point point2, Point point3) {
        // Tells the Polygon class that we have three edges
        super(3);
        super.defineLine(0, new Line(point1, point2));
        super.defineLine(1, new Line(point2, point3));
        super.defineLine(2, new Line(point3, point1));
    }
    
    /**
     * Constructs a triangle given a center and side length
     * @param  center  center
     * @param  length  side length
     */
    public Triangle(Point center, int length) {
        // Tells the Polygon class that we have three edges
        super(3);
        Point point1 = new Point(center.getX() - length / 2, center.getY() - (int)(Math.sqrt(3) * length / 6.0));
        Point point2 = new Point(center.getX(), center.getY() - (int)(Math.sqrt(3) * length / 6.0));
        Point point3 = new Point(center.getX() + length / 2, center.getY() - (int)(Math.sqrt(3) * length / 6.0));
        super.defineLine(0, new Line(point1, point2));
        super.defineLine(1, new Line(point2, point3));
        super.defineLine(2, new Line(point3, point1));
    }
}
