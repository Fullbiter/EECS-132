/**
 * The HW3 class provides static methods for operations on matrices.
 *
 * @author   Kevin Nash (kjn33)
 * @version  2015.3.20
 */
public class HW3 {
    
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
    
    public static void swapRows(double[][] matrix, int row1, int row2) {
        try {
            double[] heldRow = new double[matrix[row1].length];
            for (int i = 0; i < matrix[row1].length; i++)
                heldRow[i] = matrix[row1][i];
            matrix[row1] = matrix[row2];
            matrix[row2] = heldRow;
        }
        catch (ArrayIndexOutOfBoundsException e) {
            ; // do nothing
        }
    }
    
    public static void scaleRows(double[][] matrix, int row, double scale) {
        try {
            if (scale == 0)
                matrix[row] = new double[] {};
            else {
                for (int i = 0; i < matrix[row].length; i++)
                    matrix[row][i] *= scale;
            }
        }
        catch (ArrayIndexOutOfBoundsException e) {
            ; // do nothing
        }
    }
    
    /***********************************************************************************************************
     *                           Everything below is to be removed before completion                           *
     ***********************************************************************************************************/
    public static void matrixVisualizer(int[][] matrix) {
        // print the entire contents of a matrix
        for (int[] ar : matrix) {
            for (int n : ar)
                System.out.print(n + " ");
            System.out.println();
        }
    }
}