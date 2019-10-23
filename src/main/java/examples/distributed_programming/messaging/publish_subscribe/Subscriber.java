package examples.distributed_programming.messaging.publish_subscribe;

import javax.jms.JMSException;
import javax.jms.MapMessage;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.Session;
import javax.jms.Topic;
import javax.jms.TopicConnection;
import javax.jms.TopicConnectionFactory;
import javax.jms.TopicSession;
import javax.jms.TopicSubscriber;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import examples.distributed_programming.messaging.resources.JNDIProperties;

public class Subscriber implements MessageListener {
    // ---------------------------------------------------------------------------------------------

    private static final String DESTINATION = "topic/myTopic1";
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
        Context ctx = new InitialContext(JNDIProperties.getProperties());
        factory = (TopicConnectionFactory) ctx.lookup("ConnectionFactory");
        topic = (Topic) ctx.lookup(DESTINATION);
    }

    // ---------------------------------------------------------------------------------------------

    public static void main(String[] args) throws Exception {
        long time = Long.parseLong(args[0]);
        Subscriber sub = new Subscriber();
        sub.subscribe();
        Thread.sleep(time);
        sub.close();
    }

    // =============================================================================================

    public void subscribe() throws JMSException {
        connection = factory.createTopicConnection(USER, PASSWORD);
        session = connection.createTopicSession(false, Session.AUTO_ACKNOWLEDGE);
        subscriber = session.createSubscriber(topic);
        subscriber.setMessageListener(this);
        connection.start();
    }

    public void close() throws JMSException {
        subscriber.close();
        session.close();
        connection.close();
    }

    // ---------------------------------------------------------------------------------------------

    @Override
    public void onMessage(Message message) {
        try {
            if (message instanceof MapMessage) {
                MapMessage mapMessage = (MapMessage) message;
                System.out.println(mapMessage.getString("Time"));
                System.out.println(mapMessage.getDouble("Value"));
                System.out.println();
            }
        } catch (JMSException e) {
            System.err.println(e);
        }
    }

    // ---------------------------------------------------------------------------------------------
}
