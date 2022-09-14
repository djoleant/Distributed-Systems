/* 
  ┌─────────────────────────────────────────────────────────────────────────┐
  │ Priprema ispita iz predmeta Distribuirani sistemi                       │
  │ Ispitni rok: Jun 2 2021                                                 │
  │ 21.06.2021                                                              │
  │                                                                         │
  │ https://blanketi.sicef.info/elfak/pregled/3111                          │
  │                                                                         │
  │ Djordje Antic - Sep 14, 2022                                            │
  └─────────────────────────────────────────────────────────────────────────┘
*/

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Vehicle extends Remote {

    /*
     * KADA JE ID da li racunamo da moze da pocinje
     * i nulom pa da koristimo STRING a ne INT ????
     */
    int getID() throws RemoteException;

    String getAddress() throws RemoteException;

    boolean isFree() throws RemoteException;

    int getRoundNum() throws RemoteException;

}
