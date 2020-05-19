package repositories;

import model.Cook;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by ch on 2020-05-19
 */
public interface CookRepository extends JpaRepository<Cook, Long> {

}
