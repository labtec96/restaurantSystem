package dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * Created by ch on 2020-05-15
 */
@Getter
@Setter
@NoArgsConstructor
public class RestaurantTableDto {
    private Long id;

    @NotNull
    @NotEmpty
    private String number;

    @NotNull
    @NotEmpty
    private String floor;

    @NotNull
    private int maxNumberOfPeople;
}
