package digipen.cet3049_cap.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import digipen.cet3049_cap.model.id.DeptEmpId;
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

@Entity
@Table(name = "dept_emp")
@Getter
@Setter
@NoArgsConstructor
public class DeptEmp {
    @EmbeddedId
    private DeptEmpId id;

    @Column(name = "from_date")
//    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate fromDate;

    @Column(name = "to_date")
//    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate toDate;

    // relationship with other tables
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "emp_no", referencedColumnName = "emp_no",
                insertable = false, updatable = false)
    private Employees employee;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "dept_no", referencedColumnName = "dept_no",
                insertable = false, updatable = false)
    private Departments department;
}