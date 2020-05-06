package services;

import model.User;

/**
 * Created by ch on 2020-05-06
 */
public interface UserService {

    void save(User user);

    User findByUsername(String username);
}
