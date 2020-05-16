package services;

import dto.MealDto;
import error.ObjectNotFoundException;
import model.Meal;

/**
 * Created by ch on 2020-05-16
 */
public interface MealService extends CrudService<Meal,Long>{

    Meal update(MealDto mealDto) throws ObjectNotFoundException;

    Meal newMeal(MealDto mealDto);
}
