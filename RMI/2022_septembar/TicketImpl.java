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

import java.rmi.server.UnicastRemoteObject;
import java.security.ProtectionDomain;
import java.util.List;
import java.rmi.RemoteException;

public class TicketImpl extends UnicastRemoteObject implements Ticket {

  protected TicketImpl(int id, List<Integer> numbers) throws RemoteException {
    super();
    this.id = id;
    this.numbers = numbers;
    // callback?
  }

  @Override
  public int getID() throws RemoteException {
    return this.id;
  }

  @Override
  public List<Integer> getNumbers() throws RemoteException {
    return this.numbers;
  }

  @Override
  public void setumbers(List<Integer> listOfNumbers) throws RemoteException {
    this.numbers = listOfNumbers;
  }

  private int id;
  private List<Integer> numbers;
}
