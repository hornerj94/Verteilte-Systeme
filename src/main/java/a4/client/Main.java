package a4.client;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import a4.ClientType;
import a4.VoteType;

public class Main {
    // ---------------------------------------------------------------------------------------------

    /** The port of the server to send the incoming messages. */
    private final static int PORT = 7825;

    /** The port of the server to send the incoming messages. */
    private final static String SERVER = "DESKTOP-Q99AK62.fh-reutlingen.de";

    // ---------------------------------------------------------------------------------------------

    public static void main(String[] args) {
        List<Client> clients = new ArrayList<>();
        String topic = "Bananen";
        
        // Creates client threads that vote with an random vote type for the topic.
        for (int i = 0; i < 323; i++) {
            ClientCommunicator clientCommunicator = new ClientCommunicator(SERVER, PORT);

            VoteType randomVoteType = VoteType.values()[new Random().nextInt(3)];
            Client client = new Client(clientCommunicator, ClientType.VOTING_CLIENT, topic, 
                    randomVoteType);
            clients.add(client);
            client.start();
            
        }
        
        // Wait till the threads are done.
        for (Client client : clients) {
            try {
                client.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        
        // Get the results for the topic.
        ClientCommunicator clientResultCommunicator = new ClientCommunicator(SERVER, PORT);
        Client resultClient = new Client(clientResultCommunicator, ClientType.VOTING_CLIENT, topic);
        resultClient.start();
        try {
			resultClient.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
    }
    
    // ---------------------------------------------------------------------------------------------
}
