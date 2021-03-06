package services;

import dto.AddresDto;
import dto.UserDto;
import error.UserAlreadyExistException;
import model.User;
import model.VerificationToken;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 * Created by ch on 2020-05-06
 */
public interface UserService extends CrudService <User,Long> {
    User registerNewUserAccount(UserDto userDto, AddresDto addresDto)
            throws UserAlreadyExistException;

    User getUser(String verificationToken);

    void saveRegisteredUser(User user);

    void createVerificationToken(User user, String token);

    VerificationToken getVerificationToken(String VerificationToken);

    User findByUsername(String username);
    User findByEmail(String email);

    boolean emailExists(String email);
}
