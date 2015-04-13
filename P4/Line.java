/**
 * Line objects represent line segments in 2D space.
 *
 * @author   Kevin Nash (kjn33)
 * @version  2015.4.11
 */
import java.awt.Graphics;

public class Line extends Polygon {
    
    /** the first point of the line segment **/
    private Point point1;
    /** the second point of the line segment **/
    private Point point2;
    
    /**
     * Constructs a line segment between the input points
     * @param  point1  the first point
     * @param  point2  the second point
     */
    public Line(Point point1, Point point2) {
        // Tells the Polygon class that we have one edge
        super(1);
        this.point1 = point1;
        this.point2 = point2;
        super.defineLine(0, new Line(point1, point2));
    }
    
    /**
     * Constructs a line segment between the input points given by their cordinates
     * @param  x1  the x coordinate of the first point
     * @param  y1  the y coordinate of the first point
     * @param  x2  the x coordinate of the second point
     * @param  y2  the y coordinate of the second point
     */
    public Line(int x1, int y1, int x2, int y2) {
        // Tells the Polygon class that we have one edge
        super(1);
        this.point1 = new Point(x1, y1);
        this.point2 = new Point(x2, y2);
        super.defineLine(0, new Line(point1, point2));
    }
    
    /**
     * Returns the first point of the line segment
     * @return  the first point
     */
    public Point getFirstPoint() {
        return point1;
    }
    
    /**
     * Sets the first point of the line segment
     * @param  point  the new first point
     */
    public void setFirstPoint(Point point) {
        this.point1 = point;
    }
    
    /**
     * Returns the second point of the line segment
     * @return  the second point
     */
    public Point getSecondPoint() {
        return point2;
    }
    
    /**
     * Sets the second point of the line segment
     * @param  point  the new second point
     */
    public void setSecondPoint(Point point) {
        this.point2 = point;
    }
}
