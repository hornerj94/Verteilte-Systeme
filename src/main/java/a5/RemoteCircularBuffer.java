package a5;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * The remote interface for the <Code>CircularBuffer<code> class.
 * 
 * @author julian
 *
 */
public interface RemoteCircularBuffer extends Remote {
    // ---------------------------------------------------------------------------------------------

    /**
     * Reads the next element out of the buffer.
     * 
     * @return The read element
     */
    int readElement() throws RemoteException;
    
    /**
     * Writes the next element into the buffer.
     * 
     * @param newValue The value to insert
     */
    void insertElement(final int newValue) throws RemoteException;
    
    // ---------------------------------------------------------------------------------------------
}
