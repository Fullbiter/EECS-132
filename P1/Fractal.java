import java.awt.Graphics;

// Fractal objects use Lines to create fractal extensions of a Line
public class Fractal {
    
    private int layerCount = 0; // number of layers to draw
    private Line line1;         // first line of fractal shape
    private Line line2;         // second ...
    private Line line3;         // third  ...
    private Line line4;         // fourth ...
    
    public Fractal(Line line, int layerCount) {
        this.layerCount = layerCount;
        Point oneThird = findMidpoint(line, (double) 1/3);
        Point twoThirds = findMidpoint(line, (double) 2/3);
        Point peak = findPeak(oneThird, twoThirds);
        line1 = new Line(line.getFirstPoint(), oneThird);
        line2 = new Line(oneThird, peak);
        line3 = new Line(peak, twoThirds);
        line4 = new Line(twoThirds, line.getSecondPoint());
    }
    
    // returns the first line of fractal shape
    public Line getLine1() {
        return line1;
    }
    
    // returns the second line of fractal shape
    public Line getLine2() {
        return line2;
    }
    
    // returns the third line of fractal shape
    public Line getLine3() {
        return line3;
    }
    
    // returns the fourth line of fractal shape
    public Line getLine4() {
        return line4;
    }
    
    // returns the fractal of the first line
    public Fractal getFractal1() {
        return new Fractal(line1, layerCount - 1);
    }
    
    // returns the fractal of the second line
    public Fractal getFractal2() {
        return new Fractal(line2, layerCount - 1);
    }
    
    // returns the fractal of the third line
    public Fractal getFractal3() {
        return new Fractal(line3, layerCount - 1);
    }
    
    // returns the fractal of the fourth line
    public Fractal getFractal4() {
        return new Fractal(line4, layerCount - 1);
    }
    
    // draws the shape of the fractal or delegates drawing to subfractal
    public void draw(Graphics graphics) {
        if (layerCount > 1) {
            getFractal1().draw(graphics);                
            getFractal2().draw(graphics);
            getFractal3().draw(graphics);
            getFractal4().draw(graphics);
        }
        else {
            line1.draw(graphics);
            line2.draw(graphics);
            line3.draw(graphics);
            line4.draw(graphics);
        }
    }
    
    // returns a midpoint at a given fraction (k) of the given line
    private Point findMidpoint(Line line, Double k) {
        int xMid = (int)(line.getFirstPoint().getX() + k * (line.getSecondPoint().getX() - line.getFirstPoint().getX()) + 0.5);
        int yMid = (int)(line.getFirstPoint().getY() + k * (line.getSecondPoint().getY() - line.getFirstPoint().getY()) + 0.5);
        return new Point(xMid, yMid);
    }
    
    // returns the third point in an equilateral triangle formed by three points
    private Point findPeak(Point point1, Point point2) {
        int x3 = (int)(0.5 * (point2.getX() + point1.getX() + Math.sqrt(3) * (point2.getY() - point1.getY())) + 0.5);
        int y3 = (int)(0.5 * (point2.getY() + point1.getY() - Math.sqrt(3) * (point2.getX() - point1.getX())) + 0.5);
        return new Point(x3, y3);
    }
}