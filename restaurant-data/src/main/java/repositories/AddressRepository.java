package repositories;

import model.Address;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by ch on 2020-05-08
 */
public interface AddressRepository extends JpaRepository<Address, Long> {
}
