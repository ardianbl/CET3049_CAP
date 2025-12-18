package digipen.cet3049_cap.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * DTO used to request and return promotion operations for an employee.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PromotionDTO {
    /** Employee number to promote */
    @NotNull
    private Long empNo;

    /** New job title (optional) */
    private String newTitle;

    /** New department number (optional) */
    private String newDeptNo;

    /** New salary amount (optional) */
    private BigDecimal newSalary;

    /** Promotion effective start date */
    @NotNull
    private LocalDate startDate;

    /** Whether employee should be made a department manager */
    @NotNull
    private Boolean toManager;
}