package digipen.cet3049_cap.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "departments")
@Getter
@Setter
@NoArgsConstructor
public class Departments {
    @Id
    @Column(name = "dept_no")
    private String deptNo;

    @Column(name = "dept_name")
    private String deptName;

    @OneToMany(fetch = FetchType.LAZY)
    @JsonIgnore
    private List<DeptEmp> deptEmp;

    @OneToMany(fetch = FetchType.LAZY)
    @JsonIgnore
    private List<DeptManager> deptManager;
}
