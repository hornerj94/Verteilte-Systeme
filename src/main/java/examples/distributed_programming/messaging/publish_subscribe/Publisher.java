package examples.distributed_programming.messaging.publish_subscribe;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.jms.JMSException;
import javax.jms.MapMessage;
import javax.jms.Session;
import javax.jms.Topic;
import javax.jms.TopicConnection;
import javax.jms.TopicConnectionFactory;
import javax.jms.TopicPublisher;
import javax.jms.TopicSession;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import examples.distributed_programming.messaging.resources.JNDIProperties;

public class Publisher {
    // ---------------------------------------------------------------------------------------------

    private static final String DESTINATION = "topic/myTopic1";
    private static final String USER = "guest";
    private static final String PASSWORD = "guest";

    // =============================================================================================

    private TopicConnectionFactory factory;
    private Topic topic;

    // ---------------------------------------------------------------------------------------------

    public Publisher() throws NamingException, JMSException {
        Context ctx = new InitialContext(JNDIProperties.getProperties());
        factory = (TopicConnectionFactory) ctx.lookup("ConnectionFactory");
        topic = (Topic) ctx.lookup(DESTINATION);
    }

    // ---------------------------------------------------------------------------------------------

    public static void main(String[] args) throws Exception {
        Publisher pub = new Publisher();
        pub.process();
    }

    // =============================================================================================

    public void process() throws JMSException {
        TopicConnection connection = factory.createTopicConnection(USER, PASSWORD);
        TopicSession session = connection.createTopicSession(false, Session.AUTO_ACKNOWLEDGE);
        TopicPublisher publisher = session.createPublisher(topic);

        SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss");
        while (true) {
            String time = formatter.format(new Date());
            double value = Math.random() * 100;
            MapMessage message = session.createMapMessage();
            message.setString("Time", time);
            message.setDouble("Value", value);

            publisher.publish(message);
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
            }
        }
    }

    // ---------------------------------------------------------------------------------------------
}
