package a6.client;

import java.rmi.RemoteException;
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
    private final static int AMOUNT = 2;
    
    // ---------------------------------------------------------------------------------------------

    public static void main(String[] args) {
    	List<ChatClient> chatClients = new ArrayList<>();
    	
    	for (int i = 0; i < AMOUNT; i++) {
			try {
				chatClients.add(new ChatClient(i + 1, SERVER, PORT));
			} catch (RemoteException e) {
				e.printStackTrace();
			}
		}
    	
    	Thread joinThread = null;
    	for (ChatClient chatClient : chatClients) {
    		chatClient.register();
    		if (joinThread == null) {
        		joinThread = new Thread(chatClient);
        		joinThread.start();
			} 
    		new Thread(chatClient).start();
		}
    	try {
			joinThread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
    	
    }
    
    // ---------------------------------------------------------------------------------------------
}
