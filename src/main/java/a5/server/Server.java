package a5.server;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;

/**
 * Server class for online votes with variable topics.
 * 
 * @author julian
 *
 */
public class Server {
    // ---------------------------------------------------------------------------------------------

    /** The buffer of the server. */
    private CircularBuffer buffer;
    
    // ---------------------------------------------------------------------------------------------

    /**
     * Creates an server object with an buffer specified by the given size.
     * 
     * @param bufferSize
     */
    public Server(final int bufferSize) {
        setCircularBuffer(bufferSize);
    }
    
    // ---------------------------------------------------------------------------------------------

    /**
     * Sets the buffer size.
     * 
     * @param bufferSize
     */
    private void setCircularBuffer(final int bufferSize) {
        try {
            buffer = new CircularBuffer(bufferSize);
            Naming.rebind("rmi://localhost/RemoteCircularBuffer", buffer);

        } catch (RemoteException | MalformedURLException e) {
            e.printStackTrace();
        } 
    }
    
    // ---------------------------------------------------------------------------------------------
}
