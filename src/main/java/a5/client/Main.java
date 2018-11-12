package a5.client;

import java.rmi.registry.Registry;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    // ---------------------------------------------------------------------------------------------

    /** The port of the server to send the incoming messages. */
    private final static int PORT = Registry.REGISTRY_PORT;

    /** The port of the server to send the incoming messages. */
    private final static String SERVER = "INF-9-007-06.windows.reutlingen-university.de";
//    private final static String SERVER = "DESKTOP-Q99AK62.fh-reutlingen.de";

    /** The amount of producer and consumer to create. */
    private final static int AMOUNT = 200;
    
    /** The scanner for reading the users input. */
    private final static Scanner scanner = new Scanner(System.in);

    /** The current type of the client.  */
    private static ClientType clientType = null;

    /** Whether the client thread is terminated or not.  */
    private static boolean isTerminated = false;

    // ---------------------------------------------------------------------------------------------
    
    public static void main(String[] args) {
    	List<Thread> threads = new ArrayList<>();

    	for (int i = 0; i < AMOUNT ; i++) {
    		ConsumerClient consumer = new ConsumerClient(i + 1, SERVER, PORT);
    		ProducerClient producer = new ProducerClient(i + 1, SERVER, PORT);

    		threads.add(consumer);
    		threads.add(producer);	
		}
    	for (Thread thread : threads) {
			thread.start();
		}
    	
    	while (!isTerminated) {
    	    startConsole();
            
    	    if (clientType != null) {
                Client client = new Client(clientType, SERVER, PORT);
                client.start();
            
                try {
                    client.join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }  
    	    }
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

    private static void startConsole() {

        System.out.println("Sie haben folgende Auswahlmöglichkeiten:\n");
        System.out.println("Geben sie das Wort \"Size\" ein, " 
                + "um die Größe des Ringpuffers zu erfahren ");
        System.out.println("Geben sie das Wort \"Elements\" ein, " 
                    + "um die Anzahl der aktuell gespeicherten Elemente zu erfahren ");
        System.out.println("Geben sie das Wort \"Exit\" ein, " 
                + "um das Programm zu beenden ");
        System.out.println("\nEingabe: ");

        String input = scanner.nextLine();

        switch (input) {
        case "Size":
            clientType = ClientType.BUFFER_SIZE_CLIENT;
            break;
            
        case "Elements":
            clientType = ClientType.ELEMENT_COUNTER_CLIENT;
            break;
            
        case "Exit":
            isTerminated = true;
            break;
            
        default:
            System.out.println("Ungültige Eingabe");
            break;
        }
    }

    // ---------------------------------------------------------------------------------------------
}
