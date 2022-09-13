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

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.*;
public class BingoManagerImpl extends UnicastRemoteObject implements BingoManager {

  protected BingoManagerImpl() throws RemoteException {
    super();
    //TODO Auto-generated constructor stub
  }

  @Override
  public Ticket playTicket(List<Integer> numbers, BingoCallback cb) throws RemoteException {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public void drawNumber() throws RemoteException {
    // TODO Auto-generated method stub
    
  }

}
