package digipen.cet3049_cap.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

/**
 * DTO representing an employee title record (title and date range).
 */
@Getter
@Setter
public class TitlesDTO {
    /** Job title */
    private String title;
    /** Title start date */
    private LocalDate fromDate;
    /** Title end date */
    private LocalDate toDate;
}
