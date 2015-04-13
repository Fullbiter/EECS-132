/**
 * Picture can be extended into specific fractal pictures in 2D space.
 *
 * @author   Kevin Nash (kjn33)
 * @version  2015.4.13
 */
import java.awt.Graphics;

public abstract class Picture {
    
    /** the base Fractal that this Picture represents **/
    private Fractal baseFractals[];
    /** the starting shape to be fractalized **/
    private Polygon baseShape;
        
    
    /**
     * Sets the number of subfractals in this Fractal
     * @param  baseShape         starting shape (Polygon)
     * @param  baseFractalCount  the number of base fractals
     */
    public Picture(Polygon baseShape) {
        this.baseShape = baseShape;
        this.baseFractals = new Fractal[baseShape.getLines().length];
    }
    
    /**
     * Draws the fractal picture
     * @param  level     the number of remaining draw iterations
     * @param  graphics  the graphics context
     */
    public void draw(int level, Graphics graphics) {
        // recursive draw calls for each base Fractal in this picture
        for (Fractal fractal : baseFractals)
            fractal.draw(level, graphics);
    }
    
    /**
     * Returns the base Fractals of this Picture
     * @return  array of base Fractals
     */
    public Fractal[] getBaseFractals() {
        return baseFractals;
    }
    
    /**
     * Sets the base Fractal at the input index to be the input Fractal
     * @param  i     the index of the Fractal to be replaced
     * @param  line  the replacement Fractal
     * @exception  ArrayIndexOutOfBoundsException
     */
    public void setFractal(int i, Fractal fractal) throws ArrayIndexOutOfBoundsException {
        baseFractals[i] = fractal;
    }
}
