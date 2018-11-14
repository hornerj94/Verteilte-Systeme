package a2.masterworker;

import java.util.concurrent.Semaphore;

import a2.utils.CalculationTask;
import a2.utils.Calculator;

/**
 * The class takes the role of an worker in the master-worker-paradigm.
 * 
 * @author julian
 *
 */
public class Worker extends Thread {
    // ---------------------------------------------------------------------------------------------

    /** The mutex semaphore for adding an element to the list of the master. */
    private Semaphore semaphore;
    
    /** The master of the worker. */
    private Master master;

    /** The current calculation task of the worker. */
    private CalculationTask calculationTask;

    // ---------------------------------------------------------------------------------------------

    /**
     * @param semaphore The event semaphore
     * @param master    The master of the worker
     */
    public Worker(final Semaphore semaphore, final Master master) {
        this.semaphore = semaphore;
        this.master = master;
    }

    // ---------------------------------------------------------------------------------------------

    @Override
    public void run() {
        while (!master.getUnfinishedCalculationTasks().isEmpty()) {
            calculationTask = master.getUnfinishedCalculationTasks().pop();
            Calculator.calculateScalar(calculationTask.getFirstScalar(), 
                    calculationTask.getSecondScalar());
            
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
}
