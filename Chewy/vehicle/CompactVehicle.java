package vehicle;

import parking.*;

import java.util.List;

import static vehicle.VehicleType.COMPACT;

public class CompactVehicle extends Vehicle {
    public CompactVehicle(String brand) {
        super(brand, COMPACT);
    }

    @Override
    public ParkingTicket park(ParkingLot parkingLot) throws Exception {
        List<ParkingSlot> compactParkingSlots = parkingLot.getCompactParkingSlots();
        List<ParkingSlot> standardParkingSlots = parkingLot.getStandardParkingSlots();
        if (parkingLot.areParkingSlotsFull(compactParkingSlots) & parkingLot.areParkingSlotsFull(standardParkingSlots))
            throw new Exception("All parking slots for compact vehicle are full");

        if (parkingLot.areParkingSlotsFull(compactParkingSlots) & !parkingLot.areParkingSlotsFull(standardParkingSlots)) {
            //park in standard slot
            StandardParkingSlot standardParkingSlot = (StandardParkingSlot) parkingLot.getAvailableParkingSlot(standardParkingSlots);
            return parkingLot.getParkingTicket(this, standardParkingSlot);
        } else {
            //park in compact slot
            CompactParkingSlot compactParkingSlot = (CompactParkingSlot) parkingLot.getAvailableParkingSlot(compactParkingSlots);
            return parkingLot.getParkingTicket(this, compactParkingSlot);
        }
    }
}
