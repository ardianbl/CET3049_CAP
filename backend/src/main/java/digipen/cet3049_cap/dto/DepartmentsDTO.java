package digipen.cet3049_cap.dto;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Getter;
import lombok.Setter;

/**
 * DTO representing a department with its identifier and name.
 */
@Getter
@Setter
@JsonPropertyOrder({"deptNo", "deptName"})
public class DepartmentsDTO {
    /** Department identifier */
    private String deptNo;
    /** Department name */
    private String deptName;
}
