package parking;

import vehicle.VehicleType;

public class EVParkingSlot extends ParkingSlot {
    public EVParkingSlot(String parkingNumber, VehicleType parkingType, ParkingSlotStatus parkingSlotStatus) {
        super(parkingNumber, parkingType, parkingSlotStatus);
    }

//    @Override
//    public List<ParkingSlot> addParkingSlots(int numOfSlots, VehicleType parkingType) {
//        return IntStream.rangeClosed(1, numOfSlots)
//                .mapToObj(numOfSlot ->
//                        new EVParkingSlot(numOfSlot + "-" + parkingType, parkingType, AVAILABLE))
//                .collect(Collectors.toList());
//    }
}
