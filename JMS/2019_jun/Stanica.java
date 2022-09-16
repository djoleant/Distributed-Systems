import javax.naming.InitialContext;
import javax.naming.NamingException;

package JMS.2019_jun;

public class Stanica {
    private String lokacija;
    private Topic tStigao;
    private Topic tUKvaru;
    private TopicConnection tc;
    private TopicSession ts;
    private TopicPublisher senderStigao;
    private TopicPublisher senderUKvaru;
    private TopicSubscriber subscriberStigao;
    private TopicSubscriber subscriberUKvaru;

    public Stanica(String lokacija) throws NamingException, JMSException {
        this.lokacija = lokacija;

        InitialContext ictx = new InitialContext();
        tStigao = (Topic) ictx.lookup("tStigao");
        tUKvaru = (Topic) ictx.lookup("tUKvaru");
        TopicConnectionFactory tcf = (TopicConnectionFactory) ictx.lookup("tcf");
        ictx.close();

        senderStigao = ts.createPublisher(tStigao);
        senderUKvaru = ts.createPublisher(tUKvaru);

        subscriberStigao = ts.createSubscriber(tStigao, "LinijaStanica = '" + this.lokacija + "'", false);
        subscriberStigao.setMessageListener(new MessageListener() {
            @Override
            public void onMessage(Message msg) {
                TextMessage txt = (TextMessage) msg;
                String linija = txt.getStringProperty("LinijaStanica");
                String trenutnaLokacija = txt.getText();

                List<String> stanice = Arrays.asList(linija.split(","));
            }
        });

        subscriberUKvaru = ts.createSubscriber("tUKvaru");
        subscriberUKvaru.setMessageListener(new MessageListener() {
            @Override
            public void onMessage(Message msg) {
                String voz = msg.getStringProperty("voz");
                System.out.println(voz + " se pokvario");
            }
        });

        tc.start();

    }

    public void Stigao(String voz, ArrayList<String> stanice, int brPutnika) throws JMSException {
        String temp = ""
        for (String stanicaString : stanice) {
            temp += stanicaString + " - ";            
        }

        TextMessage txt = ts.createTextMessage();
        txt.setText(lokacija);
        txt.setStringProperty("LinijaStanica", temp);
        txt.setIntProperty("BrojPutnika", brPutnika);


        senderStigao.send(txt);
    }

    public void UKvaru(String voz) throws JMSException {
        TextMessage msg = ts.createTextMessage();
        msg.setStringProperty("voz", voz);
        senderUKvaru.send(msg);
    }

    public void kraj() throws JMSException {
        tc.close();
        ts.close();
    }

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        Stanica st = new Stanica(s.nextLine());

        ArrayList<String> stanice = new ArrayList<String>();

        String ime = s.nextLine();
        while (ime != "quit") {
            stanice.add(ime);
            ime = s.nextLine();
        }

        // pozivi tipa stigao, kvar voza...

        s.kraj();
    }

}
