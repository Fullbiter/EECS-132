/**
 * TriangleFractal objects represent a subdivision-type fractal in 2D space.
 *
 * @author   Kevin Nash (kjn33)
 * @version  2015.4.12
 */
import java.awt.Graphics;

public class TriangleFractal extends Fractal {
    
    /**
     * Sets the number of subfractals in this Fractal
     * @param  baseShape        the polygon the fractal is based upon
     * @param  subFractalCount  the number of subfractals
     */
    public TriangleFractal(Triangle baseShape) {
        // Tell Fractal that we use a Triangle base and have three subfractals
        super(baseShape, 3);
        
        // The three points of the base shape
        Point point1 = baseShape.getLines()[0].getFirstPoint();
        Point point2 = baseShape.getLines()[1].getFirstPoint();
        Point point3 = baseShape.getLines()[2].getFirstPoint();
        
        // Find the centroid
        int centroidX = (int)((point1.getX() + point2.getX() + point3.getX()) / 3.0 + 0.5);
        int centroidY = (int)((point1.getY() + point2.getY() + point3.getY()) / 3.0 + 0.5);
        Point centroid = new Point(centroidX, centroidY);
        
        // Create three subtriangles
        Triangle triangle1 = new Triangle(point2, centroid, point1);
        Triangle triangle2 = new Triangle(point3, centroid, point2);
        Triangle triangle3 = new Triangle(point1, centroid, point3);
        
        // Tell Fractal to store each subfractal
        super.defineSubFractal(0, new TriangleFractal(triangle1));
        super.defineSubFractal(1, new TriangleFractal(triangle2));
        super.defineSubFractal(2, new TriangleFractal(triangle3));
    }
}
