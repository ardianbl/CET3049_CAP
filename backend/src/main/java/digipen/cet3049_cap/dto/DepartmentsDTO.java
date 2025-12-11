package digipen.cet3049_cap.dto;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonPropertyOrder({
        "deptNo",
        "deptName"
})
public class DepartmentsDTO {
    private String deptNo;
    private String deptName;
}
