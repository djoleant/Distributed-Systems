/*
  ┌─────────────────────────────────────────────────────────────────────────┐
  │ 01 DSR primer 1                                                         │
  └─────────────────────────────────────────────────────────────────────────┘
 */

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.*;

public class CalculatorServer {

  public CalculatorServer(String host, String port, String service) {
    LocateRegistry.createRegistry(Integer.parseInt(port));
    System.out.println("java RMI registry created");

    Calculator c = new CalculatorImpl(); //instancira kalkulator
    Naming.rebind("rmi://" + host + ":" + port + "/" + service, c); //registruje u rmi registry
  }

  //proveri da li main ide u okviru klase vrv da
  public static void main(String[] args) {
    String host = args[0];
    String port = args[1];
    String service = args[2];

    new CalculatorServer(host, port, service);

    System.in.read();
    System.exit(0);
  }
}
