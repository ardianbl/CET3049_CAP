package digipen.cet3049_cap.model.id;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Getter;

import java.time.LocalDate;

@Embeddable
@Getter
public class TitlesId {
    @Column(name = "emp_no", length = 11)
    private Long empNo;

    @Column(name = "title", length = 50)
    private String title;

    @Column(name = "from_date")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate fromDate;
}
