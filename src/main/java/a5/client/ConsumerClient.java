package a5.client;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

import a5.RemoteCircularBuffer;

/**
 * An consument thread class.
 * 
 * @author julian
 *
 */
public class ConsumerClient extends Thread {
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
     * @param circularBuffer The remote circular buffer
     */
    public ConsumerClient(final int id, final String serverAddress, final int port) {
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
        int readElement = 0;

        for (int i = 0; i < 100; i++) {
            try {
                readElement = circularBuffer.readElement();
            } catch (RemoteException e1) {
                e1.printStackTrace();
            }
//            System.out.println("Konsument " + id + " hat ausgelesen: " + readElement);

        }
    }

    // ---------------------------------------------------------------------------------------------
}
