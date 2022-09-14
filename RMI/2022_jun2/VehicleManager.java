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

public interface VehicleManager extends Remote {

    boolean requestVehicle(String address) throws RemoteException;

}
