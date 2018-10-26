package a3;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Semaphore;

import conf.NumberNotSupportedException;

/**
 * An thread class that initialize the calculation of the binomial coefficient.
 * 
 * @author julian
 *
 */
public class InitializingThread extends Thread {
    // ---------------------------------------------------------------------------------------------

    /** The list of finished calculation tasks. */
    private List<Long> results;

    /** The initial worker. */
    private RecursiveThread firstRecursiveThread;

    /** The amount of total elements n. */
    private long n;

    /** The amount to take out of n. */
    private long k;
    
    /** The number of possible combinations. */
    private long solution;

    // ---------------------------------------------------------------------------------------------

    /**
     * Default constructor.
     */
    public InitializingThread() {
        results = new ArrayList<>();

    }

    // ---------------------------------------------------------------------------------------------

    /**
     * Gets the list of finished calculation tasks.
     * 
     * @return The list of finished calculation tasks.
     */
    public List<Long> getResults() {
        return results;
    }

    /**
     * Gets the total amount of the calculation.
     * 
     * @return The total amount of the calculation.
     */
    public long getN() {
        return n;
    }

    /**
     * Sets the total amount of the calculation.
     *
     * @param n The total amount of the calculation
     */
    public void setN(final long n) {
        if (n < 0) {
            System.out.println("n darf nicht kleiner als 0 sein");
        } else {
            this.n = n;
        }
    }

    /**
     * Gets the amount to calculate the possible combinations k.
     * 
     * @return The amount to calculate the possible combinations k.
     */
    public long getK() {
        return k;
    }

    /**
     * Sets the amount to calculate the possible combinations k.
     *
     * @param k The amount to calculate the possible combinations k.
     */
    public void setK(final long k) {
        if (k < 0) {
            System.out.println("k darf nicht kleiner als 0 sein");
        } else {
            this.k = k;
        }
    }

    /**
     * Gets the number of possible combinations.
     * 
     * @return The number of possible combinations.
     */
    public long getSolution() {
        return solution;
    }

    // ---------------------------------------------------------------------------------------------

    @Override
    public void run() {
        calculateBinomialCoefficient();
    }

    // ---------------------------------------------------------------------------------------------

    /**
     * Calculates the binomial coefficient and sets the solution of the calculation.
     */
    private void calculateBinomialCoefficient() {
        Semaphore listSemaphore = new Semaphore(1);
                
        if (n < k) {
            System.out.println("Der Parameter n muss größer sein als der Parameter k");
        } else if (k == 0) {
            solution = 1;
        } else {
            firstRecursiveThread = new RecursiveThread(n, k, listSemaphore, this);

            try {
                firstRecursiveThread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            solution = multiplyResults() / calculateFactorial(k);

        }
    }

    /**
     * Collects the results of the workers multiplies them and return the result.
     * 
     * @return The result of the calculation
     */
    private long multiplyResults() {
        long workerResult = 0;
        for (Long value : results) {
            if (workerResult != 0) {
                workerResult = workerResult * value;
            } else {
                workerResult = value;
            }
        }
        return workerResult;
    
    }

    // ---------------------------------------------------------------------------------------------

    /**
     * Calculates the factorial number.
     * 
     * @param number The number to calculate the factorial
     * @return The result of the calculation
     */
    private long calculateFactorial(final long number) throws NumberNotSupportedException {
        if (number == 0) {
            return 1;
        } else if (number > 0) {
            return number * calculateFactorial(number - 1);
        } else {
            throw new NumberNotSupportedException();
        }
    }

    // ---------------------------------------------------------------------------------------------
}
