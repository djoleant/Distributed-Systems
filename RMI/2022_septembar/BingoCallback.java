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

public interface BingoCallback extends Remote {

  void currentNumber() throws RemoteException;

  void isWinner() throws RemoteException;

  void isNotWinner() throws RemoteException;

}
