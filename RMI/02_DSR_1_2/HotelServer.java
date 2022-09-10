/*
  ┌─────────────────────────────────────────────────────────────────────────┐
  │ 01 DSR primer 2                                                         │
  └─────────────────────────────────────────────────────────────────────────┘
*/

import java.io.IOException;
import java.net.MalformedURLException;
import java.rmi.*;
import java.rmi.registry.*;
import java.rmi.server.*;

public class HotelServer {

  public static void main(String args[]) {
    String host = args[0];
    String port = args[1];
    String service = args[2];

    //instanciranje remote obj
    HotelManager hotel = null;
    hotel = new HotelManagerImpl();

    LocateRegistry.createRegistry(Integer.parseInt(port));

    //registrovanje u RMI Registry
    Naming.rebind("rmi://" + host + ":" + port + "/" + service, hotel);

    System.in.read();
    System.out(0);
  }
}
