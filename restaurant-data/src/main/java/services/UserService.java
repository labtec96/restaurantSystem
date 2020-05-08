package services;

import dto.AddresDto;
import dto.UserDto;
import error.UserAlreadyExistException;
import model.User;

/**
 * Created by ch on 2020-05-06
 */
public interface UserService {
    User registerNewUserAccount(UserDto userDto, AddresDto addresDto)
            throws UserAlreadyExistException;

    User findByUsername(String username);
}
