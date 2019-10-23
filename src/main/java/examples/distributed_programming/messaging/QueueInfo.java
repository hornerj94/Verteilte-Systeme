package examples.distributed_programming.messaging;

import java.util.Date;
import java.util.Enumeration;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Queue;
import javax.jms.QueueBrowser;
import javax.jms.QueueConnection;
import javax.jms.QueueConnectionFactory;
import javax.jms.QueueSession;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import examples.distributed_programming.messaging.resources.JNDIProperties;

public class QueueInfo {
    // ---------------------------------------------------------------------------------------------

    private static final String DESTINATION = "queue/myQueue1";
    private static final String USER = "guest";
    private static final String PASSWORD = "guest";

    // =============================================================================================

    private QueueConnection connection;
    private QueueSession session;
    private QueueBrowser browser;

    // ---------------------------------------------------------------------------------------------

    public QueueInfo() throws NamingException, JMSException {
        Context ctx = new InitialContext(JNDIProperties.getProperties());
        QueueConnectionFactory factory = (QueueConnectionFactory) ctx.lookup("ConnectionFactory");
        Queue queue = (Queue) ctx.lookup(DESTINATION);
        connection = factory.createQueueConnection(USER, PASSWORD);
        session = connection.createQueueSession(false, Session.AUTO_ACKNOWLEDGE);

        // QueueBrowser erzeugen
        browser = session.createBrowser(queue);
        connection.start();
    }

    // ---------------------------------------------------------------------------------------------

    public static void main(String[] args) throws Exception {
        QueueInfo info = new QueueInfo();
        info.list();
        info.close();
    }

    // =============================================================================================

    public void list() throws JMSException {
        Enumeration e = browser.getEnumeration();
        Message message;
        int cnt = 0;
        while (e.hasMoreElements()) {
            cnt++;
            message = (Message) e.nextElement();
            TextMessage textmessage = (TextMessage) message;

            System.out.print(cnt + ".");
            System.out.println("\tMessage Content: " + textmessage.getText());
            System.out.println("\tMessageID: " + message.getJMSMessageID());
            System.out.println("\tTimestamp: " + new Date(message.getJMSTimestamp()));
            System.out.println("\tPriority: " + message.getJMSPriority());
            long expiration = message.getJMSExpiration();
            if (expiration == 0)
                System.out.println("\tExpiration: 0");
            else
                System.out.println("\tExpiration: " + new Date(expiration));
            System.out.println();
        }
    }

    // ---------------------------------------------------------------------------------------------

    public void close() throws JMSException {
        browser.close();
        session.close();
        connection.close();
    }

    // ---------------------------------------------------------------------------------------------
}
