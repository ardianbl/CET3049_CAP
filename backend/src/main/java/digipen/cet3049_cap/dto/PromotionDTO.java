package digipen.cet3049_cap.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class PromotionDTO {
    private Long empNo;
    private String newTitle;
    private String deptNo;
    private LocalDate startDate;
    private boolean isManager;
}