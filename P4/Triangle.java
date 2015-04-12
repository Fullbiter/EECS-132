/**
 * Triangle objects represent triangle in 2D space.
 *
 * @author   Kevin Nash (kjn33)
 * @version  2015.4.11
 */
import java.awt.Graphics;

public class Triangle {
    
    /** the lines that form the triangle **/
    private Line[] lines = new Line[3];
    
    /**
     * Constructs a rectangle using three vertex points
     * @param  point1  the first vertex
     * @param  point2  the second vertex
     * @param  point3  the third vertex
     */
    public Rectangle(Point point1, Point point2, Point point3) {
        lines[0] = new Line(point1, point2);
        lines[1] = new Line(point2, point3);
        lines[2] = new Line(point3, point1);
    }
    
    /**
     * Constructs a triangle given a center and side length
     * @param  center  center
     * @param  length  side length
     */
    public Rectangle(Point center, int length) {
        Point point1 = new Point(center.getX() - length / 2, center.getY() - Math.sqrt(3) * length / 6);
        Point point2 = new Point(center.getX(), center.getY() - Math.sqrt(3) * length / 6);
        Point point3 = new Point(center.getX() + length / 2, center.getY() - Math.sqrt(3) * length / 6);
        lines[0] = new Line(point1, point2);
        lines[1] = new Line(point2, point3);
        lines[2] = new Line(point3, point1);
    }
    
    /**
     * Returns the lines associated with this line
     * @return  the Line array
     */
    public Line[] getLines() {
        return lines;
    }
    
    /**
     * Draws the triangle associated with the Triangle object
     * @param  graphics  the graphics context
     */
    public void draw(Graphics graphics) {
        for (Line line : lines)
            line.draw(graphics);
    }
}
