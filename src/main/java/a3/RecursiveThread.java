package a3;

import java.util.concurrent.Semaphore;

/**
 * The class creates an thread object that recursively create other threads.
 * 
 * @author julian
 *
 */
public class RecursiveThread extends Thread {
    // ---------------------------------------------------------------------------------------------

    /** The first value to calculate. */
    private long n;

    /** The second value to calculate. */
    private long k;

    /** The mutex semaphore for adding an element to the list of the initializing thread. */
    private Semaphore semaphore;
    
    /** The thread that starts this thread and that holds the list of finished tasks. */
    private InitializingThread initializerThread;

    // ---------------------------------------------------------------------------------------------

    /**
     * 
     * @param n The first value to calculate
     * @param k The second value to calculate
     * @param semaphore The event semaphore
     * @param initializerThread The master of the worker
     */
    public RecursiveThread(final long n, final long k, final Semaphore semaphore, 
            final InitializingThread initializerThread) {
        this.n = n;
        this.k = k;
        this.semaphore = semaphore;
        this.initializerThread = initializerThread;

        start();
    }

    // ---------------------------------------------------------------------------------------------

    @Override
    public void run() { 
        RecursiveThread recursiveThread = null;

        try {
            semaphore.acquire();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        initializerThread.getResults().add(n - k + 1);
        k--;
        semaphore.release();

        if (k > 0) {
            recursiveThread = new RecursiveThread(n, k, semaphore, initializerThread);
        } else {
            return;
        }
        
        try {
            recursiveThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    // ---------------------------------------------------------------------------------------------
}
