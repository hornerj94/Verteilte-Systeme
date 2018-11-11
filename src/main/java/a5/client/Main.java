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
    private final static int AMOUNT = 10;
    
    // ---------------------------------------------------------------------------------------------
    
    public static void main(String[] args) {
    	List<Thread> threads = new ArrayList<>();
    	    	
    	for (int i = 0; i < AMOUNT ; i++) {
    		ConsumerClient consumer = new ConsumerClient(i + 1, SERVER, PORT);
    		ProducerClient producer = new ProducerClient(i + 1, SERVER, PORT);
        	AskingClient clientElementCounter = new AskingClient(ClientType.ELEMENT_COUNTER_CLIENT, SERVER, PORT);
        	AskingClient clientSizeCounter = new AskingClient(ClientType.BUFFER_SIZE_CLIENT, SERVER, PORT);

    		threads.add(consumer);
    		threads.add(producer);
    		threads.add(clientSizeCounter);
    		threads.add(clientElementCounter);
		}

    	for (Thread thread : threads) {
			thread.start();
		}
    	
    	for (Thread thread : threads) {
    		try {
				thread.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
    	
    	AskingClient clientEndElementCounter = 
    			new AskingClient(ClientType.ELEMENT_COUNTER_CLIENT, SERVER, PORT);
        clientEndElementCounter.start();
        try {
			clientEndElementCounter.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
    }

    // ---------------------------------------------------------------------------------------------
}
