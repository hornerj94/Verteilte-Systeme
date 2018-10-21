package a2.resultatsparallelismus;

import java.util.Stack;
import java.util.concurrent.Semaphore;

import a2.conf.ViolatedMatrixMultiplicationRules;
import a2.utils.CalculationTask;

/**
 * The class takes the role of an worker in result-parallelism-paradigm.
 * 
 * @author julian
 *
 */
public class Worker extends Thread {
    // ---------------------------------------------------------------------------------------------

    /** The mutex semaphore for adding an element to the list. */
    private Semaphore semaphore;
    
    /** The master of the worker thread. */
    private Master master;
    
    /** The current calculation task of the worker. */
    private Stack<CalculationTask> unfinishedTasks;

    /** The current task the thread works on. */
    private CalculationTask currentTask;
    
    // ---------------------------------------------------------------------------------------------

    /**
     * Start the execution of the thread and sets the semaphore and the master
     * thread.
     * 
     * @param semaphore The event semaphore
     * @param unfinishedTasks The unfinishedTasks
     * @param resultMatrix The result matrix
     */
    public Worker(final Semaphore semaphore, final Master master) {
        this.semaphore = semaphore;
        this.master = master;
    }

    // ---------------------------------------------------------------------------------------------

    @Override
    public void run() {
        while (!unfinishedTasks.isEmpty()) {            
            currentTask = unfinishedTasks.pop();
            calculateScalar(currentTask.getFirstScalar(), currentTask.getSecondScalar());
            
            writeCurrentResult();
        }
    }

    // ---------------------------------------------------------------------------------------------

    /**
     * Sets the unfinished tasks of the worker thread
     * 
     * @param unfinishedTasks The unfinished tasks of the worker thread.
     */
    public void setUnfinishedTasks(final Stack<CalculationTask> unfinishedTasks) {
        this.unfinishedTasks = unfinishedTasks;
    }    
    
    // ---------------------------------------------------------------------------------------------

    /**
     * Calculates the product of the two given scalars.
     * 
     * @param firstScalar  The first scalar
     * @param secondScalar The second scalar
     * @return The result of the calculation
     */
    public void calculateScalar(int[] firstScalar, int[] secondScalar) 
            throws ViolatedMatrixMultiplicationRules {
        int calcResult = 0;

        for (int i = 0; i < firstScalar.length; i++) {
            try {
                calcResult = calcResult + (firstScalar[i] * secondScalar[i]);
            } catch (ArrayIndexOutOfBoundsException e) {
                throw new ViolatedMatrixMultiplicationRules();
            }
        }
        currentTask.setResult(calcResult);
        
    }

    // ---------------------------------------------------------------------------------------------

    private void writeCurrentResult() {
        try {
            semaphore.acquire();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        master.getFinishedCalculationTasks().add(currentTask); 

        semaphore.release();
        
    }

    // ---------------------------------------------------------------------------------------------
}
