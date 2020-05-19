package repositories;

import model.Manager;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by ch on 2020-05-19
 */
public interface ManagerRepository extends JpaRepository<Manager, Long> {
}
