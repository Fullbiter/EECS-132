/**
 * The HW3 class provides static methods for operations on matrices.
 *
 * @author   Kevin Nash (kjn33)
 * @version  2015.3.24
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
        double[][] newMatrix = new double[matrix.length - 1][matrix[0].length];
        
        // copy matrix values from rows before the input row to the new matrix
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < matrix[i].length; j++)
                newMatrix[i][j] = matrix[i][j];
        }
        
        // copy matrix values from rows after the input row to the new matrix
        for (int i = row; i < matrix.length - 1; i++) {
            for (int j = 0; j < matrix[i].length; j++)
                newMatrix[i][j] = matrix[i + 1][j];
        }
        
        return newMatrix;
    }
    
    public static SparseMatrix removeRow(SparseMatrix matrix, int row) {
        return new SparseMatrix();
    }
    
    /**
     * Returns the input double matrix with the specified column removed
     * @param  matrix  any 2D double array
     * @param  column  the target column of matrix
     * @return  A version of the input matrix with a column removed
     */
    public static double[][] removeColumn(double[][] matrix, int column) {
        // do nothing for an invalid column
        if (column >= matrix[0].length || column < 0)
            return matrix;
        
        // create new matrix with one fewer column than the input matrix
        double[][] newMatrix = new double[matrix.length][matrix[0].length - 1];
        
        // copy matrix values from columns before the input column to the new matrix
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < column; j++)
                newMatrix[i][j] = matrix[i][j];
        }
        
        // copy matrix values from columns after the input column to the new matrix
        for (int i = 0; i < matrix.length; i++) {
            for (int j = column; j < matrix[i].length - 1; j++)
                newMatrix[i][j] = matrix[i][j + 1];
        }
        
        return newMatrix;
    }
    
    public static SparseMatrix removeColumn(SparseMatrix matrix, int column) {
        return new SparseMatrix();
    }
    
    public static double determinant(double[][] matrix) {
        return 0.0;
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
            // 
            double[] resizedToRow = new double[matrix[toRow].length - truncationLength];
            // 
            for (int i = 0; i < resizedToRow.length; i++)
                resizedToRow[i] = matrix[toRow][i];
            matrix[toRow] = resizedToRow;
        }
    }
    
    public static double[][] invert(double[][] matrix) throws NotInvertibleException {
        // create a shallow copy of matrix
        double[][] workingMatrix = new double[matrix.length][matrix[0].length];
        
//        try {
            prevRowLength = matrix[0].length;
            // copy matrix values from rows before the input row to the new matrix
            for (int i = 0; i < matrix.length; i++) {
                if (prevRowLength != matrix[i].length)
                    throw new NotInvertibleException();
                for (int j = 0; j < matrix[i].length; j++) {
                    workingMatrix[i][j] = matrix[i][j];
                }
            }
//        }
//        catch (ArrayIndexOutOfBoundsException e) {
//            throw new NotInvertibleException();
//        }
    }
}

class NotInvertibleException extends Exception {
    
    NotInvertibleException (String message) {
        super (message);
    }

    NotInvertibleException (Throwable cause) {
        super (cause);
    }

    NotInvertibleException (String message, Throwable cause) {
        super (message, cause);
    }
}