/**
 * The SparseMatrix class.
 *
 * @author   Kevin Nash (kjn33)
 * @version  2015.3.30
 */
public class SparseMatrix {
    
    /** the nonzero entries of this SparseMatrix **/
    public double[] values;
    
    /** the index in values of the first element in each row **/
    public int[] rowStarts;
    
    /** the column index of each element of values **/
    public int[] columnPositions;
    
    
    public SparseMatrix(double[][] matrix) {
        // the number of nonzero entries in matrix
        int nnz = 0;
        // interate over every entry in matrix
        for (double[] row : matrix) {
            for (double entry : row) {
                if (entry > 0)
                    nnz++;
            }
        }
        
        values = new double[nnz];
        rowStarts = new int[matrix.length + 1];
        rowStarts[0] = 0;
        rowStarts[rowstarts.length - 1] = nnz;
        columnPositions = new int[nnz];
        
        int i = 0;
        // interate over every entry in matrix
        for (double[] row : matrix) {
            for (double entry : row) {
                if (entry > 0) {
                    values[i++] = entry;
                    rowStarts
                }
            }
        }
        
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; i < matrix[i].length; j++) {
                if (matrix[i][j] > 0) {
                    values[k++] = matrix[i][j];
                    
                }
            }
        }
    }
}