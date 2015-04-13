/**
 * Fractal can be extended into specific fractals in 2D space.
 *
 * @author   Kevin Nash (kjn33)
 * @version  2015.4.12
 */
import java.awt.Graphics;

public abstract class Fractal {
    
    /** the base shape upon which fractals are made **/
    private Polygon baseShape;
    /** the subfractals within this Fractal **/
    private Fractal[] subFractals;
    
    /**
     * Sets the number of subfractals in this Fractal
     * @param  baseShape        the polygon the fractal is based upon
     * @param  subFractalCount  the number of subfractals
     */
    public Fractal(Polygon baseShape, int subFractalCount) {
        this.baseShape = baseShape;
        this.subFractals = new Fractal[subFractalCount];
    }
    
    /**
     * Returns an array containing this Fractal's subfractals
     * @return  array of subfractals
     */
    public Fractal[] subFractals() {
        return subFractals();
    }
    
    /**
     * Returns the base shape of this Fractal
     * @return  the base shape
     */
    public Polygon getBaseShape() {
        return baseShape;
    }
    
    /**
     * Draws the fractal
     * @param  level     the number of remaining draw iterations
     * @param  graphics  the graphics context
     */
    public void draw(int level, Graphics graphics) {
        // base case
        if (level == 0)
            baseShape.draw(graphics);
        else {
            // recursive draw calls for each subFractal in this Fractal
            for (Fractal subFractal : subFractals)
                subFractal.draw(level - 1, graphics);
        }
    }
    
    /**
     * Define a subfractal of the Fractal explicitly
     * @param  i     the index of the line to be stored
     * @param  line  the line to be stored
     * @exception  ArrayIndexOutOfBoundsException
     */
    protected void defineSubFractal(int i, Fractal subFractal) throws ArrayIndexOutOfBoundsException {
        subFractals[i] = subFractal;
    }
}
