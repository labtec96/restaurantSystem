package services;

import dto.MealDto;
import model.Meal;

/**
 * Created by ch on 2020-05-16
 */
public interface MealService extends CrudService<Meal,Long>{

    Meal newMeal(MealDto mealDto);
}
