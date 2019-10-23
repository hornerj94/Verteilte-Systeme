package exercises.task5.server;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Main {
    // ---------------------------------------------------------------------------------------------

    /** The size of the buffer. */
    private final static int BUFFERSIZE = 10;
    
    // ---------------------------------------------------------------------------------------------

    public static void main(String[] args) {
        try {
            LocateRegistry.createRegistry(Registry.REGISTRY_PORT);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        @SuppressWarnings("unused")
        Server server = new Server(BUFFERSIZE);
        
    }

    // ---------------------------------------------------------------------------------------------
}
