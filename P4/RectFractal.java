/**
 * RectFractal objects represent a subdivision-type fractal in 2D space.
 *
 * @author   Kevin Nash (kjn33)
 * @version  2015.4.12
 */
import java.awt.Graphics;

public class RectFractal extends Fractal {
    
    /**
     * Sets the number of subfractals in this Fractal
     * @param  baseShape        the polygon the fractal is based upon
     * @param  subFractalCount  the number of subfractals
     */
    public RectFractal(Rectangle baseShape) {
        // Tell Fractal that we use a Rectangle base and have eight subfractals
        super(baseShape, 8);
        
        // The outer vertices of baseShape
        Point x0y0 = new Point(baseShape.getX(), baseShape.getY());
        Point x3y0 = new Point(baseShape.getWidth() - baseShape.getX(), baseShape.getY());
        Point x0y3 = new Point(baseShape.getX(), baseShape.getHeight() - baseShape.getY());
        Point x3y3 = new Point(baseShape.getWidth() - baseShape.getX(), baseShape.getHeight() - baseShape.getY());
        
        // The x and y values that bisect baseShape into a grid
        int vert1 = (int)((baseShape.getWidth() - baseShape.getX()) * (1.0 / 3.0) + 0.5);
        int vert2 = (int)((baseShape.getWidth() - baseShape.getX()) * (2.0 / 3.0) + 0.5);
        int horiz1 = (int)((baseShape.getHeight() - baseShape.getY()) * (1.0 / 3.0) + 0.5);
        int horiz2 = (int)((baseShape.getHeight() - baseShape.getY()) * (2.0 / 3.0) + 0.5);
        
        // The inner vertices of baseShape
        Point x1y1 = new Point(vert1, horiz1);
        Point x2y1 = new Point(vert2, horiz1);
        Point x1y2 = new Point(vert1, horiz2);
        Point x2y2 = new Point(vert2, horiz2);
        
        // New Points on the outer edges of baseShape
        Point x0y1 = new Point(baseShape.getX(), horiz1);
        Point x1y0 = new Point(vert1, baseShape.getY());
        Point x2y3 = new Point(vert2, baseShape.getHeight() - baseShape.getY());
        Point x3y2 = new Point(baseShape.getWidth() - baseShape.getX(), horiz2);
        
        // Tell Fractal to store each subfractal
        super.setSubFractal(0, new RectFractal(new Rectangle(x0y0, x1y1)));
        super.setSubFractal(1, new RectFractal(new Rectangle(x1y0, x2y1)));
        super.setSubFractal(2, new RectFractal(new Rectangle(x2y1, x3y0)));
        super.setSubFractal(3, new RectFractal(new Rectangle(x0y1, x1y2)));
        super.setSubFractal(4, new RectFractal(new Rectangle(x3y2, x2y1)));
        super.setSubFractal(5, new RectFractal(new Rectangle(x0y3, x1y2)));
        super.setSubFractal(6, new RectFractal(new Rectangle(x1y2, x2y3)));
        super.setSubFractal(7, new RectFractal(new Rectangle(x3y3, x2y2)));
    }
}

