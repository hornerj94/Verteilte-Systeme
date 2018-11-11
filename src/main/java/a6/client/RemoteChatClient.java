package a6.client;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * The remote class for the chat client.
 * 
 * @author julian
 *
 */
public interface RemoteChatClient extends Remote {
    // ---------------------------------------------------------------------------------------------
    
    /**
     * Gets the id of the client.
     * 
     * @return The id of the client
     * @throws RemoteException Throws an exception if an communication error occurred
     */
    int getId() throws RemoteException;
    
    /**
     * Prints the given message on the clients console.
     * 
     * @param message The message to print
     * @throws RemoteException Throws an exception if an communication error occurred
     */
    void print(final String message) throws RemoteException;
    
    // ---------------------------------------------------------------------------------------------
}
