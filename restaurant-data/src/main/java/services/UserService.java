package services;

import model.dto.UserDto;
import error.UserAlreadyExistException;
import model.User;

/**
 * Created by ch on 2020-05-06
 */
public interface UserService {
    User registerNewUserAccount(UserDto userDto)
            throws UserAlreadyExistException;

    User findByUsername(String username);
}
