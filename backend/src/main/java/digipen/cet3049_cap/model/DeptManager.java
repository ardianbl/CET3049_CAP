package digipen.cet3049_cap.model;

import digipen.cet3049_cap.model.id.DeptManagerId;
import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

/**
 * JPA entity representing a department manager assignment (employee manages a dept).
 */
@Entity
@Table(name = "dept_manager")
@Getter
@Setter
@NoArgsConstructor
public class DeptManager {
    /** Composite id (empNo + deptNo) */
    @EmbeddedId
    private DeptManagerId deptManagerId;

    /** Manager assignment start date */
    @Column(name = "from_date")
//    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate fromDate;

    /** Manager assignment end date */
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

    public DeptManager(DeptManagerId deptManagerId, LocalDate fromDate, LocalDate toDate) {
        this.deptManagerId = deptManagerId;
        this.fromDate = fromDate;
        this.toDate = toDate;
    }
}