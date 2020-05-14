package repositories;

import model.RestaurantTable;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by ch on 2020-05-14
 */
public interface RestaurantTableRepository extends JpaRepository<RestaurantTable, Long> {
}
