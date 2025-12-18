package digipen.cet3049_cap.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

/**
 * DTO representing an employee's department assignment (dept number and date range).
 */
@Getter
@Setter
public class DeptEmpDTO {
    /** Department number */
    private String deptNo;
    /** Record start date */
    private LocalDate fromDate;
    /** Record end date */
    private LocalDate toDate;
}
