package parking;

import vehicle.VehicleType;

public class StandardParkingSlot extends ParkingSlot {
    public StandardParkingSlot(String parkingNumber, VehicleType parkingType, ParkingSlotStatus parkingSlotStatus) {
        super(parkingNumber, parkingType, parkingSlotStatus);
    }

//    @Override
//    public List<ParkingSlot> addParkingSlots(int numOfSlots, VehicleType parkingType) {
//        return IntStream.rangeClosed(1, numOfSlots)
//                .mapToObj(numOfSlot ->
//                        new StandardParkingSlot(numOfSlot + "-" + parkingType, parkingType, AVAILABLE))
//                .collect(Collectors.toList());
//    }
}
