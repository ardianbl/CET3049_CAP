package digipen.cet3049_cap.dto;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import digipen.cet3049_cap.model.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@JsonPropertyOrder({
        "empNo",
        "birthDate",
        "firstName",
        "lastName",
        "gender",
        "hireDate"
})
public class EmployeesDTO {
    private Long empNo;
    private LocalDate birthDate;
    private String firstName;
    private String lastName;
    private Employees.Gender gender;
    private LocalDate hireDate;

    private List<DeptEmpDTO> deptEmp;
    private List<DeptManagerDTO> deptManager;
    private List<SalariesDTO> salaries;
    private List<TitlesDTO> titles;
}
