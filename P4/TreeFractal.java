/**
 * TreeFractal objects represent a tree-type fractal in 2D space.
 *
 * @author   Kevin Nash (kjn33)
 * @version  2015.4.12
 */
import java.awt.Graphics;

public class TreeFractal extends Fractal {
    
    /**
     * Sets the number of subfractals in this Fractal
     * @param  baseShape        the polygon the fractal is based upon
     * @param  subFractalCount  the number of subfractals
     */
    public TreeFractal(Line baseShape) {
        // Tell Fractal that we use a Line base and have two subfractals
        super(baseShape, 2);
        
        // Copy of the base shape's first point
        Point point1 = new Point(baseShape.getFirstPoint().getX(), baseShape.getFirstPoint().getY());
        // Copy of the base shape's second point
        Point point2 = new Point(baseShape.getSecondPoint().getX(), baseShape.getSecondPoint().getY());
        // Point on the line made by the base shape
        Point point3 = new Point(point1.getX() + (int)(1.5 * (point2.getX() - point1.getX())),
                                 point1.getY() + (int)(1.5 * (point2.getY() - point1.getY())));
        // Copy of the previous point
        Point point4 = new Point(point2.getX(), point2.getY());
        // Rotate point3 to be the endpoint for a -60 deg branch
        point3.rotate(point1, Math.PI / -3.0);
        // Rotate point4 to be the endpoint for a 60 deg branch
        point4.rotate(point1, Math.PI / 3.0);
        
        // Tell Fractal to store each subfractal, the lines that make each branch
        super.defineSubFractal(0, new TreeFractal(new Line(point2, point3)));
        super.defineSubFractal(1, new TreeFractal(new Line(point2, point4)));
    }
}
