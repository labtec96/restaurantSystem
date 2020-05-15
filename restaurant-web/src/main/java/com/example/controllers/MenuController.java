package com.example.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Created by ch on 2020-05-15
 */
@Slf4j
@Controller
public class MenuController {

    @GetMapping("/admin/menu")
    public String menu(Model model) {
        log.info("Get request /admin/menu");
        //model.addAttribute("tables", restaurantTableService.findAll());
        return "admin/menu";
    }
}
