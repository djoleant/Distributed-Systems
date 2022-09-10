/*
  ┌─────────────────────────────────────────────────────────────────────────┐
  │ 01 DSR primer 2                                                         │
  └─────────────────────────────────────────────────────────────────────────┘
*/

/**--------------------------------------------
 **  Putnik je Serializable znaci on se kopira
 **  po vrednosti, za razliku od Remote objekata,
 **  ciji se prenos vrsi po referenci

 **  => Serializable je jer samo nosi neku info
 **  Serializable nema posebno implementaciju
 *---------------------------------------------**/

import java.io.Serializable;

public class Putnik implements Serializable {
  public String ime;
  public String prezime;
  public int jmbg;

  public Putnik(String ime, String prezime, int jmbg) {
    this.ime = ime;
    this.prezime = prezime;
    this.jmbg = jmbg;
  }
}
