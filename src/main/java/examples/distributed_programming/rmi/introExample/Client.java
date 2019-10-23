package examples.distributed_programming.rmi.introExample;

import java.rmi.Naming;

public class Client {
    // ---------------------------------------------------------------------------------------------

    public static void main(String args[]) {
        if (args.length != 2) {
            System.out.println("Syntax: " + "<Server> <Number>");
            return;
        }
        
        try {
            Counter myCounter = (Counter) Naming.lookup("rmi://" + args[0] + "/Counter");

            myCounter.reset();

            int count = new Integer(args[1]).intValue();

            int result = 0;
            for (int i = 0; i < count; i++) {
                result = myCounter.increment();
            }

            System.out.println("Last counter value: " + result);
        } catch (Exception e) {
            System.out.println("Ausnahme: " + e);
        }
    }

    // ---------------------------------------------------------------------------------------------
}