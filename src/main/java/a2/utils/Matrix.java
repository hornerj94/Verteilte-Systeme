package a2.utils;

/**
 * Represents an mathematical matrix.
 * 
 * @author julian
 *
 */
public class Matrix {
    // ---------------------------------------------------------------------------------------------

    /** The row number of the matrix. */
    private int rowNumber;

    /** The column number of the matrix. */
    private int columnNumber;

    /** The elements of the matrix as an two dimensional array. */
    private int[][] elements;

    // ---------------------------------------------------------------------------------------------

    /**
     * Gets the elements from the matrix.
     * 
     * @return The elements from the matrix
     */
    public int[][] getElements() {
        return elements;
    }

    /**
     * Sets the content of the matrix and checks whether the given values are valid
     * or not.
     * 
     * @param elements The rows to set for the matrix as an array.
     */
    public void setRows(final int[][] elements) {
        if (elementsAreValid(elements) && !isEmpty(elements)) {
            this.elements = elements;
            rowNumber = elements.length;
            columnNumber = elements[0].length;

        } else {
            System.out.println("Die übergebene Matrix ist nicht korrekt oder leer");
        }
    }

    /**
     * Gets the row number from the matrix.
     * 
     * @return The row number from the matrix
     */
    public int getRowNumber() {
        return rowNumber;
    }

    /**
     * Gets the column number from the matrix.
     * 
     * @return The column number from the matrix
     */
    public int getColumnNumber() {
        return columnNumber;
    }

    // ---------------------------------------------------------------------------------------------

    /**
     * Checks the given two dimensional array for being empty.
     * 
     * @param elements The elements to check 
     * @return Whether the given elements are empty or not
     */
    private boolean isEmpty(final int[][] elements) {
        if (elements.length != 0 && elements[0].length != 0) {
            return false;
        }
        return true;

    }

    /**
     * Checks whether the given two dimensional array is valid or not. Valid means
     * that all rows have the same length and all columns have the same length.
     * 
     * @return Whether the elements are valid or not.
     */
    private boolean elementsAreValid(final int[][] elements) {
        int actualNumber = 0;
        int oldNumber = 0;
        for (int i = 0; i < elements.length; i++) {
            if (actualNumber == 0) {
                actualNumber = elements[i].length;
                oldNumber = elements[i].length;

            } else {
                actualNumber = elements[i].length;
                if (actualNumber != oldNumber) {
                    return false;

                }
            }
        }
        return true;

    }

    // ---------------------------------------------------------------------------------------------

    /**
     * Prints the elements of the matrix in the console.
     */
    public void printMatrix() {
        if (elements != null && elements.length > 0) {
            for (int i = 0; i < elements.length; i++) {
                for (int j = 0; j < elements[i].length; j++) {
                    if (elements[i][j] < 0) {
                        System.out.print(elements[i][j] + "\t");
                    } else {
                        System.out.print(" " + elements[i][j] + "\t");
                    }
                }
                System.out.println();
            }

        } else {
            System.out.println("Die Matrix enthält keine Elemente");
        }
    }

    // ---------------------------------------------------------------------------------------------
}
