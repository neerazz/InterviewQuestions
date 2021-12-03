package parking;

import lombok.Data;
import lombok.NoArgsConstructor;
import vehicle.Vehicle;
import vehicle.VehicleType;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public abstract class ParkingSlot {
    private String parkingNumber;
    private Vehicle vehicle;
    private VehicleType parkingType;
    private LocalDateTime timeParked;
    private LocalDateTime timeExit;
    private BigDecimal hourlyFee;
    private ParkingSlotStatus parkingSlotStatus;

    public ParkingSlot(String parkingNumber, VehicleType parkingType, ParkingSlotStatus parkingSlotStatus) {
        this.parkingNumber = parkingNumber;
        this.parkingType = parkingType;
        this.parkingSlotStatus = parkingSlotStatus;
    }

    public ParkingSlot(String parkingNumber, VehicleType parkingType, BigDecimal hourlyFee, ParkingSlotStatus parkingSlotStatus) {
        this.parkingNumber = parkingNumber;
        this.parkingType = parkingType;
        this.hourlyFee = hourlyFee;
        this.parkingSlotStatus = parkingSlotStatus;
    }

//    abstract public List<ParkingSlot> addParkingSlots(int numOfSlots, VehicleType parkingType);
}
