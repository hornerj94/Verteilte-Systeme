package exercises.task5;

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
     * Gets the size of the buffer.
     * 
     * @return The size of the buffer
     * @throws RemoteException Throws an exception if an communication error occurred
     */
    int getSize() throws RemoteException;

    /**
     * Gets amount of elements in the buffer.
     * 
     * @return The amount of elements in the buffer
     * @throws RemoteException Throws an exception if an communication error occurred
     */
    int getElementAmount() throws RemoteException;

    /**
     * Reads the next element out of the buffer.
     * 
     * @return The read element
     * @throws RemoteException Throws an exception if an communication error occurred
    * 
     */
    int readElement() throws RemoteException;
    
    /**
     * Writes the next element into the buffer.
     * 
     * @param newValue The value to insert
     * @throws RemoteException Throws an exception if an communication error occurred
     */
    void insertElement(final int newValue) throws RemoteException;
    
    // ---------------------------------------------------------------------------------------------
}
