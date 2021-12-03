package vehicle;

import parking.EVParkingSlot;
import parking.ParkingLot;
import parking.ParkingSlot;
import parking.ParkingTicket;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static vehicle.VehicleType.EV;

public class ElectronicVehicle extends Vehicle {
    public ElectronicVehicle(String brand) {
        super(brand, EV);
    }

    @Override
    public ParkingTicket park(ParkingLot parkingLot) throws Exception {
        List<ParkingSlot> compactParkingSlots = parkingLot.getCompactParkingSlots();
        List<ParkingSlot> standardParkingSlots = parkingLot.getStandardParkingSlots();
        List<ParkingSlot> evParkingSlots = parkingLot.getEvParkingSlots();

        if (parkingLot.areAllParkingSlotsFull())
            throw new Exception("All parking slots are full");

        if (parkingLot.areParkingSlotsFull(evParkingSlots)) {
            //park in compact or standard slot
            List<ParkingSlot> compactOrStandardParkingSlot = Stream.concat(compactParkingSlots.stream(),
                    standardParkingSlots.stream()).collect(Collectors.toList());
            ParkingSlot parkingSlot = parkingLot.getAvailableParkingSlot(compactOrStandardParkingSlot);
            return parkingLot.getParkingTicket(this, parkingSlot);
        } else {
            //park in ev slot
            EVParkingSlot evParkingSlot = (EVParkingSlot) parkingLot.getAvailableParkingSlot(evParkingSlots);
            return parkingLot.getParkingTicket(this, evParkingSlot);
        }
    }
}
