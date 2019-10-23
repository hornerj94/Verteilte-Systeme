package examples.distributed_programming.rmi.introExample;

import java.rmi.Naming;

public class Intro_Server {
    // ---------------------------------------------------------------------------------------------

    public static void main(String args[]) {
        try {
            CounterImpl myCounter = new CounterImpl();
            Naming.rebind("Counter", myCounter);

            System.out.println("Server started");
        } catch (Exception e) {
            System.out.println("Exception: " + e.getMessage());
        }
    }

    // ---------------------------------------------------------------------------------------------
}