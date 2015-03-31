import java.util.Arrays;
/**
 * The HW3 class provides static methods for operations on matrices.
 *
 * @author   Kevin Nash (kjn33)
 * @version  2015.3.30
 */
public class HW3 {
    
    /**
     * Returns the input double matrix with the specified row removed
     * @param  matrix  any 2D double array
     * @param  row     the target row of matrix
     * @return  A version of the input matrix with a row removed
     */
    public static double[][] removeRow(double[][] matrix, int row) {
        // do nothing for an invalid row
        if (row >= matrix.length || row < 0)
            return matrix;
        
        // create new matrix with one fewer row than the input matrix
        double[][] newMatrix = new double[matrix.length - 1][0];
        
        // copy matrix values from rows before the input row to the new matrix
        for (int i = 0; i < row; i++) {
            newMatrix[i] = new double[matrix[i].length];
            for (int j = 0; j < newMatrix[i].length; j++)
                newMatrix[i][j] = matrix[i][j];
        }
        
        // copy matrix values from rows after the input row to the new matrix
        for (int i = row; i < matrix.length - 1; i++) {
            newMatrix[i] = new double[matrix[i + 1].length];
            for (int j = 0; j < newMatrix[i].length; j++)
                newMatrix[i][j] = matrix[i + 1][j];
        }
        
        return newMatrix;
    }
    
    /**
     * Returns the input SparseMatrix with the specified row removed
     * @param  matrix  a SparseMatrix to modify
     * @param  row     the target row of matrix
     * @return  A version of the input SparseMatrix with a row removed
     */
    public static SparseMatrix removeRow(SparseMatrix matrix, int row) {
        // do nothing for an invalid row
        if (row >= matrix.getNonZeroValues().length / (matrix.getRowStarts().length - 1) || row < 0)
            return matrix;
        
        // the number of non-zero values in the target row
        int nnzToRemove = matrix.getRowStarts()[row + 1] - matrix.getRowStarts()[row];
        // the number of non-zero values before the target row
        int nnzPrev = matrix.getRowStarts()[row];
        // temporary index
        int j = 0;
        
        // updated SparseMatrix parameters
        double[] newNonZeroValues = new double[matrix.getNonZeroValues().length - nnzToRemove];
        int[] newColumnPositions = new int[matrix.getColumnPositions().length - nnzToRemove];
        int[] newRowStarts = new int[matrix.getRowStarts().length - 1];
        
        // directly copy old nonZeroValues before the removed values into newNonZeroValues
        // directly copy old columnPositions before the removed values into newColumnPositions
        for (int i = 0; i < nnzPrev/*matrix.getRowStarts()[row + 1] - 1*/; i++) {
            newNonZeroValues[i] = matrix.getNonZeroValues()[i];
            newColumnPositions[i] = matrix.getColumnPositions()[i];
        }
        
        // copy old nonZeroValues minus removed values, starting with the first value after those removed
        // copy old columnPositions ...
        j = nnzPrev;
        for (int i = nnzPrev + nnzToRemove; i < matrix.getNonZeroValues().length; i++) {
            newNonZeroValues[j] = matrix.getNonZeroValues()[i];
            newColumnPositions[j++] = matrix.getColumnPositions()[i];
        }
        
        // directly copy old rowStarts before row into newRowStarts
        for (int i = 0; i < row + 1; i++)
            newRowStarts[i] = matrix.getRowStarts()[i];
        
        // copy old rowStarts minus removed values into newRowStarts, starting at row
        j = row;
        for (int i = row + 1; i < matrix.getRowStarts().length; i++)
            newRowStarts[j++] = matrix.getRowStarts()[i] - nnzToRemove;
        
        return new SparseMatrix(newNonZeroValues, newColumnPositions, newRowStarts);
    }
    
    /**
     * Returns the input double matrix with the specified column removed
     * @param  matrix  any 2D double array
     * @param  column  the target column of matrix
     * @return  A version of the input matrix with a column removed
     */
    public static double[][] removeColumn(double[][] matrix, int column) {
        // do nothing for an invalid column
        if (column < 0)
            return matrix;
        
        // replacement row
        double[] newRow = {};
        
        for (int i = 0; i < matrix.length; i++) {
            if (matrix[i].length > 0)
                newRow = new double[matrix[i].length - 1];
            
            if (column < matrix[i].length) {
                // copy values before column into newRow
                for (int j = 0; j < column; j++) {
                    newRow[j] = matrix[i][j];
                }
                if (column < matrix[i].length - 1) {
                    // copy values after column into newRow
                    for (int j = column + 1; j < matrix[i].length; j++)
                        newRow[j - 1] = matrix[i][j];
                }
                
            }
            matrix[i] = newRow;
        }
        return matrix;
    }
    
    /**
     * Returns the input SparseMatrix with the specified column removed
     * @param  matrix  any SparseMatrix
     * @param  column  the target column of matrix
     * @return  A version of the input matrix with a column removed
     */
    public static SparseMatrix removeColumn(SparseMatrix matrix, int column) {
        return new SparseMatrix(new double[][] {{}});
    }
    
    public static double determinant(double[][] matrix) {
        System.out.println("Method Start");
        System.out.println(Arrays.deepToString(matrix));
        // subdeterminant for even indices
        double evenDeterminant = 0.0;
        // subdeterminant for odd indices
        double oddDeterminant = 0.0;
        // temporary storage for a sum
        double sum = 0.0;
        // temporary storage for an index
        int i = 0;
        
        // return zero given no rows
        if (matrix.length == 0) {
            System.out.println("Return 1: " + 0);
            return 0.0;
        }
        
        // return a difference of sums given a single row
        if (matrix.length == 1) {
            while (i < matrix[0].length)
                sum += matrix[0][i] * ((++i % 2 != 0) ? 1 : -1);
            System.out.println("Return 2: " + sum);
            return sum;
        }
        
        // sum the first row evens
        sum = 0.0;
        i = 0;
        while (i < matrix[0].length) {
            sum += matrix[0][i];
            i += 2;
        }
        System.out.println("even sum = " + sum);
        // remove the first row and the first even column of matrix
        // calculate determinant of result, multiply by sum
        evenDeterminant = sum * determinant(removeColumn(removeRow(matrix, 0), 0));
        
        // sum the first row odds
        sum = 0.0;
        i = 1;
        while (i < matrix[0].length) {
            sum += matrix[0][i];
            i += 2;
        }
        System.out.println("odd sum = " + sum);
        // remove the first row and the first odd column of matrix
        // calculate determinant of result, multiply by sum
        oddDeterminant = sum * determinant(removeColumn(removeRow(matrix, 0), 1));
        
        System.out.println("Return 3: " + (evenDeterminant - oddDeterminant));
        // return the difference of the two subdeterminants
        return evenDeterminant - oddDeterminant;
    }
    
    /**
     * Returns the input SparseMatrix with the specified column removed
     * @param  matrix  any SparseMatrix
     * @param  column  the target column of matrix
     * @return  A version of the input matrix with a column removed
     */
    public static SparseMatrix determinant(SparseMatrix matrix) {
        return new SparseMatrix(new double[][] {{}});
    }
    
    /**
     * Replaces one specified row of the input matrix with a second, and vice versa
     * @param  matrix  any 2D double array
     * @param  row1    the first target row of matrix
     * @param  row2    the second target row of matrix
     */
    public static void swapRows(double[][] matrix, int row1, int row2) {
        // row1 and row2 exist in matrix
        if (row1 < matrix.length && row1 > 0 && row2 < matrix.length && row2 > 0) {
            // temporary 1D array to store the contents of row1
            double[] heldRow = new double[matrix[row1].length];
            
            // copy the contents of row1 into heldRow
            for (int i = 0; i < matrix[row1].length; i++)
                heldRow[i] = matrix[row1][i];
            
            // replace row1 with row2
            matrix[row1] = matrix[row2];
            // replace row2 with heldRow (holding original row1)
            matrix[row2] = heldRow;
        }
    }
    
    /**
     * Multiplies each element of a specified row in the input matrix by a specified scale
     * @param  matrix  any 2D double array
     * @param  row1    the target row
     * @param  scale   number by which to multiply
     */
    public static void scaleRows(double[][] matrix, int row, double scale) {
        // row exists in matrix
        if (row < matrix.length && row > 0) {
            if (scale == 0)
                // remove the contents of row
                matrix[row] = new double[] {};
            else {
                // multiply each element of row by scale
                for (int i = 0; i < matrix[row].length; i++)
                    matrix[row][i] *= scale;
            }
        }
    }
    
    /**
     * Adds one row of an input matrix to another a specified number of times
     * @param  matrix   any 2D double array
     * @param  toRow    the destination row
     * @param  fromRow  the source row
     * @param  scale    number by which to multiply
     */
    public static void addRows(double[][] matrix, int toRow, int fromRow, double scale) {
        // resize toRow if its length is less than that of the incoming row
        if (matrix[fromRow].length > matrix[toRow].length) {
            double[] resizedToRow = new double[matrix[fromRow].length];
            
            // copy the values of toRow into resizedToRow
            for (int i = 0; i < matrix[toRow].length; i++)
                resizedToRow[i] = matrix[toRow][i];
            
            // replace toRow with resizedToRow
            matrix[toRow] = resizedToRow;
        }
        
        // add each element of fromRow times scale to toRow
        for (int i = 0; i < matrix[toRow].length; i++)
            matrix[toRow][i] += matrix[fromRow][i] * scale;
        
        // stores the number of consecutive zero values backward from the last index of toRow
        int truncationLength = 0;
        // increments truncationLength accordingly
        for (int i = matrix[toRow].length - 1; matrix[toRow][i] == 0 && i >= 0; i--)
            truncationLength++;
        // if there is a truncation to be made
        if (truncationLength > 0) {
            double[] resizedToRow = new double[matrix[toRow].length - truncationLength];
            // fill the resized row with corresponding values
            for (int i = 0; i < resizedToRow.length; i++)
                resizedToRow[i] = matrix[toRow][i];
            matrix[toRow] = resizedToRow;
        }
    }
    
    public static double[][] invert(double[][] matrix) throws NotInvertibleException {
        // create a shallow copy of matrix
        double[][] workingMatrix = new double[matrix.length][matrix[0].length];
        
        // fill workingMatrix with values of matrix while checking for square-ness
        for (int i = 0; i < matrix.length; i++) {
            if (matrix[i].length > matrix.length)
                throw new NotInvertibleException();
            for (int j = 0; j < matrix[i].length; j++)
                workingMatrix[i][j] = matrix[i][j];
        }
        return matrix;
    }
}

/**
 * A NotInvertibleException is thrown when a matrix cannot be inverted.
 *
 * @author   Kevin Nash (kjn33)
 * @version  2015.3.30
 */
class NotInvertibleException extends Exception {
    /**
     * Constructor with no parameters
     */
    NotInvertibleException() {}
    
    /**
     * Constructor inherits behavior for messages
     */
    NotInvertibleException(String message) {
        super (message);
    }
    
    /**
     * Constructor inherits behavior for Throwables
     */
    NotInvertibleException(Throwable cause) {
        super (cause);
    }
    
    /**
     * Constructor inherits behavior for messages and Throwables
     */
    NotInvertibleException(String message, Throwable cause) {
        super (message, cause);
    }
}
