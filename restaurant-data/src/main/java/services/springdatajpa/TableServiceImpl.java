package services.springdatajpa;

import model.RestaurantTable;
import model.User;
import org.springframework.beans.factory.annotation.Autowired;
import repositories.RestaurantTableRepository;
import repositories.RoleRepository;
import services.RestaurantTableService;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by ch on 2020-05-14
 */
public class TableServiceImpl implements RestaurantTableService{

    @Autowired
    private RestaurantTableRepository restaurantTableRepository;

    @Override
    public Set<RestaurantTable> findAll() {
        Set<RestaurantTable> tables = new HashSet<>();
        restaurantTableRepository.findAll().forEach(tables::add);
        return tables;
    }

    @Override
    public RestaurantTable findById(Long id) {
        return restaurantTableRepository.findById(id).orElse(null);
    }

    @Override
    public RestaurantTable save(RestaurantTable table) {
        return restaurantTableRepository.save(table);
    }

    @Override
    public void delete(RestaurantTable table) {
        restaurantTableRepository.delete(table);
    }

    @Override
    public void deleteById(Long id) {
        restaurantTableRepository.deleteById(id);
    }
}
