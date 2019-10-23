package examples.distributed_programming.messaging.publish_subscribe_durable;

import javax.jms.JMSException;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.jms.Topic;
import javax.jms.TopicConnection;
import javax.jms.TopicConnectionFactory;
import javax.jms.TopicPublisher;
import javax.jms.TopicSession;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

public class Publisher {
    // ---------------------------------------------------------------------------------------------

    private static final String DESTINATION = "topic/myTopic2";
    private static final String USER = "guest";
    private static final String PASSWORD = "guest";

    // =============================================================================================

    private TopicConnectionFactory factory;
    private Topic topic;

    // ---------------------------------------------------------------------------------------------

    public Publisher() throws NamingException, JMSException {
        Context ctx = new InitialContext();
        factory = (TopicConnectionFactory) ctx.lookup("ConnectionFactory");
        topic = (Topic) ctx.lookup(DESTINATION);
    }

// ---------------------------------------------------------------------------------------------

    public static void main(String[] args) throws Exception {
        String text = args[0];
        Publisher pub = new Publisher();
        pub.publish(text);
    }

    // =============================================================================================

    public void publish(String text) throws JMSException {
        TopicConnection connection = factory.createTopicConnection(USER, PASSWORD);
        TopicSession session = connection.createTopicSession(false, Session.AUTO_ACKNOWLEDGE);
        TopicPublisher publisher = session.createPublisher(topic);

        TextMessage message = session.createTextMessage();
        message.setText(text);
        publisher.publish(message);

        publisher.close();
        session.close();
        connection.close();
    }

    // ---------------------------------------------------------------------------------------------
}
