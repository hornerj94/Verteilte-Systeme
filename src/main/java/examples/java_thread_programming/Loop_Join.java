package examples.java_thread_programming;

public class Loop_Join extends Thread {
    // ---------------------------------------------------------------------------------------------

    private String myName;

    // ---------------------------------------------------------------------------------------------

    public Loop_Join(String name) {
        myName = name;
    }

    // ---------------------------------------------------------------------------------------------

    public static void main(String[] args) {
        System.out.println("\nVor der Threaderzeugung");

        Loop_Join t1 = new Loop_Join("Thread_1");
        Loop_Join t2 = new Loop_Join("Thread_2");
        Loop_Join t3 = new Loop_Join("Thread_3");
        t1.start();
        t2.start();
        t3.start();

        if (t2.isAlive()) {
            System.out.println("Thread_2 lebt noch");
        }

        System.out.println("\nWarten auf das Ende der Threads");

        try {
            t1.join();
            t2.join();
            t3.join();
        } catch (InterruptedException e) {
        }

        System.out.println("\nAlle erzeugte Threads beendet");
    }

    // =============================================================================================

    @Override
    public void run() {
        for (int i = 1; i <= 10; i++) {
            System.out.println(myName + ":  " + i);
        }
    }

    // ---------------------------------------------------------------------------------------------
}