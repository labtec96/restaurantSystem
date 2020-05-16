package dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * Created by ch on 2020-05-16
 */
@Getter
@Setter
@NoArgsConstructor
public class MealDto {

    private Long id;

    @NotNull
    @NotEmpty
    private String name;

    private String description;

    private String weight;

    @NotNull
    private int price;
}
