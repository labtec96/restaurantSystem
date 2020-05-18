package services;

import dto.RestaurantTableDto;
import javassist.tools.rmi.ObjectNotFoundException;
import model.RestaurantTable;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Optional;

/**
 * Created by ch on 2020-05-14
 */
public interface RestaurantTableService extends CrudService<RestaurantTable,Long>{

    RestaurantTable update(RestaurantTableDto restaurantTableDto) throws ObjectNotFoundException;

    RestaurantTable newTable(RestaurantTableDto restaurantTableDto);

    Optional<RestaurantTable> findFreeTable(LocalDate date, LocalTime hour, LocalTime endHour, int persons);
}
