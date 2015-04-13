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

public class DrawFractal /*implements ActionListener*/ {
    
    /** The graphics context used **/
    private Graphics graphics;
    /** The frame used **/
    private JFrame frame = new JFrame("kjn33 - Fractal Visualizer");
    /** The canvas used **/
    private Canvas canvas = new Canvas();
    /** The panel used **/
    private JPanel panel = new JPanel();
    /** The buttons used **/
    private JButton[] buttons = new JButton[5];
    
    /**
     * 
     */
    public DrawFractal(int width, int height) {
        // Create a listener for future use
        GUIListener listener = new GUIListener();
        
        // Set the size of the canvas
        canvas.setSize(width, height);
        // Set the background color of the canvas
        canvas.setBackground(Color.WHITE);
        
        // Create a button for each picture
        buttons[0] = new JButton("Snowflake");
        buttons[1] = new JButton("Boxflake");
        buttons[2] = new JButton("Tree");
        buttons[3] = new JButton("Triangle");
        buttons[4] = new JButton("Carpet");
        
        // Add buttons to the panel
        // Iterate over each button in buttons
        for (JButton button : buttons)
            panel.add(button);
        
        // Add a listener to each button
        // Iterate over each button in buttons
        for (JButton button : buttons)
            button.addActionListener(listener);
        
        // Add the canvas to the frame
        frame.getContentPane().add(canvas, "Center");
        frame.getContentPane().add(panel);
        frame.pack();
        frame.setVisible(true);
        panel.setVisible(true);
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
    
    /**
     * 
     */
    public class GUIListener implements ActionListener {
        /**
         * 
         */
        public void actionPerformed(ActionEvent event) {
            Point center = new Point(canvas.getWidth() / 2, canvas.getHeight() / 2);
            int length = canvas.getWidth() / 3;
            // Draw snowflake picture
            if (event.getSource() == buttons[0])
                drawFractal(new SnowflakePicture(new Triangle(center, length)), 3);
            // Draw boxflake picture
            else if (event.getSource() == buttons[1])
                drawFractal(new SnowflakePicture(new Rectangle(center, length)), 3);
            // Draw tree picture
            else if (event.getSource() == buttons[2]) {
                drawFractal(new TreePicture(new Line(new Point(center.getX(), center.getY() / 2), center)), 5);
            }
            // Draw triangle picture
            else if (event.getSource() == buttons[3])
                drawFractal(new TrianglePicture(new Triangle(center, length)), 3);
            // Draw carpet picture
            else if (event.getSource() == buttons[4])
                drawFractal(new CarpetPicture(new Rectangle(center, length)), 3);
        }
    }
}
