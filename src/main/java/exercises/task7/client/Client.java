package exercises.task7.client;

import javax.jms.JMSException;
import javax.jms.ObjectMessage;
import javax.jms.Queue;
import javax.jms.QueueConnection;
import javax.jms.QueueConnectionFactory;
import javax.jms.QueueReceiver;
import javax.jms.QueueSender;
import javax.jms.QueueSession;
import javax.jms.Session;
import javax.jms.TemporaryQueue;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import exercises.task7.ClientType;
import exercises.task7.Request;
import exercises.task7.Response;
import exercises.task7.VoteType;
import exercises.task7.resources.JNDIProperties;

/**
 * The client class for making various requests.
 * 
 * @author julian
 *
 */
public class Client {
    // ---------------------------------------------------------------------------------------------

    /** The destination of the queue on the mom-server. */
    private static final String DESTINATION = "queue/aufgabe7Queue2";

    /** The id of the user. */
    private static final String USER = "guest";

    /** The password of the user. */
    private static final String PASSWORD = "guest";

    // =============================================================================================

    /** The type of the client. */
    private ClientType clientType;

    /** The topic of the client. */
    private String topic;

    /** The vote type of the client. */
    private VoteType voteType;

    /** The factory for creating the connection. */
    private QueueConnectionFactory factory;

    /** The queue to send the messages. */
    private Queue queue;

    // ---------------------------------------------------------------------------------------------

    /**
     * Creates the client with the given parameters.
     * 
     * @param clientType The type of the client
     * @param topic      The topic of the client
     * @param voteType   The type of the vote
     */
    public Client(final ClientType clientType, final String topic, final VoteType voteType) {
        Context ctx = null;
        try {
            ctx = new InitialContext(JNDIProperties.getProperties());
            factory = (QueueConnectionFactory) ctx.lookup("ConnectionFactory");
            queue = (Queue) ctx.lookup(DESTINATION);
        } catch (NamingException e) {
            e.printStackTrace();
        }

        this.clientType = clientType;
        this.topic = topic;
        this.voteType = voteType;
    }

    // ---------------------------------------------------------------------------------------------

    /**
     * Sends an request to the queue in the mom-server.
     */
    public Response request() {
        ObjectMessage message = null;

        QueueConnection connection = null;
        QueueSession session = null;
        TemporaryQueue tempQueue = null;
        QueueSender sender = null;
        QueueReceiver receiver = null;

        try {
            connection = factory.createQueueConnection(USER, PASSWORD);
            session = connection.createQueueSession(false, Session.AUTO_ACKNOWLEDGE);
            tempQueue = session.createTemporaryQueue();
            sender = session.createSender(queue);
            receiver = session.createReceiver(tempQueue);

            connection.start();

            Request clientRequest = new Request(topic, clientType, voteType);
            ObjectMessage request = session.createObjectMessage();
            request.setObject(clientRequest);
            request.setJMSReplyTo(tempQueue);
            sender.send(request);

            message = (ObjectMessage) receiver.receive();            
            
            return (Response) message.getObject();
        } catch (JMSException e) {
            e.printStackTrace();
        } finally {
            try {
                sender.close();
                receiver.close();
                tempQueue.delete();
                session.close();
                connection.close();
            } catch (JMSException e) {
                e.printStackTrace();
            }
        }
        
        return null;
    }

    // ---------------------------------------------------------------------------------------------
}
