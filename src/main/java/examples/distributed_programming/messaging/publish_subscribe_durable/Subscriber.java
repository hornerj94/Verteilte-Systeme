package examples.distributed_programming.messaging.publish_subscribe_durable;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.jms.Topic;
import javax.jms.TopicConnection;
import javax.jms.TopicConnectionFactory;
import javax.jms.TopicSession;
import javax.jms.TopicSubscriber;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

public class Subscriber {
    // ---------------------------------------------------------------------------------------------

    private static final String DESTINATION = "topic/myTopic2";
    private static final String USER = "guest";
    private static final String PASSWORD = "guest";

    // =============================================================================================

    private TopicConnectionFactory factory;
    private Topic topic;
    private TopicConnection connection;
    private TopicSession session;
    private TopicSubscriber subscriber;

    // ---------------------------------------------------------------------------------------------

    public Subscriber() throws NamingException, JMSException {
        Context ctx = new InitialContext();
        factory = (TopicConnectionFactory) ctx.lookup("ConnectionFactory");
        topic = (Topic) ctx.lookup(DESTINATION);
    }

    // ---------------------------------------------------------------------------------------------

    public static void main(String[] args) throws Exception {
        String option = args[0];
        String id = args[1];
        String name = args[2];

        Subscriber sub = new Subscriber();
        if (option.equals("subscribe")) {
            sub.subscribe(id, name);
        } else if (option.equals("unsubscribe")) {
            sub.unsubscribe(id, name);
        } else if (option.equals("receive")) {
            long timeout = Long.parseLong(args[3]);
            sub.receive(id, name, timeout);
        }
    }

    // =============================================================================================

    public void subscribe(String id, String name) throws JMSException {
        connection = factory.createTopicConnection(USER, PASSWORD);
        connection.setClientID(id);
        session = connection.createTopicSession(false, Session.AUTO_ACKNOWLEDGE);
        // Durable Subscriber
        subscriber = session.createDurableSubscriber(topic, name);
        subscriber.close();
        session.close();
        connection.close();
    }

    public void unsubscribe(String id, String name) throws JMSException {
        connection = factory.createTopicConnection(USER, PASSWORD);
        connection.setClientID(id);
        session = connection.createTopicSession(false, Session.AUTO_ACKNOWLEDGE);
        session.unsubscribe(name);
        session.close();
        connection.close();
    }

    // ---------------------------------------------------------------------------------------------

    public void receive(String id, String name, long timeout) throws JMSException {

        connection = factory.createTopicConnection(USER, PASSWORD);
        connection.setClientID(id);
        session = connection.createTopicSession(false, Session.AUTO_ACKNOWLEDGE);
        subscriber = session.createDurableSubscriber(topic, name);
        connection.start();

        Message message;
        while ((message = subscriber.receive(timeout)) != null) {
            if (message instanceof TextMessage) {
                TextMessage textMessage = (TextMessage) message;
                System.out.println(textMessage.getText());
            }
        }
        subscriber.close();
        session.close();
        connection.close();
    }

    // ---------------------------------------------------------------------------------------------
}
