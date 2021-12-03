package parking;

import vehicle.Vehicle;
import vehicle.VehicleType;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static parking.ParkingSlotStatus.AVAILABLE;
import static parking.ParkingSlotStatus.OCCUPIED;
import static vehicle.VehicleType.*;

public class ParkingLot {
    private List<ParkingSlot> compactParkingSlots;
    private List<ParkingSlot> standardParkingSlots;
    private List<ParkingSlot> evParkingSlots;

    public ParkingLot(int numOfCompactSlot, int numOfStandardSlot, int numOfEVSlot) {
        this.compactParkingSlots = addCompactParkingSlots(numOfCompactSlot); //addParkingSlots(numOfCompactSlot, COMPACT, new BigDecimal(1));
        this.standardParkingSlots = addStandardParkingSlots(numOfStandardSlot); //addParkingSlots(numOfStandardSlot, STANDARD, new BigDecimal(2));
        this.evParkingSlots = addEvParkingSlots(numOfEVSlot); //addParkingSlots(numOfEVSlot, EV, new BigDecimal(3));
    }

    private List<ParkingSlot> addCompactParkingSlots(int numOfSlots) {
        return IntStream.rangeClosed(1, numOfSlots)
                .mapToObj(numOfSlot ->
                        new CompactParkingSlot(numOfSlot + "-" + COMPACT, COMPACT, AVAILABLE))
                .collect(Collectors.toList());
    }

    private List<ParkingSlot> addStandardParkingSlots(int numOfSlots) {
        return IntStream.rangeClosed(1, numOfSlots)
                .mapToObj(numOfSlot ->
                        new StandardParkingSlot(numOfSlot + "-" + STANDARD, STANDARD, AVAILABLE))
                .collect(Collectors.toList());
    }

    private List<ParkingSlot> addEvParkingSlots(int numOfSlots) {
        return IntStream.rangeClosed(1, numOfSlots)
                .mapToObj(numOfSlot ->
                        new EVParkingSlot(numOfSlot + "-" + EV, EV, AVAILABLE))
                .collect(Collectors.toList());
    }

//    public ParkingTicket park(Vehicle vehicle) throws Exception {
//        switch (vehicle.getVehicleType()) {
//            case COMPACT:
//                return parkCompactVehicle(vehicle);
//            case STANDARD:
//                return parkStandardVehicle(vehicle);
//            case EV:
//                return parkEvVehicle(vehicle);
//            default:
//                throw new Exception("Vehicle not allowed here");
//        }
//    }
//
//    private ParkingTicket parkCompactVehicle(Vehicle vehicle) throws Exception {
//        if (areParkingSlotsFull(compactParkingSlots) & areParkingSlotsFull(standardParkingSlots))
//            throw new Exception("All parking slots for compact vehicle are full");
//
//        if (areParkingSlotsFull(compactParkingSlots)) {
//            //park in standard slot
//            return parkStandardVehicle(vehicle);
//        } else {
//            //park in compact slot
//            CompactParkingSlot compactParkingSlot = (CompactParkingSlot) getAvailableParkingSlot(compactParkingSlots);
//            return getParkingTicket(vehicle, compactParkingSlot);
//        }
//    }
//
//    private ParkingTicket parkStandardVehicle(Vehicle vehicle) throws Exception {
//        if (areParkingSlotsFull(standardParkingSlots))
//            throw new Exception("All parking slots for standard vehicle are full");
//
//        StandardParkingSlot standardParkingSlot = (StandardParkingSlot) getAvailableParkingSlot(standardParkingSlots);
//        return getParkingTicket(vehicle, standardParkingSlot);
//    }
//
//    private ParkingTicket parkEvVehicle(Vehicle vehicle) throws Exception {
//        if (areAllParkingSlotsFull())
//            throw new Exception("All parking slots are full");
//
//        if (areParkingSlotsFull(evParkingSlots)) {
//            //park in compact or slot
//            return parkCompactVehicle(vehicle);
//        } else {
//            //park in ev slot
//            CompactParkingSlot compactParkingSlot = (CompactParkingSlot) getAvailableParkingSlot(evParkingSlots);
//            return getParkingTicket(vehicle, compactParkingSlot);
//        }
//    }

    public boolean areAllParkingSlotsFull() {
        return areParkingSlotsFull(compactParkingSlots) &&
                areParkingSlotsFull(standardParkingSlots) &&
                areParkingSlotsFull(evParkingSlots);
    }

    public boolean areParkingSlotsFull(List<ParkingSlot> parkingSlots) {
        return parkingSlots.stream().noneMatch(ps -> ps.getParkingSlotStatus() == AVAILABLE);
    }

    public ParkingSlot getAvailableParkingSlot(List<ParkingSlot> parkingSlots) {
        Optional<ParkingSlot> parkingSlot = parkingSlots.stream().filter(ps -> ps.getParkingSlotStatus() == AVAILABLE).findFirst();
        return parkingSlot.orElseThrow();
    }

    public ParkingTicket getParkingTicket(Vehicle vehicle, ParkingSlot parkingSlot) {
        parkingSlot.setVehicle(vehicle);
        parkingSlot.setTimeParked(LocalDateTime.now());
        parkingSlot.setParkingSlotStatus(OCCUPIED);
//        return ParkingTicket.builder()
//                .parkingSlotNumber(parkingSlot.getParkingNumber())
//                .parkingTime(parkingSlot.getTimeParked())
//                .parkingType(parkingSlot.getParkingType().toString())
//                .parkingStatus(parkingSlot.getParkingSlotStatus().toString())
//                .hourlyFee(getParkingHourlyFeeByVehicleType(vehicle.getVehicleType()))
//                .vehicleBrand(vehicle.getBrand())
//                .vehicleType(vehicle.getVehicleType().toString())
//                .build();
        return null;
    }

    private BigDecimal getParkingHourlyFeeByVehicleType(VehicleType vehicleType) {
        switch (vehicleType) {
            case COMPACT:
                return new BigDecimal(1);
            case STANDARD:
                return new BigDecimal(2);
            case EV:
                return new BigDecimal(3);
            default:
                return null;
        }
    }

    public List<ParkingSlot> getCompactParkingSlots() {
        return null;
    }

    public List<ParkingSlot> getStandardParkingSlots() {
        return null;
    }

    public List<ParkingSlot> getEvParkingSlots() {
        return null;
    }
}
