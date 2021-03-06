package services.springdatajpa;

import dto.RestaurantTableDto;
import javassist.tools.rmi.ObjectNotFoundException;
import lombok.extern.slf4j.Slf4j;
import model.RestaurantTable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repositories.RestaurantTableRepository;
import services.RestaurantTableService;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

/**
 * Created by ch on 2020-05-14
 */
@Slf4j
@Service
public class RestaurantTableServiceImpl implements RestaurantTableService{

    @Autowired
    private RestaurantTableRepository restaurantTableRepository;

    @Override
    public  RestaurantTable newTable(RestaurantTableDto restaurantTableDto){
        log.info("Create new Table");
        RestaurantTable restaurantTable = new RestaurantTable();
        restaurantTable.setNumber(restaurantTableDto.getNumber());
        restaurantTable.setMaxNumberOfPeople(restaurantTableDto.getMaxNumberOfPeople());
        restaurantTable.setFloor(restaurantTableDto.getFloor());
        return restaurantTableRepository.save(restaurantTable);
    }

    @Override
    public RestaurantTable update(RestaurantTableDto restaurantTableDto) throws ObjectNotFoundException {

        Optional<RestaurantTable> restaurantTableOptional = restaurantTableRepository.findById(restaurantTableDto.getId());
        log.info("Update restaurant Table");
        if(!restaurantTableOptional.isPresent()){

            log.error("Table not found for id: " + restaurantTableDto.getId());
            throw new ObjectNotFoundException("Nie znaleziono Stolika z podanym id: " + restaurantTableDto.getId());
        } else {
            RestaurantTable restaurantTable = restaurantTableOptional.get();

            restaurantTable.setFloor(restaurantTableDto.getFloor());
            restaurantTable.setMaxNumberOfPeople(restaurantTableDto.getMaxNumberOfPeople());
            restaurantTable.setNumber(restaurantTableDto.getNumber());
            return restaurantTableRepository.save(restaurantTable);
        }
    }

    @Override
    public Optional<RestaurantTable> findFreeTable(LocalDate date, LocalTime startHour, LocalTime endHour, int persons) {
        log.info("Looking for free table");


        return restaurantTableRepository.findAll()
                .stream()
                .filter(table -> table.getMaxNumberOfPeople()>=persons)
                .filter(table -> table.isFree(date, startHour, endHour))
                .findFirst();

    }

    @Override
    public Set<RestaurantTable> findAll() {
        Set<RestaurantTable> tables = new HashSet<>();
        restaurantTableRepository.findAll().forEach(tables::add);
        return tables;
    }

    @Override
    public RestaurantTable findById(Long id) throws ObjectNotFoundException {

        Optional<RestaurantTable> tableOptional = restaurantTableRepository.findById(id);

        if (!tableOptional.isPresent()) {
            throw new ObjectNotFoundException("Table Not Found. For Id value " + id.toString());
        }

        return tableOptional.get();

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
