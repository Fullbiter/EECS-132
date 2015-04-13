/**
 * SnowflakePicture objects represent the fractal image of a snowflake.
 *
 * @author   Kevin Nash (kjn33)
 * @version  2015.4.13
 */
import java.awt.Graphics;

public class SnowflakePicture extends Picture {
    
    /**
     * Constructs a SnowflakePicture
     * @param  baseShape  the Polygon that the SnowflakePicture is based upon
     */
    public SnowflakePicture(Polygon baseShape) {
        // Tell Picture which starting shape to use and the associated number of edges
        super(baseShape, baseShape.getLines().length);
        for (int i = 0; i < baseShape.getLines().length; i++)
            super.setFractal(i, new FlakeFractal(baseShape.getLines()[i]));
    }
}
