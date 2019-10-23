package exercises.task4.client;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import exercises.task4.ClientType;
import exercises.task4.Vote;
import exercises.task4.VoteType;

public class ClientCommunicator {
	// ---------------------------------------------------------------------------------------------

	/** The server address. */
	private String server;
	
	/** The port of the server. */
	private int port;

	/** The output stream of the client. */
	private ObjectOutputStream oos;

	/** The input stream of the client. */
	private ObjectInputStream ois;

	// ---------------------------------------------------------------------------------------------

	/**
	 * Creates an client communicator with the given parameters.
	 * 
	 * @param server The server address
	 * @param port The port to request on the server
	 */
	public ClientCommunicator(final String server, final int port) {
        this.server = server;
        this.port = port;
    }

	// ---------------------------------------------------------------------------------------------

	public void closeConnection() {
		try {
			oos.flush();
			oos.close();
			ois.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// ---------------------------------------------------------------------------------------------

	public String toVote(final String topic, final VoteType voteType) {
		String reply = "";
		try { 
			Socket socket = new Socket(server, port);

			oos = new ObjectOutputStream(socket.getOutputStream());
			oos.writeObject(ClientType.VOTING_CLIENT);
			oos.writeObject(topic);
			oos.writeObject(voteType);

			ois = new ObjectInputStream(socket.getInputStream());
			reply = (String) ois.readObject();

			socket.close();
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		return reply;

	}

	public String addTopic(final String topic) {
		String reply = "";

		try {
			Socket socket = new Socket(server, port);
			
			oos = new ObjectOutputStream(socket.getOutputStream());
			oos.writeObject(ClientType.NEW_TOPIC_CLIENT);
			oos.writeObject(topic);

			ois = new ObjectInputStream(socket.getInputStream());
			reply = (String) ois.readObject();

			socket.close();
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		return reply;

	}

	public Vote checkVotes(final String topic) {
		Vote vote = null;

		try {
			Socket socket = new Socket(server, port);

			oos = new ObjectOutputStream(socket.getOutputStream());
			oos.writeObject(ClientType.CURRENT_STATE_CLIENT);
			oos.writeObject(topic);

			ois = new ObjectInputStream(socket.getInputStream());				
			vote = (Vote) ois.readObject();

			socket.close();
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		return vote;

	}

	// ---------------------------------------------------------------------------------------------
}
