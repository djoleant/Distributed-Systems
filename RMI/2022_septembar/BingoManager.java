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

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.*;
public interface BingoManager extends Remote {
  
  // registruje tiket u sistemu i dodeljuje mu id
  // ako se pozove nakon sto je zavrseno izvlacenje vraca null
  Ticket playTicket(List<Integer> numbers, BingoCallback cb) throws RemoteException;

  // vrsi izvlacenje jednog od 90 brojeva i za svakog poziva callback
  // metodu currentNumber.
  // nakon izvucenog broja proverava da li neki od igraca imaju tiket
  // sa svim izvucenim brojevima i u tom slucaju se poziva NJIHOVA
  // callback metoda isWinner dok se kod svih preostalih poziva isNotWinner
  // moguce je imati vise dobitnika
  void drawNumber() throws RemoteException;

}
