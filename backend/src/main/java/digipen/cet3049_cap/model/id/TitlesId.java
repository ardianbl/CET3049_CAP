package digipen.cet3049_cap.model.id;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;

@Embeddable
@Getter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class TitlesId implements Serializable {
    @Column(name = "emp_no", length = 11,
            nullable = false, updatable = false)
    private Long empNo;

    @Column(name = "title", length = 50,
            nullable = false, updatable = false)
    private String title;

    @Column(name = "from_date",
            nullable = false, updatable = false)
//    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate fromDate;
}