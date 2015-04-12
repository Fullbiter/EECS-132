/**
 * Point objects represent points in 2D space.
 *
 * @author   Kevin Nash (kjn33)
 * @version  2015.4.11
 */
import java.awt.Graphics;

public class Line {
    
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
        this.point1 = point1;
        this.point2 = point2;
    }
    
    /**
     * Constructs a line segment between the input points given by their cordinates
     * @param  x1  the x coordinate of the first point
     * @param  y1  the y coordinate of the first point
     * @param  x2  the x coordinate of the second point
     * @param  y2  the y coordinate of the second point
     */
    public Line(int x1, int y1, int x2, int y2) {
        point1 = new Point(x1, y1);
        point2 = new Point(x2, y2);
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
        point1 = point;
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
        point2 = point;
    }
    
    /**
     * Returns the lines associated with this line
     * @return  the Line array
     */
    public Line[] getLines() {
        return new Line[] {this};
    }
    
    /**
     * Draws the line associated with the Line object
     * @param  graphics  the graphics context
     */
    public void draw(Graphics graphics) {
        graphics.drawLine(point1.getX(), point1.getY(), point2.getX(), point2.getY());
    }
}
