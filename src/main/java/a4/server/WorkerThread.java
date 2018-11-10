package a4.server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class WorkerThread extends Thread {
    // ---------------------------------------------------------------------------------------------

    /** The socket that holds the connection between the client and the server. */
    private Socket socket;

    // ---------------------------------------------------------------------------------------------

    public WorkerThread(final Socket socket) {
        this.socket = socket;
    }

    // ---------------------------------------------------------------------------------------------

    @Override
    public void run() {
        try {
            ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
            ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());

            ClientType clientType = (ClientType) ois.readObject();
            String topic = (String) ois.readObject();

            switch (clientType) {
            case CURRENT_STATE_CLIENT:
                
                if (Server.checkTopicExists(topic)) {
                    oos.writeObject(Server.getCurrentState(topic));
                    
                } else {
                    Vote vote = new Vote();
                    vote.setExists(false);
                    oos.writeObject(vote);

                }
                break;
                
            case VOTING_CLIENT:
                if (Server.checkTopicExists(topic)) {
                    VoteType voteType = (VoteType) ois.readObject();
                    Server.addVoteToTopic(topic, voteType);
                    oos.writeObject("Stimme erfolgreich hinzugefügt");

                } else {
                    oos.writeObject("Die angegebene Umfrage existiert nicht");
                    
                }
                break;
                
            case OPEN_NEW_TOPIC:
                if (Server.checkTopicExists(topic)) {
                    oos.writeObject("Die Umfrage existiert bereits");
                    
                } else {
                    Server.addNewTopic(topic);
                    oos.writeObject("Die neue Umfrage wurde erfolgreich erstellt");

                }
                break;
                
            }

            oos.flush();
            oos.close();
            ois.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    // ---------------------------------------------------------------------------------------------
}
