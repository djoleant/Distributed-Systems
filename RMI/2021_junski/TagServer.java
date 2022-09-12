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
import java.net.MalformedURLException;
import java.rmi.AlreadyBoundException;
import java.rmi.NotBoundException;
import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;
import java.util.Scanner;

public class TagServer {

    public TagServer(String host, String port, String service)
            throws RemoteException, MalformedURLException, AlreadyBoundException {

        m_manager = new TagManagerImpl();
        LocateRegistry.createRegistry(Integer.parseInt(port));
        String bind_string = "rmi://" + host + ":" + port + "/" + service;
        this.m_bindstring = bind_string;
        Naming.bind(m_bindstring, m_manager);

    }

    /**
     * The shutdown function is used to unbind the service from the registry.
     * 
     *
     * @param host   Determine the hostname of the server
     * @param String Pass the name of the object to be created
     * @param String Return the hostname of the server
     *
     * @return The string &quot;done&quot;
     *
     * @docauthor Trelent
     */
    public void shutdown(String host, String port, String service)
            throws RemoteException, MalformedURLException, NotBoundException {
        String unbind_string = "rmi://" + host + ":" + port + "/" + service;
        Naming.unbind(unbind_string);
    }

    /**
     * The main function is used to start the TagServer.
     * 
     *
     * @param args Pass in the hostname, port number and service name
     *
     * @return Nothing
     *
     * @docauthor Trelent
     */
    public static void main(String[] args) {
        try {
            TagServer ts = new TagServer("localhost", "1099", "MessageService");
            Scanner s = new Scanner(System.in);
            if (s.nextLine() == "end")
                s.close();
            ts.shutdown("localost", "1099", "MessageService");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private TagManager m_manager;
    private String m_bindstring;
}
