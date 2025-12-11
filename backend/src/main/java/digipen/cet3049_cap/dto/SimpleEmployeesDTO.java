package digipen.cet3049_cap.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class SimpleEmployeesDTO {
    private Long empNo;
    private String firstName;
    private String lastName;
    private LocalDate hireDate;
}