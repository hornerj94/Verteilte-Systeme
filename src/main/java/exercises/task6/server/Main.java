package exercises.task6.server;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Main {
    // ---------------------------------------------------------------------------------------------

    public static void main(String[] args) {
        try {
            LocateRegistry.createRegistry(Registry.REGISTRY_PORT);
        } catch (RemoteException e) {
            e.printStackTrace();
        }

        try {
            InformationServer server = new InformationServer();
            server.startInformationSending();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        
    }

    // ---------------------------------------------------------------------------------------------
}
