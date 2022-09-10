/*
  ┌─────────────────────────────────────────────────────────────────────────┐
  │ 01 DSR primer 1                                                         │
  └─────────────────────────────────────────────────────────────────────────┘
 */

import java.net.MalformedURLException;
import java.rmi.*;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

public class CalculatorClient {

  public static void main(String[] args) {
    String host = args[0];
    String port = args[1];
    String service = args[2];

    Calculator c = (Calculator) Naming.lookup(
      "rmi://" + host + ":" + port + "/" + service
    ); //proveri da i fa li jos nesto

    System.out.println(c.add(4, 5));
  }
}
