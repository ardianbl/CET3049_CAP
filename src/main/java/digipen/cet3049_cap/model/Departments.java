package digipen.cet3049_cap.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Id;
import jakarta.persistence.Column;
import jakarta.persistence.OneToMany;
import jakarta.persistence.FetchType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Entity
@Table(name = "departments")
@Getter
@Setter
@ToString
@NoArgsConstructor
public class Departments {
    @Id
    @Column(name = "dept_no")
    private String deptNo;

    @Column(name = "dept_name")
    private String deptName;

    @OneToMany(fetch = FetchType.LAZY)
    @ToString.Exclude
    private List<DeptEmp> deptEmp;

    @OneToMany(fetch = FetchType.LAZY)
    @ToString.Exclude
    private List<DeptManager> deptManager;
}
