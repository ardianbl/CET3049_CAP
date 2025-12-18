package digipen.cet3049_cap.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

/**
 * DTO representing a department manager record (department number and date range).
 */
@Getter
@Setter
public class DeptManagerDTO {
    /** Department number */
    private String deptNo;
    /** Manager assignment start date */
    private LocalDate fromDate;
    /** Manager assignment end date */
    private LocalDate toDate;
}
