package a6.server;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import a6.client.ChatClient;
import a6.client.RemoteChatClient;

public class InformationServer extends UnicastRemoteObject implements RemoteInformationServer {
    // ---------------------------------------------------------------------------------------------

    /** The serial version uid of the chat client. */
    private static final long serialVersionUID = -213185636953358702L;

    // =============================================================================================

    /** The server. */
    private List<RemoteChatClient> remoteChatClients;

    /** The random generator of the server. */
    private Random randomGenerator;

    // ---------------------------------------------------------------------------------------------

    /**
     * Default constructor
     * 
     * @throws RemoteException Throws an exception if an communication error
     *                         occurred
     */
    public InformationServer() throws RemoteException {
        super();

        randomGenerator = new Random();
        remoteChatClients = new ArrayList<>();

        try {
            Naming.rebind("rmi://localhost/RemoteInformationServer", this);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

    }

    // ---------------------------------------------------------------------------------------------

    /**
     * Starts to send the information to the registered clients.
     */
    public void startInformationSending() {
        try {

            int randomValue;
            while (true) {
                randomValue = randomGenerator.nextInt(8400);
                for (RemoteChatClient remoteChatClient : remoteChatClients) {
                    remoteChatClient.print("Der aktuelle Wert beträgt: " + randomValue);
                }
                Thread.sleep(60000);

            }
        } catch (RemoteException | InterruptedException e) {
            e.printStackTrace();
        }

    }

    // ---------------------------------------------------------------------------------------------

    /**
     * {@inheritDoc}
     */
    @Override
    public synchronized void addClient(final ChatClient chatClient) throws RemoteException {
        if (!checkClientExists(chatClient)) {
            remoteChatClients.add(chatClient);

        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public synchronized void removeClient(ChatClient chatClient) throws RemoteException {
        remoteChatClients.remove(chatClient);

    }

    // ---------------------------------------------------------------------------------------------

    /**
     * Checks whether the client already is in the list or not.
     * 
     * @param chatClient The chat client to check
     * @return Whether the client already is in the list or not
     */
    private boolean checkClientExists(final ChatClient chatClient) {
        try {
            int clientId = chatClient.getId();
            int remoteId = 0;
            for (RemoteChatClient remoteChatClient : remoteChatClients) {
                remoteId = remoteChatClient.getId();

                if (clientId == remoteId) {
                    return true;

                }
            }
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        return false;

    }

    // ---------------------------------------------------------------------------------------------
}
