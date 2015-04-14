/**
 * The Geometry class provides static methods for geometric operations.
 *
 * @author   Kevin Nash (kjn33)
 * @version  2015.4.12
 */
public final class Geometry {
    
    /**
     * Private constructor prevents instantiation
     */
    private Geometry() {}
    
    /**
     * Returns a midpoint at a given fraction (k) of the given line
     * @param  line  the number of remaining draw iterations
     * @param  k     the decimal form of the desired fraction
     */
    public static Point findMidpoint(Line line, Double k) {
        int xMid = (int)(line.getFirstPoint().getX() + k * (line.getSecondPoint().getX() - line.getFirstPoint().getX()) + 0.5);
        int yMid = (int)(line.getFirstPoint().getY() + k * (line.getSecondPoint().getY() - line.getFirstPoint().getY()) + 0.5);
        return new Point(xMid, yMid);
    }
    
    /**
     * Returns the third point in an equilateral triangle formed by three points
     * @param  point1  the first point
     * @param  point2  the second point
     */
    public static Point findPeak(Point point1, Point point2) {
        int x3 = (int)(0.5 * (point2.getX() + point1.getX() - Math.sqrt(3) * (point2.getY() - point1.getY())) + 0.5);
        int y3 = (int)(0.5 * (point2.getY() + point1.getY() + Math.sqrt(3) * (point2.getX() - point1.getX())) + 0.5);
        return new Point(x3, y3);
    }
}
