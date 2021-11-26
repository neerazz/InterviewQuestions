package vehicle;

import lombok.AllArgsConstructor;
import lombok.Data;
import parking.ParkingLot;
import parking.ParkingTicket;

@Data
@AllArgsConstructor
public abstract class Vehicle {
    private String brand;
    private VehicleType vehicleType;

    abstract public ParkingTicket park(ParkingLot parkingLot) throws Exception;
}
