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
    private int n;

    /** The second value to calculate. */
    private int k;

    /**
     * The mutex semaphore for adding an element to pascals triangle.
     */
    private Semaphore pascalsSemaphore;

    /**
     * The thread that starts this thread and that holds the list of finished tasks.
     */
    private InitializingThread initializerThread;

    // ---------------------------------------------------------------------------------------------

    /**
     * 
     * @param n                 The first value to calculate
     * @param k                 The second value to calculate
     * @param semaphore         The mutex semaphore
     * @param initializerThread The initializier
     */
    public RecursiveThread(final int n, final int k, final Semaphore semaphore,
            final InitializingThread initializerThread) {
        this.n = n;
        this.k = k;
        this.pascalsSemaphore = semaphore;
        this.initializerThread = initializerThread;

        start();
    }

    // ---------------------------------------------------------------------------------------------

    @Override
    public void run() {
        calculateBinomialCoeffizient(n, k);
    }

    // ---------------------------------------------------------------------------------------------

    private void calculateBinomialCoeffizient(final int n, final int k) {
        try {
            if (k == 0 || n == k) {
                initializerThread.getPascalsTriangle()[n][k] = 1;
            } else {
                RecursiveThread firstRecursiveThread = 
                        new RecursiveThread(n - 1, k - 1, pascalsSemaphore, initializerThread);
                RecursiveThread secondRecursiveThread = 
                        new RecursiveThread(n - 1, k, pascalsSemaphore, initializerThread);

                firstRecursiveThread.join();
                secondRecursiveThread.join();

                initializerThread.getPascalsTriangle()[n][k] = 
                        initializerThread.getPascalsTriangle()[n - 1][k - 1]
                      + initializerThread.getPascalsTriangle()[n - 1][k];
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    // ---------------------------------------------------------------------------------------------

}
