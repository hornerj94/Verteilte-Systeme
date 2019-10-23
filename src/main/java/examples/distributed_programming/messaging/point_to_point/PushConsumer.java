package examples.distributed_programming.messaging.point_to_point;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.Queue;
import javax.jms.QueueConnection;
import javax.jms.QueueConnectionFactory;
import javax.jms.QueueReceiver;
import javax.jms.QueueSession;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import examples.distributed_programming.messaging.resources.JNDIProperties;

public class PushConsumer implements MessageListener {
    // ---------------------------------------------------------------------------------------------

    private static final String DESTINATION = "queue/myQueue1";
    private static final String USER = "guest";
    private static final String PASSWORD = "guest";

    // =============================================================================================

    private QueueConnection connection;
    private QueueSession session;
    private QueueReceiver receiver;

    // ---------------------------------------------------------------------------------------------

    public PushConsumer() throws NamingException, JMSException {
        // JNDI-Kontext erzeugen
        Context ctx = new InitialContext(JNDIProperties.getProperties());

        // ConnectionFactory über Namensdienst auslesen
        QueueConnectionFactory factory = (QueueConnectionFactory) ctx.lookup("ConnectionFactory");

        // Zieladresse über Namensdienst auslesen
        Queue queue = (Queue) ctx.lookup(DESTINATION);

        // Verbindung aufbauen
        connection = factory.createQueueConnection(USER, PASSWORD);

        // Session erzeugen
        session = connection.createQueueSession(false, Session.AUTO_ACKNOWLEDGE);

        // Empfänger erzeugen
        receiver = session.createReceiver(queue);

        // MessageListener setzen
        receiver.setMessageListener(this);

        // Empfang von Nachrichten starten
        connection.start();
    }

    // ---------------------------------------------------------------------------------------------

    public static void main(String[] args) throws Exception {
        long timeout = Long.parseLong(args[0]);
        PushConsumer consumer = new PushConsumer();
        Thread.sleep(timeout);
        consumer.close();
    }

    // =============================================================================================

    // Nachrichten werden im Push-Verfahren empfangen
    @Override
    public void onMessage(Message message) {
        try {
            if (message instanceof TextMessage) {
                TextMessage textMessage = (TextMessage) message;
                System.out.println(textMessage.getText());
            }
        } catch (JMSException e) {
            System.err.println(e);
        }
    }

    // Ressourcen freigeben
    public void close() throws JMSException {
        receiver.close();
        session.close();
        connection.close();
    }

    // ---------------------------------------------------------------------------------------------
}
