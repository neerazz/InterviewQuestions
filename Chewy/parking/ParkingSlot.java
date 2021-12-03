package parking;

import vehicle.Vehicle;
import vehicle.VehicleType;

import java.math.BigDecimal;
import java.time.LocalDateTime;

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

    public String getParkingNumber() {
        return parkingNumber;
    }

    public void setParkingNumber(String parkingNumber) {
        this.parkingNumber = parkingNumber;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    public VehicleType getParkingType() {
        return parkingType;
    }

    public void setParkingType(VehicleType parkingType) {
        this.parkingType = parkingType;
    }

    public LocalDateTime getTimeParked() {
        return timeParked;
    }

    public void setTimeParked(LocalDateTime timeParked) {
        this.timeParked = timeParked;
    }

    public LocalDateTime getTimeExit() {
        return timeExit;
    }

    public void setTimeExit(LocalDateTime timeExit) {
        this.timeExit = timeExit;
    }

    public BigDecimal getHourlyFee() {
        return hourlyFee;
    }

    public void setHourlyFee(BigDecimal hourlyFee) {
        this.hourlyFee = hourlyFee;
    }

    public ParkingSlotStatus getParkingSlotStatus() {
        return parkingSlotStatus;
    }

    public void setParkingSlotStatus(ParkingSlotStatus parkingSlotStatus) {
        this.parkingSlotStatus = parkingSlotStatus;
    }
}
