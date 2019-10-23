package examples.distributed_programming.rmi.distributedSemaphor;

import java.rmi.Naming;

public class DistributedMutualExclusion {
    // ---------------------------------------------------------------------------------------------

    public static void main(String[] args) {
        try {
            RMISemaphore mutex = (RMISemaphore) Naming.lookup("rmi://" + args[0] + "/RMISemaphore");
            for (int i = 1; i <= 10; i++) {
                mutex.p();
                System.out.println("Kritischen Abschnitt betreten");

                Thread.sleep((int) (Math.random() * 1000));

                System.out.println("Kritischer Abschnitt wird verlassen");
                mutex.v();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    // ---------------------------------------------------------------------------------------------
}