package digipen.cet3049_cap.model;

import digipen.cet3049_cap.model.id.DeptEmpId;
import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

/**
 * JPA entity representing an employee's department assignment (dept_emp table).
 */
@Entity
@Table(name = "dept_emp")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DeptEmp {
    /** Composite id (empNo + deptNo) */
    @EmbeddedId
    private DeptEmpId deptEmpId;

    /** Assignment start date */
    @Column(name = "from_date")
//    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate fromDate;

    /** Assignment end date */
    @Column(name = "to_date")
//    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate toDate;

    /** Employee reference */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "emp_no", referencedColumnName = "emp_no",
                insertable = false, updatable = false)
    private Employees employee;

    /** Department reference */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "dept_no", referencedColumnName = "dept_no",
                insertable = false, updatable = false)
    private Departments department;

    public DeptEmp(DeptEmpId deptEmpId, LocalDate fromDate, LocalDate toDate) {
        this.deptEmpId = deptEmpId;
        this.fromDate = fromDate;
        this.toDate = toDate;
    }
}