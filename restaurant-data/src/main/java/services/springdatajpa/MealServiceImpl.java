package services.springdatajpa;

import dto.MealDto;
import javassist.tools.rmi.ObjectNotFoundException;
import lombok.extern.slf4j.Slf4j;
import model.Meal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repositories.MealRepository;
import services.MealService;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

/**
 * Created by ch on 2020-05-16
 */
@Slf4j
@Service
public class MealServiceImpl implements MealService {


    @Autowired
    MealRepository mealRepository;


    @Override
    public  Meal newMeal(MealDto mealDto){
        log.info("Create new Table");
        Meal meal = new Meal();
        meal.setName(mealDto.getName());
        meal.setDescription(mealDto.getDescription());
        meal.setPrice(mealDto.getPrice());
        meal.setWeight(mealDto.getWeight());
        return this.save(meal);
    }

    @Override
    public Set<Meal> findAll() {
        return new HashSet<>(mealRepository.findAll());
    }

    @Override
    public Meal findById(Long id) throws ObjectNotFoundException {
        Optional<Meal> mealOptional = mealRepository.findById(id);

        if (!mealOptional.isPresent()) {
            throw new ObjectNotFoundException("Meal Not Found. For Id value " + id.toString());
        }

        return mealOptional.get();
    }

    @Override
    public Meal save(Meal meal) {
        return mealRepository.save(meal);
    }

    @Override
    public void delete(Meal meal) {
        mealRepository.delete(meal);
    }

    @Override
    public void deleteById(Long id) {
        mealRepository.deleteById(id);
    }
}
