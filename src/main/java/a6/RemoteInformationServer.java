package a6;

import java.rmi.Remote;
import java.rmi.RemoteException;

import a6.client.ChatClient;

/**
 * The remote class for the server.
 * 
 * @author julian
 *
 */
public interface RemoteInformationServer extends Remote {
    // ---------------------------------------------------------------------------------------------
    
    /**
     * Adds an new client to the list of the server.
     * 
     * @param chatClient The chat client to add to the server list
     * @throws RemoteException Throws an exception if an communication error occurred
     */
    void addClient(RemoteChatClient chatClient) throws RemoteException;

    /**
     * Removes an client from the list of the server.
     * 
     * @param chatClient The chat client to remove from the server list
     * @throws RemoteException Throws an exception if an communication error occurred
     */
    void removeClient(RemoteChatClient chatClient) throws RemoteException;
    
    // ---------------------------------------------------------------------------------------------
}
