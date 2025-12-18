package digipen.cet3049_cap.dto;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import digipen.cet3049_cap.model.Employees;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

/**
 * DTO carrying full employee information returned by APIs, including related lists
 * of department assignments, manager roles, salaries, and titles.
 */
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
    /** Employee number */
    private Long empNo;
    /** Employee birthdate */
    private LocalDate birthDate;
    /** Employee first name */
    private String firstName;
    /** Employee last name */
    private String lastName;
    /** Employee gender */
    private Employees.Gender gender;
    /** Employee hire date */
    private LocalDate hireDate;

    /** Department assignments for the employee */
    private List<DeptEmpDTO> deptEmp;
    /** Manager roles held by the employee */
    private List<DeptManagerDTO> deptManager;
    /** Salary history for the employee */
    private List<SalariesDTO> salaries;
    /** Title history for the employee */
    private List<TitlesDTO> titles;
}
