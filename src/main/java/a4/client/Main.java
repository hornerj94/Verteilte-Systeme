package a4.client;

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
        for (int i = 0; i < 323; i++) {
            ClientCommunicator clientCommunicator = new ClientCommunicator();
            clientCommunicator.setCommunicationParameters(SERVER, PORT);

            Client client = new Client(clientCommunicator);
            client.setClientType(ClientType.VOTING_CLIENT);
            client.setTopic("Bananen");
            client.setVoteType(VoteType.values()[new Random().nextInt(3)]);
            client.start();

        }
        
        ClientCommunicator clientResultCommunicator = new ClientCommunicator();
        clientResultCommunicator.setCommunicationParameters(SERVER, PORT);
        Client resultClient = new Client(clientResultCommunicator);
        resultClient.setClientType(ClientType.CURRENT_STATE_CLIENT);
        resultClient.setTopic("Bananen");
        resultClient.start();
        try {
			resultClient.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
    }
    
    // ---------------------------------------------------------------------------------------------
}
