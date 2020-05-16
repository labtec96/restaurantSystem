package repositories;

import model.Meal;
import model.RestaurantTable;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by ch on 2020-05-16
 */
public interface MealRepository extends JpaRepository<Meal, Long> {
}
