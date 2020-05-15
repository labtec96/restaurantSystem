package services;

import dto.RestaurantTableDto;
import javassist.tools.rmi.ObjectNotFoundException;
import model.RestaurantTable;

/**
 * Created by ch on 2020-05-14
 */
public interface RestaurantTableService extends CrudService<RestaurantTable,Long>{

    RestaurantTable update(RestaurantTableDto restaurantTableDto) throws ObjectNotFoundException;
}
