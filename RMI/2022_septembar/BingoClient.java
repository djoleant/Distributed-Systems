import java.rmi.server.UnicastRemoteObject;
import java.util.List;

import javax.swing.plaf.synth.SynthSpinnerUI;

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

import java.rmi.Naming;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Random;

//DA LI JE CLIENT UVEK UnicastRemoteObject?
public class BingoClient extends UnicastRemoteObject implements BingoManager {
  public BingoClient(String host, String port, String service)
      throws RemoteException, MalformedURLException, NotBoundException {
    String lookup_String = "rmi://" + host + ":" + port + "/" + service;
    manager = (BingoManager) Naming.lookup(lookup_String);

  }

  @Override
  public Ticket playTicket(List<Integer> numbers, BingoCallback cb) throws RemoteException {

    for (int i = 0; i < 15; i++) {
      // kako unos brojeva kad mi vec prosledjujemo brojeve
      // gde se oni onda unose?
    }

    // kako da ID uvek bude jedinstven????
    int id = 0; // privremeno je ovo da ne javlja gresku
    Ticket ticket = new TicketImpl(id, numbers);

    // sta sa BingoCallback cb koji se prosledjuje ovoj metodi??

    return ticket;
  }

  @Override
  public void drawNumber() throws RemoteException {

    int random_number = (int) Math.random() * 100;
    // kako sad ovaj deo sa proverom sa igracima
    // i sa callbackom
  }

  private BingoManager manager;

}
