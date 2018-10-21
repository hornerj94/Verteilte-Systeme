package a2.masterworker;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.concurrent.Semaphore;

import a2.CalculationTask;
import a2.Matrix;
import a2.conf.ViolatedMatrixMultiplicationRules;
import a2.utils.TaskSplitter;

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

    /** The amount of threads to start. */
    private int threadNumber;
    
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
        matrixC = new Matrix();
    }

    // ---------------------------------------------------------------------------------------------

    @Override
    public void run() {
        if (threadNumber > 0) {
            Semaphore listSemaphore = new Semaphore(1);
            for (int i = 0; i < threadNumber; i++) {
                workers.add(new Worker(listSemaphore, this));
            }

            multiplyMatrix();
        } else {
            System.out.println("Die Anzahl der Threads muss mindestens 1 betragen");
        }
    }

    // ---------------------------------------------------------------------------------------------

    /**
     * Gets the stack of unfinished calculation tasks.
     * 
     * @return The stack of unfinished calculation tasks.
     */
    public Stack<CalculationTask> getUnfinishedCalculationTasks() {
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
     * Sets the amount of workers.
     * 
     * @param threadNumber The amount of workers.
     */
    public void setThreadNumber(final int threadNumber) {
        this.threadNumber = threadNumber;
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
     * Calculates the result of matrix A and B and sets the result to C. 
     * Note that the multiplication of A x B will get another result then 
     * the multiplication of B x A, because the commutative law is not 
     * valid for the multiplication of matrices.
     */
    private void multiplyMatrix() {
        try {
            int rowNumberA = matrixA.getRowNumber();
            int columnNumberB = matrixB.getColumnNumber();
            int[][] arrayResult = new int[rowNumberA][columnNumberB];

            unfinishedCalculationTasks = TaskSplitter.splitTasks(matrixA, matrixB);

            for (Worker worker : workers) {
                worker.start();
            }

            matrixC.setRows(collectResults(arrayResult));

        } catch (ViolatedMatrixMultiplicationRules e) {
            System.out.println(e.getMessage());
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
