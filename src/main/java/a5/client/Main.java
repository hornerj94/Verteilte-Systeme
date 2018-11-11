package a5.client;

import java.rmi.registry.Registry;
import java.util.ArrayList;
import java.util.List;

public class Main {
    // ---------------------------------------------------------------------------------------------

    /** The port of the server to send the incoming messages. */
    private final static int PORT = Registry.REGISTRY_PORT;

    /** The port of the server to send the incoming messages. */
    private final static String SERVER = "DESKTOP-Q99AK62.localdomain";

    /** The amount of producer and consumer to create. */
    private final static int AMOUNT = 100;
    
    // ---------------------------------------------------------------------------------------------
    
    public static void main(String[] args) {
    	List<Thread> threads = new ArrayList<>();
    	for (int i = 0; i < AMOUNT ; i++) {
    		ConsumerClient consumer = new ConsumerClient(i + 1, SERVER, PORT);
    		ProducerClient producer = new ProducerClient(i + 1, SERVER, PORT);
    		threads.add(consumer);
    		threads.add(producer);
    		
    		consumer.start();
    		producer.start();
		}
    	
    	for (Thread thread : threads) {
    		try {
				thread.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
        
    }

    // ---------------------------------------------------------------------------------------------
}
