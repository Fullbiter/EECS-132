/**
 * The HW2Test class provides unit testing for HW2.java.
 *
 * @author   Kevin Nash (kjn33)
 * @version  2015.4.11
 */
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class HW4Test {
    
    /**
     * Tests the coordinate operations of the Point class
     */
    @Test
    public void pointCoords() {
        Point point = new Point(30, 40);
        // constructor
        assertEquals("Point constructor sets an incorrect x coordinate",
                     30, point.getX());
        assertEquals("Point constructor sets an incorrect y coordinate",
                     40, point.getY());
        // coordinate change
        point.setX(40);
        point.setY(30);
        assertEquals("setX method sets an incorrect x coordinate",
                     40, point.getX());
        assertEquals("setY method sets an incorrect y coordinate",
                     30, point.getY());
        // rotation
        point.setX(100);
        point.setY(0);
        point.rotate(Math.PI / 4);
        assertEquals("rotation sets an incorrect x coordinate",
                     71, point.getX());
        assertEquals("rotation sets an incorrect y coordinate",
                     71, point.getY());
        // rotation of the origin
        point.setX(0);
        point.setY(0);
        point.rotate(Math.PI / 4);
        assertEquals("rotation of the origin results in an x coordinate other than zero",
                     0, point.getX());
        assertEquals("rotation of the origin results in a y coordinate other than zero",
                     0, point.getY());
    }
    
    /**
     * Tests the angle operations of the Point class
     */
    @Test
    public void pointAngle() {
        Point point = new Point(30, 40);
        // constructor
        assertEquals("Point constructor sets an incorrect angle",
                     0.92729, point.getAngle(), 0.0001);
        // coordinate change
        point.setX(40);
        point.setY(30);
        assertEquals("setter methods set an incorrect angle",
                     0.64350, point.getAngle(), 0.0001);
        // rotation
        point.setX(100);
        point.setY(0);
        point.rotate(Math.PI / 4);
        assertEquals("rotation sets an incorrect angle",
                     0.78539, point.getAngle(), 0.0001);
        // rotation of the origin
        point.setX(0);
        point.setY(0);
        point.rotate(Math.PI / 4);
        assertEquals("rotation of the origin results in an angle other than zero",
                     0.0, point.getAngle(), 0.0001);
    }
}