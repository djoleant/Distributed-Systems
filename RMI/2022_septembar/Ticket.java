/*
  ┌─────────────────────────────────────────────────────────────────────────┐
  │ Priprema ispita iz predmeta Distribuirani sistemi                       │
  │ Ispitni rok: Septembarski 2022                                          │
  │ 29.08.2022                                                              │
  │                                                                         │
  │ https://blanketi.sicef.info/                                            │
  │                                                                         │
  │ Djordje Antic - Sep 13, 2022                                            │
  └─────────────────────────────────────────────────────────────────────────┘
*/

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.*;

public interface Ticket extends Remote {

  int getID() throws RemoteException;

  List<Integer> getNumbers() throws RemoteException;

  // da li je setnumbers potrebna ovde ili ne
  void setumbers(List<Integer> listOfNumbers) throws RemoteException;

  // get i set callback?
}