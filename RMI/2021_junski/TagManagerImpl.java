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

import java.rmi.*;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashMap;

public class TagManagerImpl extends UnicastRemoteObject implements TagManager {

    public TagManagerImpl() throws RemoteException {
        super();
        this.following = new HashMap<>();
    }

    /**
     * The sendMessage function sends a message to all users who follow the tags
     * that are included in the message. The function takes as input a TagMessage
     * object and sends it to each user who follows at least one of the tags in
     * that message. If there is no such user, then nothing happens.
     *
     * 
     * @param message Send the message to the client
     *
     * @return A list of users that have been notified
     *
     * @docauthor Trelent
     */
    @Override
    public void sendMessage(TagMessage message) throws RemoteException {
        HashMap<Integer, User> notify = new HashMap<>();

        for (String message_tagString : message.m_tags) {
            for (User user : this.following.get(message_tagString).values()) {
                if (!notify.containsKey(user.getId()) && user.getId() != message.m_user.getId()) {
                    notify.put(user.getId(), user);
                    user.getCallback().onTagMessage(message, message_tagString);
                }
            }
        }

    }

    /**
     * The follow function is used to add a user to the list of followers for a
     * given tag.
     * 
     *
     * @param user               Get the user object from the database
     * 
     * @param String             Store the tag name
     * @param TagMessageCallback Send the message to the user
     *
     * @return The callback that was passed in
     *
     * @docauthor Trelent
     */
    @Override
    public void follow(User user, String tag, TagMessageCallback callback) throws RemoteException {

        if (user.getCallback() == null)
            user.setCallback(callback);
        HashMap<Integer, User> followers = this.following.get(tag);

        if (followers == null)
            this.following.put(tag, followers = new HashMap<>());

        if (!followers.containsKey(user.getId()))
            followers.put(user.getId(), user);

    }

    private HashMap<String, HashMap<Integer, User>> following;

}
