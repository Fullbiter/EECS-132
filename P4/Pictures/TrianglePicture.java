/**
 * TrianglePicture objects represent the fractal image of a subdivided triangle.
 *
 * @author   Kevin Nash (kjn33)
 * @version  2015.4.13
 */
import java.awt.Graphics;

public class TrianglePicture extends Picture {
    
    /**
     * Constructs a TrianglePicture
     * @param  baseShape  the Triangle that the TrianglePicture is based upon
     */
    public TrianglePicture(Triangle baseShape) {
        // Tell Picture which starting shape to use and the associated number of edges
        super(baseShape, baseShape.getLines().length);
        TriangleFractal baseFractal = new TriangleFractal(baseShape);
        for (int i = 0; i < baseFractal.subFractals().length; i++)
            super.setFractal(i, baseFractal.subFractals()[i]);
    }
}
