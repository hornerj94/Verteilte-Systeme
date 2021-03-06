package exercises.task5.client;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

import exercises.task5.RemoteCircularBuffer;

/**
 * Client class for online votes with variable topics.
 * 
 * @author julian
 *
 */
public class Client extends Thread {
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
    public Client(final ClientType clientType, final String serverAddress, final int port) {
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
        try {
            if (clientType == ClientType.BUFFER_SIZE_CLIENT) {
                System.out.println("Die Gr��e des Ringpuffers betr�gt: " 
                        + circularBuffer.getSize());

            } else {
                System.out.println("Im Ringpuffer sind aktuell " + circularBuffer.getElementAmount() 
                            + " Elemente gespeichert");
            }
        } catch (RemoteException e) {
            e.printStackTrace();
        }

    }

    // ---------------------------------------------------------------------------------------------
}
