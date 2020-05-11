package repositories;

import model.User;
import model.VerificationToken;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by ch on 2020-05-11
 */
public interface VerificationTokenRepository extends JpaRepository<VerificationToken, Long> {

    VerificationToken findByToken(String token);

    VerificationToken findByUser(User user);
}
