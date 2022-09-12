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

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class UserImpl extends UnicastRemoteObject implements User {

    protected UserImpl(int id, String name) throws RemoteException {
        super();
        this.m_id = id;
        this.m_name = name;
        this.m_callback = null;
    }

    /**
     * The getId function returns the id of the object.
     * 
     *
     *
     * @return The value of the m_id field
     *
     * @docauthor Trelent
     */
    @Override
    public int getId() throws RemoteException {
        return this.m_id;
    }

    /**
     * The getName function returns the name of the object.
     * 
     *
     *
     * @return The name of the person
     *
     * @docauthor Trelent
     */
    @Override
    public String getName() throws RemoteException {
        return this.m_name;
    }

    /**
     * The getCallback function returns the TagMessageCallback object that is
     * associated with this tag.
     * 
     *
     *
     * @return The callback object that is registered with the service
     *
     * @docauthor Trelent
     */
    @Override
    public TagMessageCallback getCallback() throws RemoteException {
        return this.m_callback;
    }

    /**
     * The setCallback function sets the callback function for this TagMessage
     * object.
     * 
     *
     * @param callback Pass the message to the activity
     *
     * @return Nothing
     *
     * @docauthor Trelent
     */
    @Override
    public void setCallback(TagMessageCallback callback) throws RemoteException {
        this.m_callback = callback;

    }

    private int m_id;
    private String m_name;
    private TagMessageCallback m_callback;

}
