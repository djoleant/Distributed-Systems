
public class MsgListener implements MessageListener {
    String id;

    public MsgListener() {
        id = "";
    }

    public MsgListener(String id) {
        this.id = id;
    }

    public void onMessage(Message msg) {
        TextMessage tmsg = (TextMessage) msg;
        try {
            System.out.println(id + ": " + tmsg.getText());
        } catch (JMSException jE) {
            jE.printStackTrace();
        }
    }

}
