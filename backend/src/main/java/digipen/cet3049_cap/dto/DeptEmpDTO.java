package digipen.cet3049_cap.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class DeptEmpDTO {
    private String deptNo;
    private LocalDate fromDate;
    private LocalDate toDate;
}
