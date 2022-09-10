/*
  ┌─────────────────────────────────────────────────────────────────────────┐
  │ 01 DSR primer 2                                                         │
  └─────────────────────────────────────────────────────────────────────────┘
*/

/**--------------------------------------------
 **  Soba je Remote zato sto se menja i ona
 **  se prenosi po referenci.

 **  Remote ima posebnu implementaciju, u ovom
 **  slucaju to je SobaImpl.java
 *---------------------------------------------**/

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Soba extends Remote {
  public int kojaCena() throws RemoteException;

  public int kolikoKreveta() throws RemoteException;

  public boolean status() throws RemoteException;

  /**-----------------------
   * *  MENJA STANJE OBJEKTA
   * *  BITNA CINJENICA
   *------------------------**/
  public void rezervacija(Putnik cl) throws RemoteException;
}
