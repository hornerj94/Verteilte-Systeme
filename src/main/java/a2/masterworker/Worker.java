package a2.masterworker;

import java.util.concurrent.Semaphore;

import a2.conf.ViolatedMatrixMultiplicationRules;
import a2.utils.CalculationTask;

/**
 * The class takes the role of an worker in the master-worker-paradigm.
 * 
 * @author julian
 *
 */
public class Worker extends Thread {
    // ---------------------------------------------------------------------------------------------

    /** The mutex semaphore for adding an element to the list. */
    private Semaphore semaphore;
    
    /** The master of the worker. */
    private Master master;

    /** The current calculation task of the worker. */
    private CalculationTask calculationTask;

    // ---------------------------------------------------------------------------------------------

    /**
     * Start the execution of the thread and sets the semaphore and the master
     * thread.
     * 
     * @param semaphore The event semaphore
     * @param master    The master of the worker
     */
    public Worker(final Semaphore semaphore, final Master master) {
        this.semaphore = semaphore;
        this.master = master;
    }

    // ---------------------------------------------------------------------------------------------

    public void run() throws ViolatedMatrixMultiplicationRules {
        while (!master.getCalculationTasks().isEmpty()) {
            calculationTask = master.getCalculationTasks().pop();
                calculateScalar(calculationTask.getFirstScalar(), calculationTask.getSecondScalar());
            
            try {
                semaphore.acquire();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            master.getFinishedCalculationTasks().add(calculationTask);
            semaphore.release();
        }
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
        calculationTask.setResult(calcResult);
        calculationTask.setDone(true);
    }

    // ---------------------------------------------------------------------------------------------
}
