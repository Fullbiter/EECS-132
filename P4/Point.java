/**
 * Point objects represent points in 2D space.
 *
 * @author   Kevin Nash (kjn33)
 * @version  2015.4.11
 */
public class Point {
    
    /** x coordinate **/    
    private int x = 0;
    /** y coordinate **/
    private int y = 0;
    /** angle between x axis and vector from origin to this point **/
    private double angle = 0.0;
    
    /**
     * Constructs a point at the given coordinates
     * @param  x  the x coordinate
     * @param  y  the y coordinate
     */
    public Point(int x, int y) {
        this.x = x;
        this.y = y;
        if (x != 0 && y != 0)
            updateAngle();
    }
    
    /**
     * Returns the x coordinate
     * @return  the x coordinate
     */
    public int getX() {
        return x;
    }
    
    /**
     * Sets the x coordinate
     * @param  x  the x coordinate
     */
    public void setX(int x) {
        this.x = x;
        updateAngle();
    }
    
    /**
     * Returns the y coordinate
     * @return  the y coordinate
     */
    public int getY() {
        return y;
    }
    
    /**
     * Sets the y coordinate
     * @param  y  the y coordinate
     */
    public void setY(int y) {
        this.y = y;
        updateAngle();
    }
    
    /**
     * Returns the angle between x axis and vector from origin to this point
     * @return  the angle
     */
    public double getAngle() {
        return angle;
    }
    
    /**
     * Rotates this point about origin by the given angle
     * @param  angle  the angle
     */
    public void rotate(double angle) {
        double sin = Math.sin(angle);
        double cos = Math.cos(angle);
        int xNew = (int)(x * cos - y * sin + 0.5);
        y = (int)(x * sin + y * cos + 0.5);
        x = xNew;
        updateAngle();
    }
    
    /**
     * Rotates this point about a given point by the given angle
     * @param  anchor  the anchor point
     * @param  angle   the angle
     */
    public void rotate(Point anchor, double angle) {
        // translate anchor point to origin and this point relatively
        x -= anchor.getX();
        y -= anchor.getY();
        // rotate as normal
        rotate(angle);
        // reverse the prior translation
        x -= anchor.getX();
        y -= anchor.getY();
        updateAngle();
    }
    
    /**
     * Updates the angle to reflect the current coordinates
     */
    private void updateAngle() {
        if (x != 0 && y != 0)
            angle = Math.acos(x / Math.sqrt(x * x + y * y));
        else
            angle = 0.0;
    }
}
