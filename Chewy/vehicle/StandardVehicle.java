package vehicle;

import parking.ParkingLot;
import parking.ParkingSlot;
import parking.ParkingTicket;
import parking.StandardParkingSlot;

import java.util.List;

import static vehicle.VehicleType.STANDARD;

public class StandardVehicle extends Vehicle {
    public StandardVehicle(String brand) {
        super(brand, STANDARD);
    }

    @Override
    public ParkingTicket park(ParkingLot parkingLot) throws Exception {
        List<ParkingSlot> standardParkingSlots = parkingLot.getStandardParkingSlots();
        if (parkingLot.areParkingSlotsFull(standardParkingSlots))
            throw new Exception("All parking slots for standard vehicle are full");

        StandardParkingSlot standardParkingSlot = (StandardParkingSlot) parkingLot.getAvailableParkingSlot(standardParkingSlots);
        return parkingLot.getParkingTicket(this, standardParkingSlot);
    }
}
