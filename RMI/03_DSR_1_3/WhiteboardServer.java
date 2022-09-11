/*
  ┌─────────────────────────────────────────────────────────────────────────┐
  │ 03 DSR primer Whiteboard bez Callbacka                                  │
  └─────────────────────────────────────────────────────────────────────────┘
*/

import java.net.MalformedURLException;
import java.rmi.*;
import java.rmi.registry.*;

public class WhiteboardServer {
    public static void main(String args[]) {
        try {
            LocateRegistry.createRegistry(1099);
            System.out.println("Registry created");
        } catch (RemoteException e) {
            e.printStackTrace();
        }

        IWhiteboardManager mngr;
        try {
            mngr = new WhiteboardManager();

            Naming.rebind("rmi://localhost:1099/Whiteboard", mngr);
        } catch (RemoteException | MalformedURLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        System.out.println("Whiteboard server ready");
    }
}
