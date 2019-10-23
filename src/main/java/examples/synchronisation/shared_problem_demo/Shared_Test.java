package examples.synchronisation.shared_problem_demo;

import examples.synchronisation.semaphoren.Semaphore;

/* Datei Shared_Test.java                         E. Ammann   */
/* Demo-Anwendung zum Synchronisationsproblem                 */
/* Paramenter:  - Zahl von Threads                            */

public class Shared_Test {
    // ---------------------------------------------------------------------------------------------

    private final static int ANFANGSWERT = 1000;

    // ---------------------------------------------------------------------------------------------

    public static void main(String args[]) {
        int i;
        Shared shared;
        Semaphore mutex = new Semaphore(1);

        if (args.length != 1) {
            System.out.println("Syntaktisch falscher Aufruf des Programms");
            System.exit(1);
        }

        shared = new Shared(ANFANGSWERT);

        for (i = 0; i < (Integer.parseInt(args[0]) / 2); i++) {
            new Actor(mutex, shared, 1).start();
            new Actor(mutex, shared, -1).start();
        }

        try {
            Thread.sleep(20000);
        } catch (InterruptedException e) {
        }
        System.out.println("Endwert: " + shared.get_counter());
    }

    // ---------------------------------------------------------------------------------------------
}
