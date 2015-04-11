/**
 * The HW3Test class provides unit testing for HW3.java.
 *
 * @author   Kevin Nash (kjn33)
 * @version  2015.3.31
 */
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class HW3Test {
    
    /** Adds a rule for exception checking **/
    @Rule
    public ExpectedException thrown = ExpectedException.none();
    
    /**
     * Tests the removeRow method for matrices
     */
    @Test
    public void removeRowMatrix() {
        double[][] input = {{1}, {2, 3}, {4, 5, 6}};
        
        double[][] model = {{2, 3}, {4, 5, 6}};
        double[][] trial = HW3.removeRow(input, 0);
        for (int i = 0; i < model.length; i++)
            assertArrayEquals("Remove the first row", model[i], trial[i], 0.001);
        
        model = new double[][] {{1}, {2, 3}};
        trial = HW3.removeRow(input, 2);
        for (int i = 0; i < model.length; i++)
            assertArrayEquals("Remove the last row", model[i], trial[i], 0.001);
        
        model = new double[][] {{1}, {4, 5, 6}};
        trial = HW3.removeRow(input, 1);
        for (int i = 0; i < model.length; i++)
            assertArrayEquals("Remove a middle row", model[i], trial[i], 0.001);
        
        model = new double[][] {{1}, {2, 3}, {4, 5, 6}};
        trial = HW3.removeRow(input, -1);
        for (int i = 0; i < model.length; i++)
            assertArrayEquals("Remove an invalid row", model[i], trial[i], 0.001);
        
        input = new double[][] {{1}};
        model = new double[][] {};
        trial = HW3.removeRow(input, 0);
        for (int i = 0; i < model.length; i++)
            assertArrayEquals("Remove the only row", model[i], trial[i], 0.001);
    }
    
    /**
     * Tests the removeRow method for SparseMatrix
     */
    @Test
    public void removeRowSparseMatrix() {
        SparseMatrix input = new SparseMatrix(new double[][] {{1}, {2, 3}, {4, 5, 6}});
        
        double[][] model = new SparseMatrix(new double[][] {{2, 3}, {4, 5, 6}}).getFullMatrix();
        double[][] trial = HW3.removeRow(input, 0).getFullMatrix();
        for (int i = 0; i < model.length; i++)
            assertArrayEquals("Remove the first row", model[i], trial[i], 0.001);
        
        model = new SparseMatrix(new double[][] {{1}, {2, 3}}).getFullMatrix();
        trial = HW3.removeRow(input, 2).getFullMatrix();
        for (int i = 0; i < model.length; i++)
            assertArrayEquals("Remove the last row", model[i], trial[i], 0.001);
        
        model = new SparseMatrix(new double[][] {{1}, {4, 5, 6}}).getFullMatrix();
        trial = HW3.removeRow(input, 1).getFullMatrix();
        for (int i = 0; i < model.length; i++)
            assertArrayEquals("Remove a middle row", model[i], trial[i], 0.001);
        
        model = new SparseMatrix(new double[][] {{1}, {2, 3}, {4, 5, 6}}).getFullMatrix();
        trial = HW3.removeRow(input, -1).getFullMatrix();
        for (int i = 0; i < model.length; i++)
            assertArrayEquals("Remove an invalid row", model[i], trial[i], 0.001);
        
        input = new SparseMatrix(new double[][] {{1}});
        model = new SparseMatrix(new double[][] {}).getFullMatrix();
        trial = HW3.removeRow(input, 0).getFullMatrix();
        for (int i = 0; i < model.length; i++)
            assertArrayEquals("Remove the only row", model[i], trial[i], 0.001);
    }
    
    /**
     * Tests the removeColumn method for matrices
     */
    @Test
    public void removeColumnMatrix() {
        double[][] input = {{1, 2, 3}, {4, 5, 6}};
        
        double[][] model = {{2, 3}, {5, 6}};
        double[][] trial = HW3.removeColumn(input, 0);
        for (int i = 0; i < model.length; i++)
            assertArrayEquals("Remove the first column", model[i], trial[i], 0.001);
        
        model = new double[][] {{1, 2}, {4, 5}};
        trial = HW3.removeColumn(input, 2);
        for (int i = 0; i < model.length; i++)
            assertArrayEquals("Remove the last column", model[i], trial[i], 0.001);
        
        model = new double[][] {{1, 3}, {4, 6}};
        trial = HW3.removeColumn(input, 1);
        for (int i = 0; i < model.length; i++)
            assertArrayEquals("Remove a middle column", model[i], trial[i], 0.001);
        
        model = new double[][] {{1, 2, 3}, {4, 5, 6}};
        trial = HW3.removeColumn(input, -1);
        for (int i = 0; i < model.length; i++)
            assertArrayEquals("Remove an invalid column", model[i], trial[i], 0.001);
        
        input = new double[][] {{1}, {2}, {3}};
        model = new double[][] {{}, {}, {}};
        trial = HW3.removeColumn(input, 0);
        for (int i = 0; i < model.length; i++)
            assertArrayEquals("Remove the only column", model[i], trial[i], 0.001);
    }
    
    /**
     * Tests the removeColumn method for SparseMatrix
     */
    @Test
    public void removeColumnSparseMatrix() {
        SparseMatrix input = new SparseMatrix(new double[][] {{1, 2, 3}, {4, 5, 6}});
        
        double[][] model = new SparseMatrix(new double[][] {{2, 3}, {5, 6}}).getFullMatrix();
        double[][] trial = HW3.removeColumn(input, 0).getFullMatrix();
        for (int i = 0; i < model.length; i++)
            assertArrayEquals("Remove the first column", model[i], trial[i], 0.001);
        
        model = new SparseMatrix(new double[][] {{1, 2}, {4, 5}}).getFullMatrix();
        trial = HW3.removeColumn(input, 2).getFullMatrix();
        for (int i = 0; i < model.length; i++)
            assertArrayEquals("Remove the last column", model[i], trial[i], 0.001);
        
        model = new SparseMatrix(new double[][] {{1, 3}, {4, 6}}).getFullMatrix();
        trial = HW3.removeColumn(input, 1).getFullMatrix();
        for (int i = 0; i < model.length; i++)
            assertArrayEquals("Remove a middle column", model[i], trial[i], 0.001);
        
        model = new SparseMatrix(new double[][] {{1, 2, 3}, {4, 5, 6}}).getFullMatrix();
        trial = HW3.removeColumn(input, -1).getFullMatrix();
        for (int i = 0; i < model.length; i++)
            assertArrayEquals("Remove an invalid column", model[i], trial[i], 0.001);
        
        input = new SparseMatrix(new double[][] {{1}, {2}, {3}});
        model = new SparseMatrix(new double[][] {{}, {}, {}}).getFullMatrix();
        trial = HW3.removeColumn(input, 0).getFullMatrix();
        for (int i = 0; i < model.length; i++)
            assertArrayEquals("Remove the only column", model[i], trial[i], 0.001);
    }
    
    /**
     * Tests the determinant method for matrices
     */
    @Test
    public void determinantMatrix() {
        double[][] input = {{3, 5}, {5, 2, 4}, {6, 0, 2, 9}};
        double model = 181.0;
        assertEquals("True matrix", model, HW3.determinant(input), 0.001);
        
        input = new double[][] {{4, 2, 5}};
        model = 7.0;
        assertEquals("One-row matrix", model, HW3.determinant(input), 0.001);
        
        input = new double[][] {};
        model = 0.0;
        assertEquals("Empty matrix", model, HW3.determinant(input), 0.001);
    }
    
    /**
     * Tests the determinant method for SparseMatrix
     */
    @Test
    public void determinantSparseMatrix() {
        SparseMatrix input = new SparseMatrix(new double[][] {{3, 5}, {5, 2, 4}, {6, 0, 2, 9}});
        double model = 181.0;
        assertEquals("True matrix", model, HW3.determinant(input), 0.001);
        
        input = new SparseMatrix(new double[][] {{4, 2, 5}});
        model = 7.0;
        assertEquals("One-row matrix", model, HW3.determinant(input), 0.001);
        
        input = new SparseMatrix(new double[][] {});
        model = 0.0;
        assertEquals("Empty matrix", model, HW3.determinant(input), 0.001);
    }
    
    /**
     * Tests the swapRows method
     */
    @Test
    public void swapRows() {
        double[][] input = {{1, 2}, {3, 4}, {5, 6}, {7, 8}};
        double[][] model = {{3, 4}, {1, 2}, {5, 6}, {7, 8}};
        HW3.swapRows(input, 0, 1);
        for (int i = 0; i < model.length; i++)
            assertArrayEquals("Swap the first row", model[i], input[i], 0.001);
        
        input = new double[][] {{1, 2}, {3, 4}, {5, 6}, {7, 8}};
        model = new double[][] {{1, 2}, {3, 4}, {7, 8}, {5, 6}};
        HW3.swapRows(input, 2, 3);
        for (int i = 0; i < model.length; i++)
            assertArrayEquals("Swap the last row", model[i], input[i], 0.001);
        
        input = new double[][] {{1, 2}, {3, 4}, {5, 6}, {7, 8}};
        model = new double[][] {{1, 2}, {5, 6}, {3, 4}, {7, 8}};
        HW3.swapRows(input, 1, 2);
        for (int i = 0; i < model.length; i++)
            assertArrayEquals("Swap the middle rows", model[i], input[i], 0.001);
        
        input = new double[][] {{1, 2}, {3, 4}, {5, 6}, {7, 8}};
        model = new double[][] {{1, 2}, {3, 4}, {5, 6}, {7, 8}};
        HW3.swapRows(input, -1, -1);
        for (int i = 0; i < model.length; i++)
            assertArrayEquals("Swap invalid rows", model[i], input[i], 0.001);
        
        input = new double[][] {};
        model = new double[][] {};
        HW3.swapRows(input, 0, 1);
        for (int i = 0; i < model.length; i++)
            assertArrayEquals("Swap empty input", model[i], input[i], 0.001);
    }
    
    /**
     * Tests the scaleRows method
     */
    @Test
    public void scaleRows() {
        double[][] input = {{1, 2}, {3, 4}};
        double[][] model = {{1, 2}, {3, 4}};
        HW3.scaleRows(input, 1, 1);
        for (int i = 0; i < model.length; i++)
            assertArrayEquals("Scale = 1 (unchanged)", model[i], input[i], 0.001);
        
        input = new double[][] {{1, 2}, {3, 4}};
        model = new double[][] {{1, 2}, {6, 8}};
        HW3.scaleRows(input, 1, 2);
        for (int i = 0; i < model.length; i++)
            assertArrayEquals("Scale > 1", model[i], input[i], 0.001);
        
        input = new double[][] {{1, 2}, {3, 4}};
        model = new double[][] {{1, 2}, {-3, -4}};
        HW3.scaleRows(input, 1, -1);
        for (int i = 0; i < model.length; i++)
            assertArrayEquals("Scale < 0", model[i], input[i], 0.001);
        
        input = new double[][] {{1, 2}, {3, 4}};
        model = new double[][] {{1, 2}, {}};
        HW3.scaleRows(input, 1, 0);
        for (int i = 0; i < model.length; i++)
            assertArrayEquals("Scale = 0", model[i], input[i], 0.001);
    }
    
    /**
     * Tests the addRows method
     */
    @Test
    public void addRows() {
        double[][] input = {{1, 1}, {1, 1}};
        double[][] model = {{1, 1}, {1, 1}};
        HW3.addRows(input, 0, 1, 0);
        for (int i = 0; i < model.length; i++)
            assertArrayEquals("Scale of addend = 0 (unchanged rows)", model[i], input[i], 0.001);
        
        input = new double[][] {{1, 1}, {1, 1}};
        model = new double[][] {{2, 2}, {1, 1}};
        HW3.addRows(input, 0, 1, 1);
        for (int i = 0; i < model.length; i++)
            assertArrayEquals("Row addition", model[i], input[i], 0.001);
        
        input = new double[][] {{1, 1}, {1, 1}};
        model = new double[][] {{-1, -1}, {1, 1}};
        HW3.addRows(input, 0, 1, -2);
        for (int i = 0; i < model.length; i++)
            assertArrayEquals("Row subtraction", model[i], input[i], 0.001);
        
        input = new double[][] {{1, 1}, {1, 1}};
        model = new double[][] {{}, {1, 1}};
        HW3.addRows(input, 0, 1, -1);
        for (int i = 0; i < model.length; i++)
            assertArrayEquals("Row subtraction to zero", model[i], input[i], 0.001);
    }
    
    /**
     * Tests the construction of a SparseMatrix
     */
    @Test
    public void sparseMatrix() {
        SparseMatrix input = new SparseMatrix(new double[][] {{0, 1, 0}, {3, 0, 2}, {0, 0, 0}, {5, 4, 0}});
        
        double[][] model = {{0, 1, 0}, {3, 0, 2}, {0, 0, 0}, {5, 4, 0}};
        double[][] trial = input.getFullMatrix();
        for (int i = 0; i < model.length; i++)
            assertArrayEquals("Full matrix", model[i], trial[i], 0.001);
        
        double[] modelNZ = {1, 3, 2, 5, 4};
        double[] trialNZ = input.getNonZeroValues();
        assertArrayEquals("Non-zero values", modelNZ, trialNZ, 0.001);
        
        int[] modelCP = {1, 0, 2, 0, 1};
        int[] trialCP = input.getColumnPositions();
        assertArrayEquals("Column positions", modelCP, trialCP);
        
        int[] modelRS = {0, 1, 3, 3, 5};
        int[] trialRS = input.getRowStarts();
        assertArrayEquals("Row starts", modelRS, trialRS);
    }
}