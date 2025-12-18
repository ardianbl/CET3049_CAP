package digipen.cet3049_cap.model;

import digipen.cet3049_cap.model.id.TitlesId;
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
 * JPA entity representing a title record for an employee (title + date range).
 */
@Entity
@Table(name = "titles")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Titles {
    /** Composite key (empNo, title, fromDate) */
    @EmbeddedId
    private TitlesId titlesId;

    /** Title end date */
    @Column(name = "to_date")
    private LocalDate toDate;

    /** Employee reference */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "emp_no", referencedColumnName = "emp_no",
                insertable = false, updatable = false)
    private Employees employee;

    public Titles(TitlesId titlesId, LocalDate toDate) {
        this.titlesId = titlesId;
        this.toDate = toDate;
    }
}