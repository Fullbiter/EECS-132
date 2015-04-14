/**
 * Creates a canvas upon which to draw fractals.
 *
 * @author   Kevin Nash (kjn33)
 * @version  2015.4.13
 */
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.UIManager;

public class DrawFractal implements ActionListener {
    
    /** The graphics context used **/
    private Graphics graphics;
    /** The frame used **/
//    private JFrame frame = new JFrame("kjn33 - Fractal Visualizer");
    /** The canvas used **/
    private Canvas canvas = new Canvas();
    /** The panel used **/
//    private JPanel panel = new JPanel();
    /** The buttons used **/
    private JButton[] buttons = new JButton[10];
    
    /**
     * 
     */
    public DrawFractal(int width, int height) {
        // Change the L&F of the UI to match the user's OS
        try {UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());}
        catch (Exception e) {;} // do nothing
        
        JFrame frame = new JFrame("kjn33 - Fractal Visualizer");
        JTabbedPane tabs = new JTabbedPane();
        JPanel polygonPanel = new JPanel();
        JPanel fractalPanel = new JPanel();
        
        // Set the size of the canvas
        canvas.setSize(width, height);
        // Set the background color of the canvas
        canvas.setBackground(Color.WHITE);
        
        // Create a button for each picture
        buttons[0] = new JButton("Line");
        buttons[1] = new JButton("Triangle");
        buttons[2] = new JButton("Square");
        buttons[3] = new JButton("Snowflake");
        buttons[4] = new JButton("Boxflake");
        buttons[5] = new JButton("Tree");
        buttons[6] = new JButton("Triangle");
        buttons[7] = new JButton("Carpet");
        buttons[8] = new JButton("Clear Canvas");
        buttons[9] = new JButton("Clear Canvas");
        
        // Give each button an action
        buttons[0].setActionCommand("line");
        buttons[1].setActionCommand("triangle");
        buttons[2].setActionCommand("square");
        buttons[3].setActionCommand("snowflake");
        buttons[4].setActionCommand("boxflake");
        buttons[5].setActionCommand("tree");
        buttons[6].setActionCommand("triangle");
        buttons[7].setActionCommand("carpet");
        buttons[8].setActionCommand("clear");
        buttons[9].setActionCommand("clear");
        
        // Add a listener to every button
        for (JButton button : buttons)
            button.addActionListener(this);
        
        // Add Polygon buttons to the polygonPanel
        for (int i = 0; i <= 2; i++)
            polygonPanel.add(buttons[i]);
        polygonPanel.add(buttons[9]);
        
        // Add Fractal buttons to the fractalPanel
        for (int i = 3; i < buttons.length - 1; i++)
            fractalPanel.add(buttons[i]);
        
        // Add polygonPanel to the polygon tab
        tabs.addTab("Polygons", polygonPanel);
        // Add polygonPanel to the polygon tab
        tabs.addTab("Fractals", fractalPanel);
        
        // Add the canvas to the frame
        frame.getContentPane().add(canvas, "Center");
        frame.getContentPane().add(tabs, "South");
        frame.pack();
        frame.setVisible(true);
        this.getGraphics();
    }
    
    public static void main(String[] args) {
        DrawFractal drawing = new DrawFractal(500, 500);
    }
    
    /**
     * Pulls graphics context from the canvas if it is not already defined
     * @return  the graphics context
     */
    public Graphics getGraphics() {
        if (graphics == null) {
            graphics = canvas.getGraphics();
            if (graphics != null)
                graphics.setColor(Color.BLACK);
        }
        return graphics;
    }
    
    /**
     * Draws an input fractal picture
     * @param  picture     the picture
     * @param  layerCount  the number of layers to draw
     */
    public void drawFractal(Picture picture, int layerCount) {
        picture.draw(layerCount, getGraphics());
    }
    
    public void actionPerformed(ActionEvent e) {
        Point center = new Point(canvas.getWidth() / 2, canvas.getHeight() / 2);
        int length = canvas.getWidth() / 3;
        // Draw line
        if ("line".equals(e.getActionCommand())) {
            Line line = new Line(new Point(center.getX(), (int)(center.getY() * (3.0 / 2.0) + 0.5)), center);
            System.out.println("Other: (" + line.getFirstPoint().getX() + ", " + line.getFirstPoint().getY() + ")");
            System.out.println("Center: (" + center.getX() + ", " + center.getY() + ")");
            line.draw(graphics);
        }
        // Draw triangle
        else if ("triangle".equals(e.getActionCommand())) {
            Triangle triangle = new Triangle(center, length);
            triangle.draw(graphics);
        }
        // Draw square
        else if ("square".equals(e.getActionCommand())) {
            Rectangle square = new Rectangle(center, length);
            square.draw(graphics);
        }
        // Draw snowflake picture
        else if ("snowflake".equals(e.getActionCommand()))
            drawFractal(new SnowflakePicture(new Triangle(center, length)), 3);
        // Draw boxflake picture
        else if ("boxflake".equals(e.getActionCommand()))
            drawFractal(new SnowflakePicture(new Rectangle(center, length)), 3);
        // Draw tree picture
        else if ("tree".equals(e.getActionCommand()))
            drawFractal(new TreePicture(new Line(new Point(center.getX(),(int)(center.getY() * (3.0 / 2.0) + 0.5)), center)), 5);
        // Draw triangle picture
        else if ("triangle".equals(e.getActionCommand()))
            drawFractal(new TrianglePicture(new Triangle(center, length)), 3);
        // Draw carpet picture
        else if ("carpet".equals(e.getActionCommand()))
            drawFractal(new CarpetPicture(new Rectangle(center, length)), 3);
        // Disable all drawing buttons
        for (int i = 0; i < buttons.length - 2; i++)
            buttons[i].setEnabled(false);
        // Clear canvas and re-enable all buttons
        if ("clear".equals(e.getActionCommand())) {
            canvas.getGraphics().clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
            for (JButton button : buttons)
                button.setEnabled(true);
        }
    }
}
