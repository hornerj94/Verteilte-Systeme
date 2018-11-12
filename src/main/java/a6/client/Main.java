package a6.client;

import java.rmi.RemoteException;
import java.rmi.registry.Registry;
import java.util.Scanner;

public class Main {
    // ---------------------------------------------------------------------------------------------

    /** The port of the server to send the incoming messages. */
    private final static int PORT = Registry.REGISTRY_PORT;

    /** The port of the server to send the incoming messages. */
    private final static String SERVER = "DESKTOP-Q99AK62.fh-reutlingen.de";

    /** The amount of producer and consumer to create. */
    private final static int AMOUNT = 2;
    
    /** The scanner for reading the users input. */
    private final static Scanner scanner = new Scanner(System.in);

    /** The amount of producer and consumer to create. */
    private static ChatClient CHAT_CLIENT = null;
    
    // ---------------------------------------------------------------------------------------------

    public static void main(String[] args) {
        try {
            CHAT_CLIENT = new ChatClient(1, SERVER, PORT);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        
        while (true) {
            startConsole();
            
        }        
    }
    
    // ---------------------------------------------------------------------------------------------

    private static void startConsole() {

        System.out.println("Sie haben folgende Auswahlmöglichkeiten:\n");
        System.out.println("Geben sie das Wort \"Register\" ein, " 
                + "um sich für minütliche Börsenwerte zu registrieren ");
        System.out.println("Geben sie das Wort \"Remove\" ein, " 
                + "um die registrierung rückgängig zu machen ");
        System.out.println("Geben sie das Wort \"Exit\" ein, " 
                + "um das Programm zu beenden ");
        System.out.println("\nEingabe: ");

        String input = scanner.nextLine();

        switch (input) {
        case "Register":
            CHAT_CLIENT.register();
            break;
            
        case "Remove":
            CHAT_CLIENT.remove();
            break;
            
        case "Exit":
            System.exit(0);
            
        default:
            System.out.println("Ungültige Eingabe");
            break;
        }
    }

    // ---------------------------------------------------------------------------------------------
}
