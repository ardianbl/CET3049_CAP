package digipen.cet3049_cap.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import digipen.cet3049_cap.model.id.SalariesId;
import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "salaries")
@Getter
@Setter
@NoArgsConstructor
public class Salaries {
    @EmbeddedId
    private SalariesId salariesId;

    @Column(name = "salary", length = 11)
    private BigDecimal salary;

    @Column(name = "to_date")
//    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate toDate;

    // relationship with Employees table
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "emp_no", referencedColumnName = "emp_no",
                insertable = false, updatable = false)
    private Employees employee;
}