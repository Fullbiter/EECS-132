/**
 * TreePicture objects represent the fractal image of a tree.
 *
 * @author   Kevin Nash (kjn33)
 * @version  2015.4.13
 */
import java.awt.Graphics;

public class TreePicture extends Picture {
    
    /**
     * Constructs a TreePicture
     * @param  baseShape  the Polygon that the TreePicture is based upon
     */
    public TreePicture(Polygon baseShape) {
        // Tell Picture which starting shape to use and the associated number of edges
        super(baseShape);
        for (int i = 0; i < baseShape.getLines().length; i++)
            super.setFractal(i, new TreeFractal(baseShape.getLines()[i]));
    }
}
