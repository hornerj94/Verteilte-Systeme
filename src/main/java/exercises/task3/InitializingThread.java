package exercises.task3;

/**
 * An thread class that initialize the calculation of the binomial coefficient.
 * 
 * @author julian
 *
 */
public class InitializingThread extends Thread {
    // ---------------------------------------------------------------------------------------------

    /** Pascals triangle as two dimensional array, the first array represents the rows 
     * of the triangle and the second array represents the columns. */
    private int[][] pascalsTriangle;

    /** The amount of total elements n. */
    private int n;

    /** The amount to take out of n. */
    private int k;
    
    /** The number of possible combinations. */
    private int solution;
    
    // ---------------------------------------------------------------------------------------------

    /**
     * Gets pascals triangle as two dimensional array.
     * 
     * @return Pascals triangle as two dimensional array.
     */
    public int[][] getPascalsTriangle() {
        return pascalsTriangle;
    }

    /**
     * Gets the total amount of the calculation.
     * 
     * @return The total amount of the calculation.
     */
    public int getN() {
        return n;
    }

    /**
     * Sets the total amount of the calculation.
     *
     * @param n The total amount of the calculation
     */
    public void setN(final int n) {
        if (n < 0) {
            System.out.println("n darf nicht kleiner als 0 sein");
        } else if (n > 21) {
            System.out.println("n darf nicht größer sein als 21");
        } else {
            this.n = n;
        }
    }

    /**
     * Gets the amount to calculate the possible combinations k.
     * 
     * @return The amount to calculate the possible combinations k.
     */
    public int getK() {
        return k;
    }

    /**
     * Sets the amount to calculate the possible combinations k.
     *
     * @param k The amount to calculate the possible combinations k.
     */
    public void setK(final int k) {
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
    public int getSolution() {
        return solution;
    }

    // ---------------------------------------------------------------------------------------------

    /**
     * {@inheritDoc}
     */
    @Override
    public void run() {
        if (k > n) {
            solution = 0;
        } else if (k == n) {
            solution = 1;
        } else if (k == 1) {
            solution = n;
        } else {
            pascalsTriangle = new int[n + 1][k + 1];
            RecursiveThread recursiveThread = new RecursiveThread(n, k, this);
    
            try {
                recursiveThread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            solution = pascalsTriangle[n - 1][k - 1] + pascalsTriangle[n - 1][k];
            
        }
    }

    // ---------------------------------------------------------------------------------------------
}
