package digipen.cet3049_cap.model;

import digipen.cet3049_cap.model.id.SalariesId;
import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * JPA entity representing a salary record for an employee (amount + date range).
 */
@Entity
@Table(name = "salaries")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Salaries {
    /** Composite id (empNo + fromDate) */
    @EmbeddedId
    private SalariesId salariesId;

    /** Salary amount */
    @Column(name = "salary", length = 11)
    private BigDecimal salary;

    /** Salary end date */
    @Column(name = "to_date")
    private LocalDate toDate;

    /** Employee reference */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "emp_no", referencedColumnName = "emp_no",
                insertable = false, updatable = false)
    private Employees employee;

    public Salaries(SalariesId salariesId, BigDecimal salary, LocalDate toDate) {
        this.salariesId = salariesId;
        this.salary = salary;
        this.toDate = toDate;
    }
}