package digipen.cet3049_cap.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

/**
 * Lightweight DTO for listing employees (used for paginated results and lists).
 */
@Getter
@Setter
@AllArgsConstructor
public class SimpleEmployeesDTO {
    /** Employee number */
    private Long empNo;
    /** First name */
    private String firstName;
    /** Last name */
    private String lastName;
    /** Hire date */
    private LocalDate hireDate;
}