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

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

import javax.annotation.processing.RoundEnvironment;

public class VehicleManagerImpl extends UnicastRemoteObject implements VehicleManager {

    protected VehicleManagerImpl(List<Vehicle> listOfVehicles) throws RemoteException {
        super();
        this.listOfVehicles = listOfVehicles;
        this.requestedAddresses = null;
        /*
         * da li je ispravna linija iznad tj. to da ne prosledjujemo
         * konstruktoru req. addr. jer one jos ne postoje jel tako
         */
    }

    @Override
    public boolean requestVehicle(String address) throws RemoteException {
        // TODO Implement everything

        if (listOfVehicles.isEmpty()) {
            System.out.println("No vehicles registered!");
            return false;
        }

        Vehicle temp_vehicle = listOfVehicles.get(0);

        // nalazimo prvo slobodno vozilo
        for (Vehicle vehicle : listOfVehicles) {
            if (vehicle.isFree()) {
                temp_vehicle = vehicle;
                break; // da li ce ovaj break napustiti ovu foreach petlju?
            }
        }
        boolean temp_free = temp_vehicle.isFree();
        int temp_roundNum = temp_vehicle.getRoundNum();

        if (!temp_free) {
            System.out.println("Free vehicle not found");
            return false;
        }

        // ispitujemo da li je neko sa manjim roundNum
        for (Vehicle vehicle : listOfVehicles) {
            if (vehicle.isFree() && vehicle.getRoundNum() < temp_roundNum) {
                temp_vehicle = vehicle;
                temp_free = vehicle.isFree();
                temp_roundNum = vehicle.getRoundNum();
            }
        }

        // TODO: dodaj proveru za ako je isti roundNum da random bira

        // KAKO CALLBACK!!???

        return true;
    }

    private List<Vehicle> listOfVehicles;
    private List<String> requestedAddresses;

}
