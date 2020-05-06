package repositories;

import model.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by ch on 2020-05-06
 */
public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
}
