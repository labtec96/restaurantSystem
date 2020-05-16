package com.example.controllers;

import dto.MealDto;
import javassist.tools.rmi.ObjectNotFoundException;
import lombok.extern.slf4j.Slf4j;
import model.Meal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import services.MealService;

import javax.validation.Valid;

/**
 * Created by ch on 2020-05-15
 */
@Slf4j
@Controller
public class MealController {

    @Autowired
    MealService mealService;

    @GetMapping("/admin/menu")
    public String menu(Model model) {
        log.info("Get request /admin/menu");
        model.addAttribute("meals", mealService.findAll());
        return "admin/menu";
    }

    @GetMapping("/admin/meal/{id}")
    @ResponseBody
    public Meal getMeal(@PathVariable String id, Model model) {
        log.info("Get request /admin/meal/{id}");
        try {
            return mealService.findById(Long.valueOf(id));
        }catch (ObjectNotFoundException ex){
            //Todo
            return null;
        }
    }

    @PostMapping("/admin/meal/update")
    public String update(Model model, @ModelAttribute("meal") @Valid MealDto mealDto, BindingResult resultMeal) {
        log.info("Post request /admin/meal/update: for id" + mealDto.getId());
        try {
            Meal updatedMeal = mealService.update(mealDto);
            log.info("Updated meal with id:" + updatedMeal.getId());
        } catch (error.ObjectNotFoundException uaeEx) {
            resultMeal.rejectValue("errors", null, uaeEx.getMessage());
        }

        return "redirect:/admin/menu";
    }

    @PostMapping("/admin/meal/new")
    public String newMeal(Model model, @ModelAttribute("meal") @Valid MealDto mealDto, BindingResult resultMeal) {
        log.info("Post request /admin/meal/new");
        mealService.newMeal(mealDto);
        return "redirect:/admin/menu";
    }


    @GetMapping("/admin/meal/{id}/delete")
    public String deleteMeal(@PathVariable String id, Model model) {
        log.info("Get Delete Meal: Deleting meal id " + id);
        mealService.deleteById(Long.valueOf(id));
        return "redirect:/admin/menu";
    }
}
