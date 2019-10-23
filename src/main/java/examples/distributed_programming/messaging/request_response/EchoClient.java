package examples.distributed_programming.messaging.request_response;

import javax.jms.JMSException;
import javax.jms.Queue;
import javax.jms.QueueConnection;
import javax.jms.QueueConnectionFactory;
import javax.jms.QueueReceiver;
import javax.jms.QueueSender;
import javax.jms.QueueSession;
import javax.jms.Session;
import javax.jms.TemporaryQueue;
import javax.jms.TextMessage;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import examples.distributed_programming.messaging.resources.JNDIProperties;

public class EchoClient {
    // ---------------------------------------------------------------------------------------------

    private static final String DESTINATION = "queue/myQueue1";
    private static final String USER = "guest";
    private static final String PASSWORD = "guest";

    // =============================================================================================

    private String text;
    private QueueConnectionFactory factory;
    private Queue queue;

    // ---------------------------------------------------------------------------------------------

    public EchoClient(String text) throws NamingException, JMSException {
        this.text = text;

        Context ctx = new InitialContext(JNDIProperties.getProperties());

        factory = (QueueConnectionFactory) ctx.lookup("ConnectionFactory");

        queue = (Queue) ctx.lookup(DESTINATION);

    }

    // ---------------------------------------------------------------------------------------------

    public static void main(String[] args) throws Exception {
        String text = args[0];
        EchoClient client = new EchoClient(text);
        client.process();
    }

    // =============================================================================================

    public void process() throws JMSException {
        QueueConnection connection = null;
        QueueSession session = null;
        TemporaryQueue tempQueue = null;
        QueueSender sender = null;
        QueueReceiver receiver = null;

        try {
            connection = factory.createQueueConnection(USER, PASSWORD);
            session = connection.createQueueSession(false, Session.AUTO_ACKNOWLEDGE);

            // temporäre Queue für die Antwort erzeugen
            tempQueue = session.createTemporaryQueue();

            sender = session.createSender(queue);
            receiver = session.createReceiver(tempQueue);
            connection.start();

            TextMessage request = session.createTextMessage();
            request.setText(text);
            request.setJMSReplyTo(tempQueue);
            sender.send(request);

            // auf Antwort warten
            TextMessage response = (TextMessage) receiver.receive();

            System.out.println(response.getText());
        } finally {
            sender.close();
            receiver.close();
            tempQueue.delete();
            session.close();
            connection.close();
        }
    }

    // ---------------------------------------------------------------------------------------------
}
