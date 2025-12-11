package digipen.cet3049_cap.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class TitlesDTO {
    private String title;
    private LocalDate fromDate;
    private LocalDate toDate;
}
