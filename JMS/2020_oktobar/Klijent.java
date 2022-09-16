import javax.naming.InitialContext;
import javax.naming.NamingException;

package JMS.2020_oktobar;

public class Klijent {
    private int ID;

    private Queue qSplit;
    private Queue qJoin;

    private QueueConnection qc;
    private QueueSession qs;

    private MessageProducer splitProducer;
    private MessageProducer joinProducer;

    public Klijent(int iD) throws NamingException, JMSException {
        this.ID = ID;

        InitialContext ic = new InitialContext();
        QueueConnectionFactory qcf = (QueueConnectionFactory) ic.lookup("qcf");
        TopicConnectionFactory tcf = (TopicConnectionFactory) ic.lookup("tcf");

        ic.close();

        qc = (QueueConnection) qcf.createQueueConnection();
        qs = (QueueSession) qc.createSession(false, Session.AUTO_ACKNOWLEDGE);

        splitProducer = qs.createProducer(qSplit);
        joinProducer = qs.createProducer(qJoin);

    }

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        Klijent k = new Klijent((int) s.nextLine().trim());
        LinkedList<Integer> niz = new LinkedList<>();

        k.start();

        while(true) {
            input = s.nextLine().trim();
            if(input == "quit") break;
            niz.add((int) input);
        
        }

        k.sortiraj(niz);
        s.nextLine();
        s.close();
        k.stop();
    }


    public void start() throws JMSException {
        qs.createConsumer(qSplit, "", true).setMessageListener()
    }

    public void stop() throws JMSException {
        qs.close();
        qc.close();
    }

    public void sortira(LinkedList<Integer> niz) throws JMSException {
        //sortiranje...
    }

}
