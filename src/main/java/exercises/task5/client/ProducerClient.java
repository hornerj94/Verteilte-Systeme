package exercises.task5.client;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

import exercises.task5.RemoteCircularBuffer;

/**
 * An producer thread class.
 * 
 * @author julian
 *
 */
public class ProducerClient extends Thread {
    // ---------------------------------------------------------------------------------------------

    /** The number of the consument. */
    private int id;

    /** The remote buffer for calls. */
    private RemoteCircularBuffer circularBuffer;
    
    // ---------------------------------------------------------------------------------------------

    /**
     * Creates an consument object with the given parameters.
     * 
     * @param id             The id of the consument
     * @param serverAddress  The address of the server
     * @param port           The port of the server
     */
    public ProducerClient(final int id, final String serverAddress, final int port) {
        this.id = id;
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
        for (int i = 0; i < 100; i++) {
            try {
                circularBuffer.insertElement(100 * id + i);
            } catch (RemoteException e1) {
                e1.printStackTrace();
            }
//            System.out.println("Produzent " + id + " hat eingefuegt: " + (100 * id + i));

        }
    }

    // ---------------------------------------------------------------------------------------------
}
