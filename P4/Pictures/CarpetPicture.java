/**
 * CarpetPicture objects represent the fractal image of a Serpinski carpet.
 *
 * @author   Kevin Nash (kjn33)
 * @version  2015.4.13
 */
import java.awt.Graphics;

public class CarpetPicture extends Picture {
    
    /**
     * Constructs a CarpetPicture
     * @param  baseShape  the Rectangle that the CarpetPicture is based upon
     */
    public CarpetPicture(Rectangle baseShape) {
        // Tell Picture which starting shape to use and the associated number of edges
        super(baseShape);
        RectFractal baseFractal = new RectFractal(baseShape);
        for (int i = 0; i < baseFractal.subFractals().length; i++)
            super.setFractal(i, baseFractal.subFractals()[i]);
    }
}
