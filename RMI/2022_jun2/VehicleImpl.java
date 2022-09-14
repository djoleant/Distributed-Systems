/* 
  ┌─────────────────────────────────────────────────────────────────────────┐
  │ Priprema ispita iz predmeta Distribuirani sistemi                       │
  │ Ispitni rok: Jun 2 2021                                                 │
  │ 21.06.2021                                                              │
  │                                                                         │
  │ https://blanketi.sicef.info/elfak/pregled/3111                          │
  │                                                                         │
  │ Djordje Antic - Sep 14, 2022                                            │
  └─────────────────────────────────────────────────────────────────────────┘
*/

import java.rmi.server.UnicastRemoteObject;
import java.rmi.RemoteException;

public class VehicleImpl extends UnicastRemoteObject implements Vehicle {

    protected VehicleImpl(int id, String address, boolean isFree, int roundNum) throws RemoteException {
        super();
        this.id = id;
        this.address = address; //DA LI DA SE IZBACI IZ KONSTRUKTORA
        this.isFree = isFree;
        this.roundNum = roundNum;
    }

    @Override
    public int getID() throws RemoteException {
        return this.id;
    }

    //DA LI DA SE DODA setAddress();

    @Override
    public String getAddress() throws RemoteException {
        return this.address;
    }

    @Override
    public boolean isFree() throws RemoteException {
        return this.isFree;
    }

    @Override
    public int getRoundNum() throws RemoteException {
        return this.roundNum;
    }

    private int id; // DA LI STRING ako pocinje npr nulom??
    private String address;
    private boolean isFree;
    private int roundNum;

}
