package examples.distributed_programming.messaging.request_response;

import javax.jms.JMSException;
import javax.jms.Queue;
import javax.jms.QueueConnection;
import javax.jms.QueueConnectionFactory;
import javax.jms.QueueReceiver;
import javax.jms.QueueSender;
import javax.jms.QueueSession;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import examples.distributed_programming.messaging.resources.JNDIProperties;

public class EchoServer {
    // ---------------------------------------------------------------------------------------------

    private static final String DESTINATION = "queue/myQueue1";
    private static final String USER = "guest";
    private static final String PASSWORD = "guest";

    // =============================================================================================

    private QueueConnectionFactory factory;
    private Queue queue;

    // ---------------------------------------------------------------------------------------------

    public EchoServer() throws NamingException, JMSException {
        Context ctx = new InitialContext(JNDIProperties.getProperties());

        factory = (QueueConnectionFactory) ctx.lookup("ConnectionFactory");

        queue = (Queue) ctx.lookup(DESTINATION);

    }

    // ---------------------------------------------------------------------------------------------

    public static void main(String[] args) throws Exception {
        EchoServer server = new EchoServer();
        server.process();
    }

    // =============================================================================================

    public void process() throws JMSException {
        QueueConnection connection = factory.createQueueConnection(USER, PASSWORD);
        QueueSession session = connection.createQueueSession(false, Session.AUTO_ACKNOWLEDGE);
        QueueReceiver receiver = session.createReceiver(queue);
        connection.start();
        System.out.println("EchoServer gestartet ...");

        while (true) {
            TextMessage request = (TextMessage) receiver.receive();
            String text = request.getText();
            Queue tempQueue = (Queue) request.getJMSReplyTo();
            TextMessage response = session.createTextMessage();
            response.setText(text);
            QueueSender sender = session.createSender(tempQueue);
            sender.send(response);
        }
    }

    // ---------------------------------------------------------------------------------------------
}
