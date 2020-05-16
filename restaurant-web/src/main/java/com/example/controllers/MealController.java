package com.example.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import services.MealService;

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
}
