package digipen.cet3049_cap.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PromotionDTO {
    @NotNull
    private Long empNo;

    private String newTitle;

    private String newDeptNo;

    private BigDecimal newSalary;

    @NotNull
    private LocalDate startDate;

    @NotNull
    private Boolean toManager;
}