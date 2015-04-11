/**
 * The HW3 class provides static methods for operations on matrices.
 *
 * @author   Kevin Nash (kjn33)
 * @version  2015.3.31
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
        if (row > matrix.getNonZeroValues().length / (matrix.getRowStarts().length - 1) || row < 0)
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
        for (int i = 0; i < nnzPrev; i++) {
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
        // new matrix of the same length as matrix
        double[][] newMatrix = new double[matrix.length][0];
        // replacement row
        double[] newRow = {};
        
        // do nothing for an invalid column
        if (column < 0)
            return matrix;
        
        // iterate over every row in newMatrix
        for (int i = 0; i < newMatrix.length; i++) {
            
            // the row is long enough to contain column
            if (column < matrix[i].length) {
                newRow = new double[matrix[i].length - 1];
                // copy values before column into newRow
                for (int j = 0; j < column; j++)
                    newRow[j] = matrix[i][j];
                // copy values after column into newRow
                for (int j = column; j < matrix[i].length - 1; j++)
                    newRow[j] = matrix[i][j + 1];
            }
            // the row is not long enough to contain column
            else {
                newRow = new double[matrix[i].length];
                // directly copy all elements of the row into newRow
                for (int j = 0; j < newRow.length; j++)
                    newRow[j] = matrix[i][j];
            }
            newMatrix[i] = newRow;
        }
        return newMatrix;
    }
    
    /**
     * Returns the input SparseMatrix with the specified column removed
     * @param  matrix  any SparseMatrix
     * @param  column  the target column of matrix
     * @return  A version of the input matrix with a column removed
     */
    public static SparseMatrix removeColumn(SparseMatrix matrix, int column) {
        // the greatest value in columnPositions
        int maxPosition = 0;
        for (int position : matrix.getColumnPositions())
            maxPosition = (maxPosition > position) ? maxPosition : position;
        // do nothing for an invalid column
        if(column > maxPosition || column < 0)
            return matrix;
        
        // the number of rows in the matrix
        int height = matrix.getRowStarts().length - 1;
        // storage for an index in matrix.nonZeroValues
        int k = 0;
        // new matrix to output
        double[][] newMatrix = new double[height][0]; 
        for (int i = 1; i < height; i++) {
            // the number of non-zero values in a row
            int rowLength = matrix.getRowStarts()[i] - matrix.getRowStarts()[i - 1];
            // new row to put in newMatrix
            double[] newRow = new double[rowLength];
            if (column < rowLength) {
                // copy values before column into newMatrix;
                for (int j = 0; j < column; j++)
                    newRow[j] = matrix.getNonZeroValues()[k++];
                k++;
                // copy values after column into newMatrix;
                for (int j = column; j < rowLength; j++)
                    newRow[j] = matrix.getNonZeroValues()[k++];
            }
            else {
                // directly copy values into newRow
                for (int j = 0; j < rowLength; j++)
                    newRow[j] = matrix.getNonZeroValues()[k++];
            }
            newMatrix[i] = newRow;
        }
        return new SparseMatrix(newMatrix);
    }
    
    /**
     * Calculates the determinant of an input matrix
     * @param  matrix  any matrix
     * @return  the determinant of a matrix
     */
    public static double determinant(double[][] matrix) {
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
            return 0.0;
        }
        
        // return a difference of sums given a single row
        if (matrix.length == 1) {
            while (i < matrix[0].length)
                sum += matrix[0][i] * ((++i % 2 != 0) ? 1 : -1);
            return sum;
        }
        
        // sum the first row evens
        sum = 0.0;
        i = 0;
        while (i < matrix[0].length) {
            sum += matrix[0][i];
            i += 2;
        }
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
        // remove the first row and the first odd column of matrix
        // calculate determinant of result, multiply by sum
        oddDeterminant = sum * determinant(removeColumn(removeRow(matrix, 0), 1));
        
        // return the difference of the two subdeterminants
        return evenDeterminant - oddDeterminant;
    }
    
    /**
     * Returns the input SparseMatrix with the specified column removed
     * @param  matrix  any SparseMatrix
     * @param  column  the target column of matrix
     * @return  A version of the input matrix with a column removed
     */
    public static double determinant(SparseMatrix matrix) {
        // subdeterminant for even indices
        double evenDeterminant = 0.0;
        // subdeterminant for odd indices
        double oddDeterminant = 0.0;
        // temporary storage for a sum
        double sum = 0.0;
        // temporary storage for a row length
        int rowLength = 0;
        // temporary storage for an index
        int i = 0;
        
        // return zero given no rows
        if (matrix.getNonZeroValues().length == 0) {
            return 0.0;
        }
        
        // return a difference of sums given a single row
        if (matrix.getRowStarts().length == 2) {
            while (i < matrix.getNonZeroValues().length)
                sum += matrix.getNonZeroValues()[i] * ((++i % 2 != 0) ? 1 : -1);
            return sum;
        }
        
        rowLength = matrix.getRowStarts()[1] - matrix.getRowStarts()[0];
        
        // sum the first row evens
        sum = 0.0;
        i = 0;
        while (i < rowLength) {
            sum += matrix.getNonZeroValues()[i];
            i += 2;
        }
        // remove the first row and the first even column of matrix
        // calculate determinant of result, multiply by sum
        evenDeterminant = sum * determinant(removeColumn(removeRow(matrix, 0), 0));
        
        // sum the first row odds
        sum = 0.0;
        i = 1;
        while (i < rowLength) {
            sum += matrix.getNonZeroValues()[i];
            i += 2;
        }
        // remove the first row and the first odd column of matrix
        // calculate determinant of result, multiply by sum
        oddDeterminant = sum * determinant(removeColumn(removeRow(matrix, 0), 1));
        
        // return the difference of the two subdeterminants
        return evenDeterminant - oddDeterminant;
    }
    
    /**
     * Replaces one specified row of the input matrix with a second, and vice versa
     * @param  matrix  any 2D double array
     * @param  row1    the first target row of matrix
     * @param  row2    the second target row of matrix
     */
    public static void swapRows(double[][] matrix, int row1, int row2) {
        // row1 and row2 exist in matrix
        if (row1 < matrix.length && row1 >= 0 && row2 < matrix.length && row2 >= 0) {
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
        for (int i = 0; i < matrix[fromRow].length; i++)
            matrix[toRow][i] += matrix[fromRow][i] * scale;
        
        // stores the number of consecutive zero values backward from the last index of toRow
        int truncationLength = 0;
        // increments truncationLength accordingly
        for (int i = matrix[toRow].length - 1; i >= 0 && matrix[toRow][i] == 0; i--)
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
    
    /**
     * Inverts a square matrix
     * @param  matrix  any square 2D double array
     * @return  inverse of the input matrix
     */
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
        
        double[][] resultMatrix = new double[matrix.length][0];
        // create a shallow resultMatrix (diagonal)
        for (int i = 0; i < workingMatrix.length; i++) {
            resultMatrix[i] = new double[i + 1];
            resultMatrix[i][resultMatrix[i].length - 1] = 1;
        }
        
        // ensure the [i][i] indices are non-zero by iterating over every row
        for (int i = 0; i < workingMatrix.length; i++) {
            if (workingMatrix[i][i] == 0) {
                int j = 1 + 1;
                // find a non-zero value for i
                while (workingMatrix[j][i] == 0 && j < workingMatrix[i].length)
                    j++;
                if (j >= workingMatrix[i].length)
                    throw new NotInvertibleException();
                else {
                    swapRows(workingMatrix, i, j);
                    swapRows(resultMatrix, i, j);
                }
            }
            // interate over every row before i
            for (int j = 0; j < i; j++) {
                addRows(workingMatrix, j, i, -1 * workingMatrix[j][i] / workingMatrix[i][i]);
                addRows(resultMatrix, j, i, -1 * workingMatrix[j][i] / workingMatrix[i][i]);
            }
            // interate over every row after i
            for (int j = i + 1; j < workingMatrix.length; j++) {
                addRows(workingMatrix, j, i, -1 * workingMatrix[j][i] / workingMatrix[i][i]);
                addRows(resultMatrix, j, i, -1 * workingMatrix[j][i] / workingMatrix[i][i]);
            }
            scaleRows(workingMatrix, i, 1.0 / workingMatrix[i][i]);
            scaleRows(resultMatrix, i, 1.0 / workingMatrix[i][i]);
        }
        return resultMatrix;
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
