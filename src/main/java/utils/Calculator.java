package utils;

import a2.Matrix;
import conf.ViolatedMatrixMultiplicationRulesException;

/**
 * Utility class for various calculating tasks.
 * 
 * @author julian
 *
 */
public final class Calculator {
    // ---------------------------------------------------------------------------------------------

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
        resultMatrix.setElements(arrayResult);
        return resultMatrix;

    }

    // ---------------------------------------------------------------------------------------------

    /**
     * Returns an array of int containing an scalar from the given matrix in the
     * given row.
     * 
     * @param rowIndex The index to get the values from
     * @param matrix   The Matrix to get the scalar from
     * @return The horizontal scalar of the given column.
     */
    public static int[] getHorizontalScalar(int rowIndex, Matrix matrix) {
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
     * Returns an array of int containing an scalar from the given matrix in the given
     * column.
     * 
     * @param columnIndex The index to get the values from
     * @param matrix      The Matrix to get the scalar from
     * @return The vertical scalar of the given column.
     */
    public static int[] getVerticalScalar(int columnIndex, Matrix matrix) {
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
     * Calculates the result of the two given scalars.
     * 
     * @param firstScalar  The first scalar
     * @param secondScalar The second scalar
     * @return The result of the calculation
     */
    public static int calculateScalar(int[] firstScalar, int[] secondScalar)
            throws ViolatedMatrixMultiplicationRulesException {
        int result = 0;

        if (firstScalar.length != secondScalar.length) {
            throw new ViolatedMatrixMultiplicationRulesException();
        }

        for (int i = 0; i < firstScalar.length; i++) {
            try {
                result = result + (firstScalar[i] * secondScalar[i]);
            } catch (ArrayIndexOutOfBoundsException e) {
                throw new ViolatedMatrixMultiplicationRulesException();
            }
        }
        return result;

    }

    // ---------------------------------------------------------------------------------------------

    /**
     * Checks whether the matrices comply with the mathematical rules of
     * multiplication or not.
     * 
     * @param matrixA The first matrix
     * @param matrixB The second matrix
     * @return Whether the matrices comply with the rules or not
     */
    public static void checkMatrixMultiplicationRules(final Matrix matrixA, final Matrix matrixB)
            throws ViolatedMatrixMultiplicationRulesException {
        if (matrixA.getColumnNumber() != matrixB.getRowNumber()) {
            throw new ViolatedMatrixMultiplicationRulesException();
        }
    }

    // ---------------------------------------------------------------------------------------------
}
