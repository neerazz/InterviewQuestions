import parking.ParkingLot;
import vehicle.CompactVehicle;

import java.util.List;

public class Runner {
    public static void main(String[] args) throws Exception {
        ParkingLot parkingLot = new ParkingLot(5, 5, 5);
        List<CompactVehicle> compactVehicles = List.of(
                new CompactVehicle("Honda"),
                new CompactVehicle("Mazda"),
                new CompactVehicle("BMW"),
                new CompactVehicle("Toyota"),
                new CompactVehicle("Volvo"),
                new CompactVehicle("Mercedes")
        );
        for (CompactVehicle cpV : compactVehicles) {
            System.out.println(cpV.park(parkingLot));
        }
    }
}
