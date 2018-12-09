package a6.client;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import a6.RemoteChatClient;
import a6.RemoteInformationServer;

/**
 * The chat client class.
 * 
 * @author julian
 *
 */
public class ChatClient extends UnicastRemoteObject implements RemoteChatClient {
    // ---------------------------------------------------------------------------------------------
    
    /** The serial version uid of the chat client. */
    private static final long serialVersionUID = -213185636958858702L;

    // =============================================================================================

    /** The id of the chat client. */
    private int id;
    
    /** The server. */
    private RemoteInformationServer server;
    
    // ---------------------------------------------------------------------------------------------
    
    /**
     * Creates an chat client object with the given id.
     * 
     * @param id The id of the chat client
     * @throws RemoteException Throws an exception if an communication error occurred
     */
    public ChatClient(final int id, final String serverAddress, final int port) 
            throws RemoteException {
        this.id = id;
        try {
            server = (RemoteInformationServer) Naming.lookup(
                    "rmi://" + serverAddress + ":" + port + "/RemoteInformationServer");
        } catch (MalformedURLException | RemoteException | NotBoundException e) {
            e.printStackTrace();
        }

    }

    // ---------------------------------------------------------------------------------------------

    /**
     * The client registers itself to the information server.
     */
    public void register() {
        try {
            server.addClient(this);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    /**
     * The client removes itself from the information server.
     */
    public void remove() {
        try {
            server.removeClient(this);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    // ---------------------------------------------------------------------------------------------

    /**
     * {@inheritDoc}
     */
    @Override
    public int getId() throws RemoteException {
        return id;
        
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public void print(final String message) throws RemoteException {
        System.out.println(message);
        
    }
    
    // ---------------------------------------------------------------------------------------------
}
