package examples.synchronisation.shared_problem_demo;

import examples.synchronisation.semaphoren.Semaphore;

/* Datei Actor.java                  E.Ammann    */
/* Klasse fuer die Actor-Threads                 */

public class Actor extends Thread {
    // ---------------------------------------------------------------------------------------------

    private Shared shared;
    private int delta;
    private Semaphore semaphore;

    // ---------------------------------------------------------------------------------------------

    public Actor(Shared shared, int delta) {
        this.shared = shared;
        this.delta = delta;
    }

    public Actor(Semaphore semaphore, Shared shared, int delta) {
        this.semaphore = semaphore;
        this.shared = shared;
        this.delta = delta;
    }

    // ---------------------------------------------------------------------------------------------

    @Override
    public void run() {
        semaphore.p();
        for (int i = 0; i < 1000; i++) {
            shared.change_counter(delta);
            yield();
        }
        semaphore.v();
    }

    // ---------------------------------------------------------------------------------------------
}
