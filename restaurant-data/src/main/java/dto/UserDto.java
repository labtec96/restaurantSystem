package dto;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import model.Address;
import validators.PasswordMatches;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.lang.reflect.Field;

/**
 * Created by ch on 2020-05-07
 */
@PasswordMatches
@Getter
@Setter
@NoArgsConstructor
public class UserDto {

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

    private Address address;
}
