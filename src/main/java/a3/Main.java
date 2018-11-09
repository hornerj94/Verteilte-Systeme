package a3;

public class Main {
    // ---------------------------------------------------------------------------------------------

    /**
     * The program prints the binomial coefficient to the console. 
     * The binomial coefficient is the amount of the possible combinations if 
     * we take an given amount of elements out of the total amount of elements.
     * 
     * To calculate the binomial coefficient we need two parameters:
     * (1) The amount of total elements
     * (2) The amount of elements to calculate possible combinations
     * 
     * The first and second parameter will be set inside the args array
     * of the main method.
     * 
     * @param args The array with the necessary parameters.
     */
    public static void main(String[] args) {

        int n = 0;
        int k = 0;
        try {
            n = Integer.parseInt(args[0]);
            k = Integer.parseInt(args[1]);
        } catch (NumberFormatException e) {
            System.out.println("Einer der Parameter war keine Zahl oder hatte das falsche Format");
        } catch (IndexOutOfBoundsException e2) { 
            System.out.println("Es muss ein Parameter übergeben werden");
        }

        InitializingThread initializingThread = new InitializingThread();        
        initializingThread.setN(n);
        initializingThread.setK(k);
        
        initializingThread.start();     
        try {
            initializingThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
        int binomialCoefficient = initializingThread.getSolution();
        System.out.println("Der Binomialkoeffizient ist: " + binomialCoefficient);;

    }
    
    // ---------------------------------------------------------------------------------------------
}
