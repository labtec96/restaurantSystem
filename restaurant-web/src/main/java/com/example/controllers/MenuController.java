package com.example.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import services.MealService;

/**
 * Created by ch on 2020-06-12
 */
@Slf4j
@Controller
public class MenuController {

    @Autowired
    MealService mealService;

    @GetMapping("/menu")
    public String menu(Model model) {
        log.info("Get request /menu");
        model.addAttribute("meals", mealService.findAll());
        return "menu";
    }
}
