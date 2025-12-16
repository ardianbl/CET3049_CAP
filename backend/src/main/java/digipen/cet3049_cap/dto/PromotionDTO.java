package digipen.cet3049_cap.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class PromotionDTO {
    @NotNull
    private Long empNo;
    @NotBlank
    private String newTitle;
    @NotNull
    private String deptNo;
    @NotNull
    private LocalDate startDate;
    private LocalDate toDate = LocalDate.of(9999, 1, 1);
    @NotNull
    private boolean toManager;
}