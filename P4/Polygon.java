/**
 * Polygon can be extended into specific shapes in 2D space.
 *
 * @author   Kevin Nash (kjn33)
 * @version  2015.4.12
 */
import java.awt.Graphics;

public abstract class Polygon {
    
    /** the lines that form the polygon **/
    private Line[] lines;
    
    /**
     * Gives lines an input length
     * @param  lineCount  the number of edges
     */
    public Polygon(int lineCount) {
        lines = new Line[lineCount];
    }
    
    /**
     * Returns the lines associated with this polygon
     * @return  the Line array
     */
    public Line[] getLines() {
        return lines;
    }
    
    /**
     * Draws the polygon line by line
     * @param  graphics  the graphics context
     */
    public void draw(Graphics graphics) {
        for (Line line : lines)
            line.draw(graphics);
    }
    
    /**
     * Define a line of the polygon explicitly
     * @exception  ArrayIndexOutOfBoundsException
     */
    protected void defineLine(int i, Line line) throws ArrayIndexOutOfBoundsException {
        lines[i] = line;
    }
}
