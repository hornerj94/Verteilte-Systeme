package a4.client;

import a4.Vote;
import a4.VoteType;

public class Client extends Thread {
    // ---------------------------------------------------------------------------------------------

    /** The client communicator of this client object. */
    private ClientCommunicator clientCommunicator;
     
    // ---------------------------------------------------------------------------------------------
    
    /**
     * Creates the client with the given parameters.
     * 
     * @param clientCommunicator The client communicator of the client
     */
    public Client(ClientCommunicator clientCommunicator) {
        this.clientCommunicator = clientCommunicator;
        
    }
    
    // ---------------------------------------------------------------------------------------------

    /**
     * Request to vote the given topic.
     * 
     * @param topic The topic to vote
     * @param vote The type of the vote
     */
    public void toVote(final String topic, final VoteType voteType) {
        String result = clientCommunicator.toVote(topic, voteType);
        System.out.println(result);
        System.out.println("\n");
        
    }

    /**
     * Request to add an new topic.
     * 
     * @param topic The topic to add
     */
    public void addTopic(final String topic) {
        String result = clientCommunicator.addTopic(topic);
        System.out.println(result);
        System.out.println("\n");

    }

    /**
     * Request the current state of the given topic.
     * 
     * @param topic The topic to check
     */
    public void checkVotes(final String topic) {
        Vote vote  = clientCommunicator.checkVotes(topic);
        System.out.println(vote.isExisting());
        if (vote.isExisting()) {
        	printVote(vote);	
		} else {
			System.out.println("Zu der Umfrage " + topic + " wurde kein Eintrag gefunden");
		}
        
        
    }

    // ---------------------------------------------------------------------------------------------

    /**
     * Prints the given vote on the console.
     * 
     * @param vote The vote to print
     */
    private void printVote(final Vote vote) {
        System.out.println("Aktueller Stand der Umfrage: " + vote.getTopic());
        System.out.println("Zustimmungen: " + vote.getApproval());
        System.out.println("Ablehnungen: " + vote.getRefusal());
        System.out.println("Enthaltungen: " + vote.getAbstention());
        System.out.println("\n");
    }
    
    // ---------------------------------------------------------------------------------------------
}
