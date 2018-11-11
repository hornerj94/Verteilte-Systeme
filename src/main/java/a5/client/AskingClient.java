package a5.client;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

import a5.RemoteCircularBuffer;

public class AskingClient extends Thread {
    // ---------------------------------------------------------------------------------------------
    
    /** The type of the client. */
    private ClientType clientType;
    
    /** The remote buffer for calls. */
    private RemoteCircularBuffer circularBuffer;

    // ---------------------------------------------------------------------------------------------

    /**
     * Creates an client object with the given parameters.
     * 
     * @param circularBuffer The remote circular buffer
     */
    public AskingClient(final ClientType clientType, final String serverAddress, final int port) {
        this.clientType = clientType;
        try {
            circularBuffer = (RemoteCircularBuffer) Naming.lookup(
                    "rmi://" + serverAddress + ":" + port + "/RemoteCircularBuffer");
        } catch (MalformedURLException | RemoteException | NotBoundException e) {
            e.printStackTrace();
        }
    }

    // ---------------------------------------------------------------------------------------------

    /**
     * {@inheritDoc}
     */
    @Override
    public void run() {
        if (clientType == ClientType.BUFFER_SIZE_CLIENT) {
            System.out.println("Die Größe des Ringpuffers beträgt: " + circularBuffer.getSize());
        } else {
            System.out.println("Im Ringpuffer sind aktuell " + circularBuffer.getElementAmount() 
                    + " Elemente gespeichert");
        }
    
    }
    
    // ---------------------------------------------------------------------------------------------
}
