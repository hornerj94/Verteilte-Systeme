package exercises.task7.server;

import javax.jms.JMSException;
import javax.jms.ObjectMessage;
import javax.jms.Queue;
import javax.jms.QueueConnection;
import javax.jms.QueueConnectionFactory;
import javax.jms.QueueReceiver;
import javax.jms.QueueSender;
import javax.jms.QueueSession;
import javax.jms.Session;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import exercises.task7.Request;
import exercises.task7.Response;
import exercises.task7.resources.JNDIProperties;

public class Main {
    // ---------------------------------------------------------------------------------------------

    /** The destination of the queue on the mom-server. */
    private static final String DESTINATION = "queue/aufgabe7Queue2";

    /** The id of the user. */
    private static final String USER = "guest";

    /** The password of the user. */
    private static final String PASSWORD = "guest";

    /** The factory for creating the connection. */
    private static QueueConnectionFactory factory;

    /** The queue to send the messages. */
    private static Queue queue;

    // ---------------------------------------------------------------------------------------------

    public static void main(String[] args) { 
        Context ctx = null;
        try {
            ctx = new InitialContext(JNDIProperties.getProperties());
            factory = (QueueConnectionFactory) ctx.lookup("ConnectionFactory");
            queue = (Queue) ctx.lookup(DESTINATION);
        } catch (NamingException e) {
            e.printStackTrace();
        }

        start();
    }

    // ---------------------------------------------------------------------------------------------

    public static void start() {
        QueueConnection connection = null;
        QueueSession session = null;
        QueueReceiver receiver = null;
        try {
            connection = factory.createQueueConnection(USER, PASSWORD);
            session = connection.createQueueSession(false, Session.AUTO_ACKNOWLEDGE);
            receiver = session.createReceiver(queue);
            connection.start();
            System.out.println("Server wird gestartet...");

            while (true) {
                ObjectMessage receivedObjectMessage = (ObjectMessage) receiver.receive();
                Request request = (Request) receivedObjectMessage.getObject();
                String topic = request.getTopic();

                Response response = null;
                switch (request.getClientType()) {
                case CURRENT_STATE_CLIENT:
                    if (Server.checkTopicExists(topic)) {
                        response = new Response(Server.getCurrentState(topic), "");
                    } else {
                        response = new Response(null, "Zu diesem Thema existiert keine Umfrage");
                    }
                    
                    break;
                case VOTING_CLIENT:
                    if (Server.checkTopicExists(topic)) {
                        Server.addVoteToTopic(topic, request.getVoteType());
                        response = new Response(null, "Stimme erfolgreich hinzugefügt");
                    } else {
                        response = new Response(null, "Die angegebene Umfrage existiert nicht");
                    }

                    break;
                case NEW_TOPIC_CLIENT:
                    if (Server.checkTopicExists(topic)) {
                        response = new Response(null, "Die Umfrage existiert bereits");
                    } else {
                        Server.addNewTopic(topic);
                        response = new Response(null, 
                                "Die neue Umfrage wurde erfolgreich erstellt");
                    }

                    break;
                }

                Queue tempQueue = (Queue) receivedObjectMessage.getJMSReplyTo();
                QueueSender sender = session.createSender(tempQueue);
                
                ObjectMessage responseObjectMessage = session.createObjectMessage(response);
                sender.send(responseObjectMessage);
            }
        } catch (JMSException e) {
            e.printStackTrace();
        } finally {
            try {
                connection.close();
                session.close();
                receiver.close();
            } catch (JMSException e) {
                e.printStackTrace();
            }
        }
    }

    // ---------------------------------------------------------------------------------------------
}
