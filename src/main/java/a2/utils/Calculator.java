package a2.utils;

import a2.conf.ViolatedMatrixMultiplicationRules;

/**
 * Calculator utility for various calculating tasks.
 * 
 * @author julian
 *
 */
public class Calculator {
    // ----------------------------------------------------------------------------------------------

    /**
     * Calculates the result of the multiplication of two matrices. Note that the
     * multiplication of A x B will return another result then the multiplication of
     * B x A, because the commutative law is not valid for the multiplication of
     * matrices.
     * 
     * @param matrixA The first matrix
     * @param matrixB The second matrix
     * @return The result matrix C as an two dimensional array
     */
    public static Matrix multiplyMatrix(final Matrix matrixA, final Matrix matrixB) {
        try {
            checkMatrixMultiplicationRules(matrixA, matrixB);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
        
        Matrix resultMatrix = new Matrix();
        int rowNumberA = matrixA.getRowNumber();
        int columnNumberB = matrixB.getColumnNumber();

        int[][] arrayResult = new int[rowNumberA][columnNumberB];

        int[] scalarA = new int[columnNumberB];
        int[] scalarB = new int[rowNumberA];
        for (int i = 0; i < rowNumberA; i++) {

            scalarA = getHorizontalScalar(i, matrixA);
            for (int j = 0; j < columnNumberB; j++) {

                scalarB = getVerticalScalar(j, matrixB);
                if (scalarA.length != 0 && scalarB.length != 0) {
                    arrayResult[i][j] = calculateScalar(scalarA, scalarB);
                }
            }
        }
        resultMatrix.setRows(arrayResult);
        return resultMatrix;
        
    }

    // ----------------------------------------------------------------------------------------------

    /**
     * Checks whether the matrices comply with the rules or not.
     * 
     * @param matrixA The first matrix
     * @param matrixB The second matrix
     * @return Whether the matrices comply with the rules or not
     */ 
    public static void checkMatrixMultiplicationRules(final Matrix matrixA, final Matrix matrixB) 
            throws ViolatedMatrixMultiplicationRules{
        int rowNumberA = matrixA.getRowNumber();
        int columnNumberB = matrixB.getColumnNumber();
        int[] scalarA = new int[columnNumberB];
        int[] scalarB = new int[rowNumberA];
        for (int i = 0; i < rowNumberA; i++) {

            scalarA = getHorizontalScalar(i, matrixA);
            for (int j = 0; j < columnNumberB; j++) {

                scalarB = getVerticalScalar(j, matrixB);
                if (scalarA.length != 0 && scalarB.length != 0) {
                    calculateScalar(scalarA, scalarB);
                }
            }
        }
    }

    // ----------------------------------------------------------------------------------------------

    /**
     * Returns an int array containing an scalar from the given matrix in the given
     * row.
     * 
     * @param rowIndex The index to get the values from
     * @param matrix   The Matrix to get the scalar from
     * @return The horizontal scalar of the given column.
     */
    private static int[] getHorizontalScalar(int rowIndex, Matrix matrix) {
        if (rowIndex >= 0 && rowIndex < matrix.getRowNumber()) {
            int[] resultSkalar = new int[matrix.getColumnNumber()];

            for (int j = 0; j < matrix.getColumnNumber(); j++) {
                resultSkalar[j] = matrix.getElements()[rowIndex][j];
            }

            return resultSkalar;

        } else {
            return null;

        }
    }

    /**
     * Returns an int array containing an scalar from the given matrix in the given
     * column.
     * 
     * @param columnIndex The index to get the values from
     * @param matrix      The Matrix to get the scalar from
     * @return The vertical scalar of the given column.
     */
    private static int[] getVerticalScalar(int columnIndex, Matrix matrix) {
        if (columnIndex >= 0 && columnIndex < matrix.getColumnNumber()) {
            int[] resultSkalar = new int[matrix.getRowNumber()];

            for (int i = 0; i < matrix.getRowNumber(); i++) {
                resultSkalar[i] = matrix.getElements()[i][columnIndex];
            }

            return resultSkalar;

        } else {
            return null;

        }
    }

    /**
     * Calculates the product of the two given scalars.
     * 
     * @param firstScalar  The first scalar
     * @param secondScalar The second scalar
     * @return The result of the calculation
     */
    private static int calculateScalar(int[] firstScalar, int[] secondScalar) 
            throws ViolatedMatrixMultiplicationRules {
        int result = 0;

        for (int i = 0; i < firstScalar.length; i++) {
            try {
                result = result + (firstScalar[i] * secondScalar[i]);    
            } catch (ArrayIndexOutOfBoundsException e) {
                throw new ViolatedMatrixMultiplicationRules("Die Regeln der Matrizenmultiplikation "
                        + "wurden nicht eingehalten.\nBeachten sie dass bei einer "
                        + "Matrizenmultiplikation die erste Matrix\ndieselbe Anzahl an Spalten "
                        + "haben muss wie die Zeilen der zweiten Matrix");
            }
        }            

        return result;
    }

    // ----------------------------------------------------------------------------------------------
}
