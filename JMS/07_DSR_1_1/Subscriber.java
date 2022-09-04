/*
  ┌─────────────────────────────────────────────────────────────────────────┐
  │ 07 DSR primer 11                                                        │
  └─────────────────────────────────────────────────────────────────────────┘
 */

import javax.jms.*;
import javax.naming.*;

public class Subscriber {
    static Context ictx = null;

    public static void main(String[] args) throws Exception {
        ictx = new InitialContext();
        Queue queue = (Queue) ictx.lookup("queue");
        Topic topic = (Topic) ictx.lookup("topic");
        ConnectionFactory cf = (ConnectionFactory) ictx.lookup("jms/__defaultConnectionFactory");
        ictx.close();
        Connection cnx = cf.createConnection();
        Session sess = cnx.createSession(false, Session.AUTO_ACKNOWLEDGE);
        MessageConsumer recv = sess.createConsumer(queue);
        MessageConsumer subs = sess.createConsumer(topic);

        recv.setMessageListener(new MsgListener("Queue"));
        subs.setMessageListener(new MsgListener("Topic"));
        cnx.start();
        System.in.read();
        cnx.close();
    }
}