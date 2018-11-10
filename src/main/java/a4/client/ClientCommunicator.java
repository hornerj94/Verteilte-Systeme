package a4.client;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class ClientCommunicator {
    // ---------------------------------------------------------------------------------------------

    /** The port of the server to send the incoming messages. */
    private final static int PORT = 7825;

    /** The socket of the server that waits for new incoming messages. */
    private static Socket socket;

    // ---------------------------------------------------------------------------------------------

    public void setCommunicationParameters(final String server, final int port) {
        try {
            socket = new Socket(server, port);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    
    // ---------------------------------------------------------------------------------------------

    public String toVote(final String topic, final VoteType voteType) {
        String reply = "";
        
        try {
            ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
            oos.writeObject(ClientType.VOTING_CLIENT);
            oos.writeObject(topic);
            oos.writeObject(voteType);
            
            ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
            reply = (String) ois.readObject();
            
            oos.flush();
            oos.close();
            ois.close();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return reply;
        
    }

    public String addTopic(final String topic) {
        String reply = "";
        
        try {
            ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
            oos.writeObject(ClientType.OPEN_NEW_TOPIC);
            oos.writeObject(topic);
            
            ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
            reply = (String) ois.readObject();
            
            oos.flush();
            oos.close();
            ois.close();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return reply;

    }

    public Vote checkVotes(final String topic) {
        Vote vote = null;
        
        try {
            ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
            oos.writeObject(ClientType.CURRENT_STATE_CLIENT);
            oos.writeObject(topic);
            
            ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
            vote = (Vote) ois.readObject();
            
            oos.flush();
            oos.close();
            ois.close();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return vote;
        
    }

    // ---------------------------------------------------------------------------------------------
}
