package digipen.cet3049_cap.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import digipen.cet3049_cap.model.id.DeptEmpId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Column;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;

@Entity
@Table(name = "dept_emp")
@Getter
@Setter
@ToString
@NoArgsConstructor
public class DeptEmp {
    @EmbeddedId
    @ToString.Exclude
    private DeptEmpId id;

    @Column(name = "from_date")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate fromDate;

    @Column(name = "to_date")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate toDate;

    @ToString.Include(name = "empNo")
    public Long getEmpNo() { return id.getEmpNo(); }

    @ToString.Include(name = "deptNo")
    public String getDeptNo() { return id.getDeptNo(); }

    // relationship with other tables
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "emp_no", referencedColumnName = "emp_no")
    @ToString.Exclude
    private Employees employee;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "dept_no", referencedColumnName = "dept_no")
    @ToString.Exclude
    private Departments department;
}
