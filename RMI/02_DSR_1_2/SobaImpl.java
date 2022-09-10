/*
  ┌─────────────────────────────────────────────────────────────────────────┐
  │ 01 DSR primer 2                                                         │
  └─────────────────────────────────────────────────────────────────────────┘
*/

/**--------------------------------------------
 **  Soba je Remote zato sto se menja i ona
 **  se prenosi po referenci.

 **  Remote ima posebnu implementaciju, u ovom
 **  slucaju to je SobaImpl.java za Soba.java
 *---------------------------------------------**/

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class SobaImpl extends UnicastRemoteObject implements Soba {
  private boolean zauzeto;
  private Putnik p;
  private int brkr;
  private int cn;

  public SobaImpl(int cena, int brojkrev) throws RemoteException {
    brkr = brojkrev;
    cn = cena;
    zauzeto = false;
  }

  public int kojaCena() {
    return cn;
  }

  public int kolikoKreveta() {
    return brkr;
  }

  public boolean status() {
    return zauzeto;
  }

  /**-----------------------
   * *  MENJA STANJE OBJEKTA
   * *  BITNA CINJENICA
   *------------------------**/
  public void rezervacija(Putnik putnik) {
    zauzeto = true;
    p = putnik;
  }
}
