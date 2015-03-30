/**
 * SparseMatrix objects represent a matrix using three arrays to save memory
 *
 * @author   Kevin Nash (kjn33)
 * @version  2015.3.30
 */
public class SparseMatrix {
    
    /** the nonzero entries of this SparseMatrix **/
    public double[] nonZeroValues;
    
    /** the column index of each element of values **/
    public int[] columnPositions;
    
    /** the index in values of the first element in each row **/
    public int[] rowStarts;
    
    /**
     * Constructor for SparseMatrix in native form
     * @param  nonZeroValues    the nonzero entries of this SparseMatrix
     * @param  columnPositions  the column index of each element of values
     * @param  rowStarts        the index in values of the first element in each row
     */
    public SparseMatrix(double[] nonZeroValues, int[] columnPositions, int[] rowStarts) {
        this.nonZeroValues = nonZeroValues;
        this.columnPositions = columnPositions;
        this.rowStarts = rowStarts;
    }
    
    /**
     * Constructor for SparseMatrix in two-dimensional array form
     * @param  matrix  the matrix used to construct a SparseMatrix
     */
    public SparseMatrix(double[][] matrix) {
        // the number of non-zero entries in matrix
        int nnz = 0;
        // interate over the length of every row in matrix,
        // increment count of non-zero entries when necessary
        for (double[] row : matrix) {
            for (double entry : row) {    // Note: This would be needlessly expensive if this assignment
                if (entry > 0)            // allowed the use of a resizable data structure such as
                    nnz++;                // an ArrayList. I need to know NNZ before I can populate values.
            }
        }
        
        nonZeroValues = new double[nnz];
        columnPositions = new int[nnz];
        rowStarts = new int[matrix.length + 1];
        
        // index in values and columnPositions
        int valueIndex = 0;
        // index in rowStarts
        int rowStartIndex = 1;
        // interate over the length of matrix
        for (double[] row : matrix) {
            // give rowStarts at the current index the value at the previous index
            rowStarts[rowStartIndex] = rowStarts[rowStartIndex - 1];
            // iterate over the length of row
            for (int col = 0; col < row.length; col++) {
                if (row[col] > 0) {
                    nonZeroValues[valueIndex] = row[col];
                    columnPositions[valueIndex++] = col;
                    rowStarts[rowStartIndex]++;
                }
            }
            rowStartIndex++;
        }
    }
    
    /**
     * Getter method for the non-zero values held by a SparseMatrix
     * @return  non-zero values
     */
    public double[] getNonZeroValues() {
        return nonZeroValues;
    }
    
    /**
     * Setter method for the non-zero values held by a SparseMatrix
     * @param  non-zero values
     */
    public void setNonZeroValues(double[] nonZeroValues) {
        this.nonZeroValues = nonZeroValues;
    }
    
    /**
     * Getter method for the column indices of non-zero values held by a SparseMatrix
     * @return  column indices
     */
    public int[] getColumnPositions() {
        return columnPositions;
    }
    
    /**
     * Setter method for the column indices of non-zero values held by a SparseMatrix
     * @param  column indices
     */
    public void setColumnPositions(int[] columnPositions) {
        this.columnPositions = columnPositions;
    }
    
    /**
     * Getter method for the values of the first element in each row of a SparseMatrix
     * @return  the first values in each row
     */
    public int[] getRowStarts() {
        return rowStarts;
    }
    
    /**
     * Setter method for the values of the first element in each row of a SparseMatrix
     * @param  the first values in each row
     */
    public void setRowStarts(int[] rowStarts) {
        this.rowStarts = rowStarts;
    }
    
    /**
     * Returns a matrix representation of the SparseMatrix with no truncation
     * @return  Untruncated matrix
     */
    public double[][] getFullMatrix() {
        // the maximum length of a row
        int maxLength = 0;
        // iterate over the length of columnPositions
        for (int position : columnPositions)
            // update maxLength, ternary
            maxLength = (maxLength > position + 1) ? maxLength : position + 1;
        
        double[][] fullMatrix = new double[rowStarts.length - 1][maxLength];
        
        // the current row
        int row = 0;
        // the maximum number of non-zero values that can be placed in the current row
        int maxRowNNZ = 0;
        // the current number ...
        int curRowNNZ = 0;
        // the index in nonZeroValues of the last element copied
        int valueIndex = 0;
        
        // iterate over rowStarts, skipping the zeroth index
        while (row + 1 < rowStarts.length) {
            // the maxRowNNZ = the change in rowStarts (always non-negative)
            maxRowNNZ = rowStarts[row + 1] - rowStarts[row];
            // reset the current number of non-zero values
            curRowNNZ = 0;
            // iterate a number of times equal to maxRowNNZ
            while (curRowNNZ < maxRowNNZ) {
                // copy the value at valueIndex into the proper position in fullMatrix
                fullMatrix[row][columnPositions[valueIndex]] = nonZeroValues[valueIndex];
                // increment counters to reflect the above operation
                valueIndex++;
                curRowNNZ++;
            }
            row++;
        }
        return fullMatrix;
    }
}
