import java.awt.Graphics;

// Line objects represent lines in 2D space
public class Line {
    
    private Point point1; // first point of segment
    private Point point2; // second point of segment
    
    public Line(Point point1, Point point2) {
        this.point1 = point1;
        this.point2 = point2;
    }
    
    public Line(int x1, int y1, int x2, int y2) {
        point1 = new Point(x1, y1);
        point2 = new Point(x2, y2);
    }
    
    // returns first point of segment
    public Point getFirstPoint() {
        return point1;
    }
    
    // sets first point of segment
    public void setFirstPoint(Point point) {
        point1 = point;
    }
    
    // returns second point of segment
    public Point getSecondPoint() {
        return point2;
    }
    
    // sets second point of segment
    public void setSecondPoint(Point point) {
        point2 = point;
    }
    
    // draws the line associated with the Line instance
    public void draw(Graphics graphics) {
        graphics.drawLine(point1.getX(), point1.getY(), point2.getX(), point2.getY());
    }
}