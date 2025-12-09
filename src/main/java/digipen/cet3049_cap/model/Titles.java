package digipen.cet3049_cap.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import digipen.cet3049_cap.model.id.TitlesId;
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
import lombok.ToString;

import java.time.LocalDate;

@Entity
@Table(name = "titles")
@Getter
@Setter
@ToString
@NoArgsConstructor
public class Titles {
    @EmbeddedId
    @ToString.Exclude
    private TitlesId titlesId;

    @Column(name = "to_date")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate toDate;

    @ToString.Include
    public Long getEmpNo() { return titlesId.getEmpNo(); }

    @ToString.Include
    public String getTitle() { return titlesId.getTitle(); }

    @ToString.Include
    public LocalDate getFromDate() { return titlesId.getFromDate(); }

    // relationship with Employees table
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "emp_no", referencedColumnName = "emp_no")
    private Employees employee;
}
