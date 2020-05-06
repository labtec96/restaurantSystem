package repositories;

import model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by ch on 2020-05-06
 */
public interface RoleRepository extends JpaRepository<Role, Long> {
}
