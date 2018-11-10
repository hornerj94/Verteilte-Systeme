package a4.client;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import a4.ClientType;
import a4.Vote;
import a4.VoteType;
import a4.server.Server;

public class Client extends Thread {
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
	 * @param clientCommunicator
	 *            The client communicator of the client
	 */
	public Client(ClientCommunicator clientCommunicator) {
		this.clientCommunicator = clientCommunicator;

	}

	// ---------------------------------------------------------------------------------------------

	public void setClientType(final ClientType clientType) {
		this.clientType = clientType;
	}

	public void setTopic(String topic) {
		this.topic = topic;
	}

	public void setVoteType(final VoteType voteType) {
		this.voteType = voteType;
	}

	// ---------------------------------------------------------------------------------------------

	@Override
	public void run() {
		String result = "";

		switch (clientType) {
		case CURRENT_STATE_CLIENT:
			Vote vote = clientCommunicator.checkVotes(topic);

			if (vote.isExisting()) {
				printVote(vote);
			} else {
				System.out.println("Zu der Umfrage " + topic + " wurde kein Eintrag gefunden");
			}
			break;

		case VOTING_CLIENT:
			result = clientCommunicator.toVote(topic, voteType);

			System.out.println(result);
			System.out.println("\n");
			break;

		case OPEN_NEW_TOPIC:
			result = clientCommunicator.addTopic(topic);

			System.out.println(result);
			System.out.println("\n");
			break;

		}
	}

	// ---------------------------------------------------------------------------------------------

	/**
	 * Prints the given vote on the console.
	 * 
	 * @param vote
	 *            The vote to print
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
