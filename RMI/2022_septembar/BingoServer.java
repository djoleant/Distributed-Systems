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
import java.lang.invoke.TypeDescriptor;
import java.net.MalformedURLException; //da li je ovaj exc neophodan za ispit?
import java.rmi.AlreadyBoundException; //da li je ovaj exc neophodan za ispit?
import java.rmi.NotBoundException; //da li je ovaj exc neophodan za ispit?
import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;
import java.util.Scanner;

public class BingoServer {
  public BingoServer(String host, String port, String service)
      throws RemoteException, MalformedURLException, AlreadyBoundException {
    manager = new BingoManagerImpl();
    LocateRegistry.createRegistry(Integer.parseInt(port));
    String bind_string = "rmi://" + host + ":" + port + "/" + service;
    Naming.bind(bind_string, manager);
  }

  public void shutdown(String host, String port, String service)
      throws RemoteException, MalformedURLException, AlreadyBoundException, NotBoundException {
    String unbind_string = "rmi://" + host + ":" + port + "/" + service;
    Naming.unbind(unbind_string);
  }

  public static void main(String[] args) {
    try {
      // sta od argumenta unosimo ispod?
      BingoServer bs = new BingoServer("localhost", "1099", "MessageService");

      // DA LI JE BLOK ISPOD POTREBAN ZA ISPIT
      Scanner s = new Scanner(System.in);
      if (s.nextLine() == "end") {
        s.close();
        bs.shutdown("localhost", "1099", "MessageService");
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  private BingoManager manager;
}