package exercises.task4.client;

import exercises.task4.ClientType;
import exercises.task4.Vote;
import exercises.task4.VoteType;

public class ClientRequest extends Thread {
	// ---------------------------------------------------------------------------------------------

	/** The client communicator of this client object. */
	private ClientCommunicator clientCommunicator;

	/** The type of the client. */
	private ClientType clientType;

	/** The topic of the client. */
	private String topic;

	/** The vote type of the client. */
	private VoteType voteType;

	// ---------------------------------------------------------------------------------------------

    /**
     * Creates the client with the given parameters.
     * 
     * @param clientCommunicator The client communicator of the client
     * @param clientType The type of the client
     * @param topic The topic of the client
     * @param voteType The type of the vote
     */
    public ClientRequest(final ClientCommunicator clientCommunicator, final ClientType clientType, 
            final String topic, final VoteType voteType) {
        this.clientCommunicator = clientCommunicator;
        this.clientType = clientType;
        this.topic = topic;
        this.voteType = voteType;

    }

	// ---------------------------------------------------------------------------------------------

    /**
     * {@inheritDoc}
     */
    @Override
	public void run() {
		String result = "";

		switch (clientType) {
		case CURRENT_STATE_CLIENT:
			Vote vote = clientCommunicator.checkVotes(topic);

			if (vote.isExisting()) {
				printVote(vote);
			} else {
				System.out.println("Zu der Umfrage " + topic + " wurde kein Eintrag gefunden\n\n");
			}
			break;

		case VOTING_CLIENT:
			result = clientCommunicator.toVote(topic, voteType);
            System.out.println(result + "\n\n");
			break;

		case NEW_TOPIC_CLIENT:
			result = clientCommunicator.addTopic(topic);
			System.out.println(result + "\n\n");
			break;

		}
	}

	// ---------------------------------------------------------------------------------------------

	/**
	 * Prints the given vote on the console.
	 * 
	 * @param vote The vote to print
	 */
	private void printVote(final Vote vote) {
		System.out.println("Aktueller Stand der Umfrage: " + vote.getTopic() + "\n");
		System.out.println("Zustimmungen: " + vote.getApproval());
		System.out.println("Ablehnungen: " + vote.getRefusal());
		System.out.println("Enthaltungen: " + vote.getAbstention());
		System.out.println("\n");
	}

	// ---------------------------------------------------------------------------------------------
}
