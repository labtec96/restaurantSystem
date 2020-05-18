package dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

/**
 * Created by ch on 2020-05-17
 */
@Getter
@Setter
@NoArgsConstructor
public class ReservationDto {

    private Long id;

    @NotNull
    @NotEmpty
    private String date;

    @NotNull
    @NotEmpty
    private String startHour;

    @NotNull
    @NotEmpty
    private String endHour;

    @NotNull
    private int persons;
}
