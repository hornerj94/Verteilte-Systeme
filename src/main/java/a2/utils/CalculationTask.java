package a2.utils;

/**
 * Represents an task to calculate something.
 * 
 * @author julian
 *
 */
public class CalculationTask {
    // ---------------------------------------------------------------------------------------------

    /** The row index for the result array. */
    private int rowIndex;
    
    /** The column index for the result array. */
    private int columnIndex;
    
    /** The first scalar to calculate. */
    private int[] firstScalar;
    
    /** The second scalar to calculate. */
    private int[] secondScalar;
    
    /** The result of the calculation. */
    private int result;
    
    /** Whether the task is done or not. */
    private boolean isDone;
    
    // ---------------------------------------------------------------------------------------------

    public CalculationTask(int rowIndex, int columnIndex, final int[] firstScalar, 
            final int[] secondScalar) {
        this.rowIndex = rowIndex;
        this.columnIndex = columnIndex;
        this.firstScalar = firstScalar;
        this.secondScalar = secondScalar;
    }

    // ---------------------------------------------------------------------------------------------

    /**
     * The row index for the result array.
     * 
     * @return The row index for the result array
     */
    public int getRowIndex() {
        return rowIndex;
    }

    /**
     * The column index for the result array.
     * 
     * @return The column index for the result array
     */
    public int getColumnIndex() {
        return columnIndex;
    }
    
    /**
     * The first scalar to calculate.
     * 
     * @return The first scalar to calculate
     */
    public int[] getFirstScalar() {
        return firstScalar;
    }

    /**
     * The second scalar to calculate.
     * 
     * @return The second scalar to calculate
     */
    public int[] getSecondScalar() {
        return secondScalar;
    }

    /**
     * The result of the task.
     * 
     * @return The result of the task
     */
    public int getResult() {
        return result;
    }
         
    /**
     * Sets the result of the task.
     * 
     * @param result The result of the task
     */
    public void setResult(final int result) {
        this.result = result;
    }

    /**
     * Gets whether the task is calculated or not.
     * 
     * @return Whether the task is calculated or not
     */
    public boolean isDone() {
        return isDone;
    }

    /**
     * Sets whether the task is done or not.
     * 
     * @param result Whether the task is done or not
     */

    public void setDone(final boolean isDone) {
        this.isDone = isDone;
    }

    // ---------------------------------------------------------------------------------------------
}
