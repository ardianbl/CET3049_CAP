package digipen.cet3049_cap.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
public class SalariesDTO {
    private BigDecimal salary;
    private LocalDate fromDate;
    private LocalDate toDate;
}
