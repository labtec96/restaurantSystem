package repositories;

import model.VerificationToken;
import model.Waiter;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by ch on 2020-05-19
 */
public interface WaiterRepository extends JpaRepository<Waiter, Long> {
}
