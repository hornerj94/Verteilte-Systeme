package a2.masterworker;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import a2.utils.CalculationTask;
import a2.utils.Calculator;
import a2.utils.Matrix;

/**
 * An thread class which takes the role of the master in master-worker-paradigm.
 * 
 * @author julian
 *
 */
public class Master extends Thread {
    // ---------------------------------------------------------------------------------------------

    /** The stack of unfinished calculation tasks. */
    private Stack<CalculationTask> unfinishedCalculationTasks;

    /** The list of finished calculation tasks. */
    private List<CalculationTask> finishedCalculationTasks;

    /** The list of workers of the master. */
    private List<Worker> workers;

    /** The first matrix to calculate. */
    private Matrix matrixA;

    /** The second matrix to calculate. */
    private Matrix matrixB;

    /** The result of the calculation. */
    private Matrix matrixC;

    // ---------------------------------------------------------------------------------------------

    /**
     * Default constructor.
     */
    public Master() {
        finishedCalculationTasks = new ArrayList<>();
        unfinishedCalculationTasks = new Stack<>();
        workers = new ArrayList<>();
    }

    // ---------------------------------------------------------------------------------------------

    public void run() {
        matrixC = multiplyMatrix();
    }

    // ---------------------------------------------------------------------------------------------

    /**
     * Gets the stack of unfinished calculation tasks.
     * 
     * @return The stack of unfinished calculation tasks.
     */
    public Stack<CalculationTask> getCalculationTasks() {
        return unfinishedCalculationTasks;
    }

    /**
     * Gets the list of finished calculation tasks.
     * 
     * @return The list of finished calculation tasks.
     */
    public List<CalculationTask> getFinishedCalculationTasks() {
        return finishedCalculationTasks;
    }

    /**
     * Sets the list of the workers from the master.
     * 
     * @param workers The list of the workers
     */
    public void setWorkers(final List<Worker> workers) {
        this.workers = workers;
    }

    /**
     * Sets the first matrix to calculate.
     * 
     * @param matrixA The first matrix to calculate
     */
    public void setMatrixA(final Matrix matrixA) {
        this.matrixA = matrixA;
    }

    /**
     * Sets the second matrix to calculate.
     * 
     * @param matrixB The second matrix to calculate
     */
    public void setMatrixB(final Matrix matrixB) {
        this.matrixB = matrixB;

    }

    /**
     * Gets the result matrix.
     * 
     * @param matrixA The first matrix
     */
    public Matrix getMatrixC() {
        return matrixC;
    }

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
    public Matrix multiplyMatrix() {
        try {
            Calculator.checkMatrixMultiplicationRules(matrixA, matrixB);
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
                        unfinishedCalculationTasks.push(new CalculationTask(i, j, scalarA, scalarB));
                    }
                }
            }
            for (Worker worker : workers) {
                worker.start();
            }
            arrayResult = collectResults(arrayResult);

            resultMatrix.setRows(arrayResult);
            return resultMatrix;

        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    /**
     * Returns an int array containing an scalar from the given matrix in the given
     * row.
     * 
     * @param rowIndex The index to get the values from
     * @param matrix   The Matrix to get the scalar from
     * @return The horizontal scalar of the given column.
     */
    private int[] getHorizontalScalar(int rowIndex, Matrix matrix) {
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
    private int[] getVerticalScalar(int columnIndex, Matrix matrix) {
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

    // ---------------------------------------------------------------------------------------------

    /**
     * Collects the results of the calculation and put them in the specified column
     * and row of the returned array.
     * 
     * @return An two dimensional array that contains the result of the calculation
     */
    private int[][] collectResults(final int[][] resultArray) {
        for (Worker worker : workers) {
            try {
                worker.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        for (CalculationTask tasks : finishedCalculationTasks) {
            resultArray[tasks.getRowIndex()][tasks.getColumnIndex()] = tasks.getResult();
        }

        return resultArray;
    }

    // ---------------------------------------------------------------------------------------------
}
