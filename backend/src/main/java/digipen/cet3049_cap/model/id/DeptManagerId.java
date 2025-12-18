package digipen.cet3049_cap.model.id;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * Embeddable composite key for {@link digipen.cet3049_cap.model.DeptManager}.
 */
@Embeddable
@Getter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class DeptManagerId implements Serializable {
    @Column(name = "emp_no", length = 11,
            nullable = false, updatable = false)
    private Long empNo;

    @Column(name = "dept_no", length = 4,
            nullable = false, updatable = false)
    private String deptNo;
}