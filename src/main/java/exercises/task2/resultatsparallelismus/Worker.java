package exercises.task2.resultatsparallelismus;

import java.util.Stack;
import java.util.concurrent.Semaphore;

import exercises.task2.utils.CalculationTask;
import exercises.task2.utils.Calculator;

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
    
    /** The distributor of the work. */
    private WorkDistributorThread master;
    
    /** The list of calculation task the worker has to finish. */
    private Stack<CalculationTask> unfinishedTasks;

    /** The current task the thread works on. */
    private CalculationTask currentTask;
    
    // ---------------------------------------------------------------------------------------------

    /**
     * Sets the semaphore and the master thread.
     * 
     * @param semaphore The event semaphore
     * @param unfinishedTasks The unfinishedTasks
     * @param resultMatrix The result matrix
     */
    public Worker(final Semaphore semaphore, final WorkDistributorThread master) {
        this.semaphore = semaphore;
        this.master = master;
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
     * {@inheritDoc}
     */
    @Override
    public void run() {
        while (!unfinishedTasks.isEmpty()) {            
            currentTask = unfinishedTasks.pop();
            Calculator.calculateScalar(currentTask.getFirstScalar(), currentTask.getSecondScalar());
            
            writeCurrentResult();
        }
    }

    // ---------------------------------------------------------------------------------------------

    /**
     * Writes the current finished task to the finished calculation task list.
     */
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
