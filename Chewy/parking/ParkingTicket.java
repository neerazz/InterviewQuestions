package parking;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
@ToString
public class ParkingTicket {
    private String parkingSlotNumber;
    private LocalDateTime parkingTime;
    private String parkingType;
    private String parkingStatus;
    private BigDecimal hourlyFee;
    private String vehicleBrand;
    private String vehicleType;
}
