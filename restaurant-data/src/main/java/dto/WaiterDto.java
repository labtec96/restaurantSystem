package dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * Created by ch on 2020-05-19
 */
@Getter
@Setter
@NoArgsConstructor
public class WaiterDto {

    private Long id;
    @NotNull
    @NotEmpty
    private String firstName;

    @NotNull
    @NotEmpty
    private String lastName;

    @NotNull
    @NotEmpty
    private String password;
    private String passwordConfirm;

    @NotNull
    @NotEmpty
    @Email
    private String email;

    private String percentageOfTips;

    @NotNull
    private int salary;

    @NotNull
    @NotEmpty
    private String accountNumber;

}
