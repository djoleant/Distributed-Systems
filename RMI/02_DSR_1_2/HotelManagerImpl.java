/*
  ┌─────────────────────────────────────────────────────────────────────────┐
  │ 01 DSR primer 2                                                         │
  └─────────────────────────────────────────────────────────────────────────┘
*/

import java.rmi.RemoteException;
import java.rmi.servewr.UnicastRemoteObject;

public class HotelManagerImpl extends UnicastRemoteObject implements HotelManager {

    private Soba[] sobeniz = {
        new SobaImpl(500, 3);
        new SobaImpl(200, 4);
    };

    public HotelManagerImpl() throws RemoteException {
        super();
    }

    public Soba nadjisobu(int mc, int nb) throws RemoteException {
        int i = 0;

        while(i < sobeniz.length) && !(sobeniz[i].status()==false){
            if (i<sobeniz.length) return (Soba) this.sobeniz[i];
            else return null
        }
    }

}