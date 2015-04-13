/**
 * Rectagle objects represent rectangles in 2D space.
 *
 * @author   Kevin Nash (kjn33)
 * @version  2015.4.11
 */
import java.awt.Graphics;

public class Rectangle extends Polygon {
    
    /** the rectangle origin x coordinate (left) **/
    private int x = 0;
    /** the rectangle origin y coordinate (bottom) **/
    private int y = 0;
    /** the rectangle's width **/
    private int width = 0;
    /** the rectangle's height **/
    private int height = 0;
    
    /**
     * Constructs a rectangle using two vertex points
     * @param  point1  the first vertex
     * @param  point2  the second vertex
     */
    public Rectangle(Point point1, Point point2) {
        // Tells the Polygon class that we have four edges
        super(4);
        
        // find the displacement in x
        int deltaX = point2.getX() - point1.getX();
        // find the displacement in y
        int deltaY = point2.getY() - point1.getY();
        x = (width < 0) ? point2.getX() : point1.getX();
        y = (height < 0) ? point2.getY() : point1.getY();
        // ensure the dimensions are positive
        width = Math.abs(deltaX);
        height = Math.abs(deltaY);
        
        super.defineLine(0, new Line(x, y, x, y + height - 1));
        super.defineLine(1, new Line(x, y + height - 1, x + width - 1, y + height - 1));
        super.defineLine(2, new Line(x + width - 1, y + height - 1, x + width - 1, y));
        super.defineLine(3, new Line(x + width - 1, y, x, y));
    }
    
    /**
     * Constructs a square given a center and side length
     * @param  center  center
     * @param  length  side length
     */
    public Rectangle(Point center, int length) {
        // Tells the Polygon class that we have four edges
        super(4);
        x = center.getX() - length / 2;
        y = center.getY() - length / 2;
        width = length;
        height = length;
        super.defineLine(0, new Line(x, y, x, y + height - 1));
        super.defineLine(1, new Line(x, y + height - 1, x + width - 1, y + height - 1));
        super.defineLine(2, new Line(x + width - 1, y + height - 1, x + width - 1, y));
        super.defineLine(3, new Line(x + width - 1, y, x, y));
    }
    
    /**
     * Returns the x coordinate of the rectangle's origin
     * @return  x coordinate
     */
    public int getX() {
        return x;
    }
    
    /**
     * Returns the y coordinate of the rectangle's origin
     * @return  y coordinate
     */
    public int getY() {
        return y;
    }
    
    /**
     * Returns the width of this Rectangle
     * @return  width
     */
    public int getWidth() {
        return width;
    }
    
    /**
     * Returns the height of this Rectangle
     * @return  height
     */
    public int getHeight() {
        return height;
    }
    
    /**
     * Draws the rectangle associated with the Rectangle object
     * @param  graphics  the graphics context
     */
    @Override
    public void draw(Graphics graphics) {
        graphics.fillRect(x, y, width, height);
    }
}
