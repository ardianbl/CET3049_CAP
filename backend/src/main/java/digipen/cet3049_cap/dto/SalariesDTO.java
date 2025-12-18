package digipen.cet3049_cap.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * DTO representing a salary record for an employee (amount and date range).
 */
@Getter
@Setter
public class SalariesDTO {
    /** Salary amount */
    private BigDecimal salary;
    /** Salary record start date */
    private LocalDate fromDate;
    /** Salary record end date */
    private LocalDate toDate;
}
