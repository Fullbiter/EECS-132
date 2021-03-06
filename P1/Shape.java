import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JFrame;

// Shape objects can draw fractal shapes
public class Shape {
    
    private JFrame frame = new JFrame("kjn33 - Fractal Visualizer");
    private Canvas canvas = new Canvas(); 
    private Graphics graphics;
    
    public Shape(int width, int height) {
        canvas.setSize(width, height);
        canvas.setBackground(Color.WHITE);
        frame.getContentPane().add(canvas, "Center");
        frame.pack();                                 // set size of JFrame based on its canvas component
        frame.setVisible(true);
        this.getGraphics();
    }
    
    // pulls graphics context from the canvas if it is not already defined
    public Graphics getGraphics() {
        if (graphics == null) {
            graphics = canvas.getGraphics();
            if (graphics != null)
                graphics.setColor(Color.BLACK);
        }
        return graphics;
    }
    
    // calls the given line's draw method if an appropriate graphics context exists
    public void drawLine(Line line) {
        if (graphics != null)
            line.draw(graphics);
    }
    
    // draws a fractal with a square base of given size and number of layers
    public void drawBoxflake(int size, int numLayers) {
        
        // create vertices of the square
        Point topLeft = new Point(canvas.getWidth() / 2 - size / 2, canvas.getHeight() / 2 - size / 2);
        Point topRight = new Point(canvas.getWidth() / 2 + size / 2, canvas.getHeight() / 2 - size / 2);
        Point bottomLeft = new Point(canvas.getWidth() / 2 - size / 2, canvas.getHeight() / 2 + size / 2);
        Point bottomRight = new Point(canvas.getWidth() / 2 + size / 2, canvas.getHeight() / 2 + size / 2);
        
        // create lines between neighboring vertices
        Line topL = new Line(topLeft, topRight);
        Line bottomL = new Line(bottomRight, bottomLeft);
        Line leftL = new Line(bottomLeft, topLeft);
        Line rightL = new Line(topRight, bottomRight);
        
        if (numLayers < 1) {
            // call the draw methods belonging to Line
            topL.draw(graphics);
            bottomL.draw(graphics);
            leftL.draw(graphics);
            rightL.draw(graphics);
        }
        else {
            // call the draw methods belonging to Fractal
            Fractal topF = new Fractal(topL, numLayers);
            topF.draw(graphics);
            Fractal bottomF = new Fractal(bottomL, numLayers);
            bottomF.draw(graphics);
            Fractal leftF = new Fractal(leftL, numLayers);
            leftF.draw(graphics);
            Fractal rightF = new Fractal(rightL, numLayers);
            rightF.draw(graphics);
        }
    }
    
    // draws a fractal with an equilateral triangle base of given size and number of layers
    public void drawSnowflake(int size, int numLayers) {
        
        // find the apothem and radius of the base equilateral triangle
        double apothem = Math.sqrt(3) * size / 6;
        double radius = size / Math.sqrt(3);
        
        // create vertices of the triangle
        Point leftP = new Point(canvas.getWidth() / 2 - size / 2, (int)(canvas.getHeight() / 2 - apothem + 0.5));
        Point rightP = new Point(canvas.getWidth() / 2 + size / 2, (int)(canvas.getHeight() / 2 - apothem + 0.5));
        Point bottomP = new Point(canvas.getWidth() / 2, (int)(canvas.getHeight() / 2 + radius + 0.5));
        
        // create lines between each vertex
        Line topL = new Line(leftP, rightP);
        Line leftL = new Line(bottomP, leftP);
        Line rightL = new Line(rightP, bottomP);
        
        if (numLayers < 1) {
            // call the draw methods belonging to Line
            topL.draw(graphics);
            leftL.draw(graphics);
            rightL.draw(graphics);
        }
        else {
            // call the draw methods belonging to Fractal
            Fractal topF = new Fractal(topL, numLayers);
            topF.draw(graphics);
            Fractal leftF = new Fractal(leftL, numLayers);
            leftF.draw(graphics);
            Fractal rightF = new Fractal(rightL, numLayers);
            rightF.draw(graphics);
        }
    }
}