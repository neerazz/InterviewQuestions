package vehicle;

import parking.ParkingLot;
import parking.ParkingTicket;

public abstract class Vehicle {
    private String brand;
    private VehicleType vehicleType;

    public Vehicle(String brand, VehicleType vehicleType) {
        this.brand = brand;
        this.vehicleType = vehicleType;
    }

    abstract public ParkingTicket park(ParkingLot parkingLot) throws Exception;
}
