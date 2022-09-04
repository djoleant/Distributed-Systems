/*
  ┌─────────────────────────────────────────────────────────────────────────┐
  │ 07 DSR primer 11                                                        │
  └─────────────────────────────────────────────────────────────────────────┘
 */

import javax.naming.*;

public class Producer {
    static Context ictx = null;

    public static void main(String[] args) throws Exception {
        ictx = new InitialContext();
        ConnectionFactory cf = (ConnectionFactory) ictx
                .lookup("jms/__defaultConnectionFactory");
        Queue queue = (Queue) ictx.lookup("queue");
        Topic topic = (Topic) ictx.lookup("topic");
        ictx.close();

        try (Connection cnx = cf.createConnection()) {
            Session sess = cnx.createSession(false, Session.AUTO_ACKNOWLEDGE);
            MessageProducer prod = sess.createProducer(null);
            TextMessage msg = sess.createTextMessage();
            int i;
            for (i = 0; i < 10; i++) {
                msg.setText("Test number " + i);
                prod.send(queue, msg);
                prod.send(topic, msg);
            }
        }
    }
}
