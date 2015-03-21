// Point objects represent points in 2D space
public class Point {
    
    private int x = 0;          // x coordinate
    private int y = 0;          // y coordinate
    private double angle = 0.0; // angle between x axis and vector from origin to this point
    
    public Point(int x, int y) {
        this.x = x;
        this.y = y;
        if (x != 0 && y != 0)
            findAngle();
    }
    
    // returns x coordinate
    public int getX() {
        return x;
    }
    
    // sets x coordinate
    public void setX(int x) {
        this.x = x;
    }
    
    // returns y coordinate
    public int getY() {
        return y;
    }
    
    // sets y coordinate
    public void setY(int y) {
        this.y = y;
    }
    
    // returns the angle between x axis and vector from origin to this point
    public double getAngle() {
        return angle;
    }
    
    // rotates this point about origin by the given angle
    public void rotate(double angle) {
        int xNew = (int)(x * Math.cos(angle) - y * Math.sin(angle) + 0.5);
        y = (int)(x * Math.sin(angle) + y * Math.cos(angle) + 0.5);
        x = xNew;
        findAngle();
    }
    
    // updates the angle to reflect the current coordinates
    private void findAngle() {
        this.angle = Math.acos(x / Math.sqrt(x * x + y * y));
    }
}