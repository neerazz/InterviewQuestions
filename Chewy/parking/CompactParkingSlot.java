package parking;

import vehicle.VehicleType;

public class CompactParkingSlot extends ParkingSlot {
    public CompactParkingSlot(String parkingNumber, VehicleType parkingType, ParkingSlotStatus parkingSlotStatus) {
        super(parkingNumber, parkingType, parkingSlotStatus);
    }

//    @Override
//    public List<ParkingSlot> addParkingSlots(int numOfSlots, VehicleType parkingType) {
//        return IntStream.rangeClosed(1, numOfSlots)
//                .mapToObj(numOfSlot ->
//                        new CompactParkingSlot(numOfSlot + "-" + parkingType, parkingType, AVAILABLE))
//                .collect(Collectors.toList());
//    }
}
