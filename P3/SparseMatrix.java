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
    
    public SparseMatrix(double[] nonZeroValues) {}
    
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
        int iV = 0;
        // index in rowStarts
        int iRS = 1;
        // interate over the length of matrix
        for (double[] row : matrix) {
            // give rowStarts at the current index the value at the previous index
            rowStarts[iRS] = rowStarts[iRS - 1];
            // iterate over the length of row
            for (int col = 0; col < row.length; col++) {
                if (row[col] > 0) {
                    nonZeroValues[iV] = row[col];
                    columnPositions[iV++] = col;
                    rowStarts[iRS]++;
                }
            }
            iRS++;
        }
    }
}