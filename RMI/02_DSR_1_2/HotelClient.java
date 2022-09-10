/*
  ┌─────────────────────────────────────────────────────────────────────────┐
  │ 01 DSR primer 2                                                         │
  └─────────────────────────────────────────────────────────────────────────┘
*/

import java.io.*;
import java.net.MalformedURLException;
import java.rmi.*;

public class HotelClient {
  private HotelManager ht;

  public HotelClient(String host, String port, String server) {
    ht =
      (HotelManager) Naming.lookup("rmi://" + host + ":" + port + "/" + server);

    int brkr = -1, cena = -1, matbr = -1;
    String prezime = null;
    String ime = null;

    BufferedReader r = new BufferedReader(new InputStreamReader(System.in));

    ime = r.readLine();
    prezime = r.readLine();
    matbr = Integer.parseInt(r.readLine());
    brkr = Integer.parseInt(r.readLine());
    cena = Integer.parseInt(r.readLine());

    Putnik p = new Putnik(ime, prezime, matbr);
    Soba s = ht.nadjisobu(cena, brkr);

    if (s == null) System.out.println("Nema sobe"); else {
      s.rezervacija(p);
      Systen.out.println("Soba rezervisana");
    }
  }
}
