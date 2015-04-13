/**
 * FlakeFractal objects represent a snowflake-type fractal in 2D space.
 *
 * @author   Kevin Nash (kjn33)
 * @version  2015.4.12
 */
import java.awt.Graphics;

public class FlakeFractal extends Fractal {
    
    /**
     * Sets the number of subfractals in this Fractal
     * @param  baseShape        the polygon the fractal is based upon
     * @param  subFractalCount  the number of subfractals
     */
    public FlakeFractal(Line baseShape, int layerCount) {
        // Tell Fractal that we use a Line base and have four subfractals, or zero if this is the final layer
        super(baseShape, (layerCount > 0) ? layerCount : 0);
        
        // The point at one third of the length of baseShape
        Point oneThird = Geometry.findMidpoint(baseShape, 1.0 / 3.0);
        // The point at two thirds of the length of baseShape
        Point twoThirds = Geometry.findMidpoint(baseShape, 2.0 / 3.0);
        // The top point of an equilateral triangle formed with oneThird and twoThirds
        Point peak = Geometry.findPeak(oneThird, twoThirds);
        
        // Tell Fractal to store each subfractal, the consecutive lines between the above Points
        super.setSubFractal(0, new FlakeFractal(new Line(baseShape.getFirstPoint(), oneThird), layerCount - 1));
        super.setSubFractal(1, new FlakeFractal(new Line(oneThird, peak), layerCount - 1));
        super.setSubFractal(2, new FlakeFractal(new Line(peak, twoThirds), layerCount - 1));
        super.setSubFractal(3, new FlakeFractal(new Line(twoThirds, baseShape.getSecondPoint()), layerCount - 1));
    }
}
