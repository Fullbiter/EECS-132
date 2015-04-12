/**
 * Rectagle objects represent rectangles in 2D space.
 *
 * @author   Kevin Nash (kjn33)
 * @version  2015.4.11
 */
import java.awt.Graphics;

public class Rectangle {
    
    /** the rectangle origin x coordinate (left) **/
    private int x = 0;
    /** the rectangle origin y coordinate (bottom) **/
    private int y = 0;
    /** the rectangle's width **/
    private int width = 0;
    /** the rectangle's height **/
    private int height = 0;
    
    /** the lines that form the rectangle **/
    private Line[] lines = new Line[4];
    
    /**
     * Constructs a rectangle using two corner points
     * @param  point1  the first corner
     * @param  point2  the second corner
     */
    public Rectangle(Point point1, Point point2) {
        // find the displacement in x
        int deltaX = point2.getX() - point1.getX();
        // find the displacement in y
        int deltaY = point2.getY() - point1.getY();
        
        x = (width < 0) ? point2.getX() : point1.getX();
        y = (height < 0) ? point2.getY() : point1.getY();
        // ensure the dimensions are positive
        width = Math.abs(deltaX);
        height = Math.abs(deltaY);
        
        defineLines();
    }
    
    /**
     * Constructs a square given a center and side length
     * @param  point   center
     * @param  length  side length
     */
    public Rectangle(Point center, int length) {
        x = center.getX() - length / 2;
        y = center.getY() - length / 2;
        width = length;
        height = length;
        
        defineLines();
    }
    
    /**
     * Returns the lines associated with this line
     * @return  the Line array
     */
    public Line[] getLines() {
        return lines;
    }
    
    /**
     * Draws the rectangle associated with the Rectangle object
     * @param  graphics  the graphics context
     */
    public void draw(Graphics graphics) {
        graphics.fillRect(x, y, width, height);
    }
    
    /**
     * Define the lines of the rectangle explicitly
     */
    private void defineLines() {
        lines[0] = new Line(x, y, x, y + height - 1);
        lines[1] = new Line(x, y + height - 1, x + width - 1, y + height - 1);
        lines[2] = new Line(x + width - 1, y + height - 1, x + width - 1, y);
        lines[3] = new Line(x + width - 1, y, x, y);
    }
}
