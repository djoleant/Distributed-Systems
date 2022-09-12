/* 
  ┌─────────────────────────────────────────────────────────────────────────┐
  │ Priprema ispita iz predmeta Distribuirani sistemi                       │
  │ Ispitni rok: Junski 2021                                                │
  │ 21.06.2021                                                              │
  │                                                                         │
  │ https://blanketi.sicef.info/elfak/pregled/2765                          │
  │                                                                         │
  │ Djordje Antic - Sep 12, 2022                                            │
  └─────────────────────────────────────────────────────────────────────────┘
*/

import java.rmi.Naming;
import java.rmi.RemoteException;
import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.server.UnicastRemoteObject;

public class TagClient extends UnicastRemoteObject implements TagMessageCallback {

    public TagClient(String host, String port, String service)
            throws RemoteException, MalformedURLException, NotBoundException {
        m_manager = (TagManager) Naming.lookup("rmi://" + host + ":" + port + "/" + service);
    }

    private TagManager m_manager;

    /**
     * The onTagMessage function is called when a message is sent to the tag.
     * 
     *
     * @param message Send a message to the other user
     * @param String  Pass the name of the user who sent a message to another user
     *
     * @return A void
     *
     * @docauthor Trelent
     */
    @Override
    public void onTagMessage(TagMessage message, String tag) throws RemoteException {
        System.out.println(message.m_user.getName() + ": " + message.m_msg);

    }

    /**
     * The sendMessage function sends a message to the manager.
     * 
     *
     * @param msg Pass the message to the manager
     *
     * @return A boolean value
     *
     * @docauthor Trelent
     */
    public void sendMessage(TagMessage msg) throws RemoteException {
        this.m_manager.sendMessage(msg);
    }

    /**
     * The followTag function is used to follow a tag.
     * 
     *
     * @param user   Pass the user object to the followtag function
     *               public void onfollow(user user, string tag) throws
     *               remoteexception {
     *               this
     * @param String Return the result of the followtag function
     *
     * @return A boolean
     *
     * @docauthor Trelent
     */
    public void followTag(User user, String tag) throws RemoteException {
        this.m_manager.follow(user, tag, this);
    }

    public static void main(String[] args) {
        // TODO: implementacija rada klijenta
    }
}