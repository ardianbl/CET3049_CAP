package digipen.cet3049_cap.model.id;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Getter;

import java.io.Serializable;

@Embeddable
@Getter
public class DeptManagerId implements Serializable {
    @Column(name = "emp_no", length = 11)
    private Long empNo;

    @Column(name = "dept_no", length = 4)
    private String deptNo;
}
