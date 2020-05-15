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
public class ReportController {

    @GetMapping("/admin/report")
    public String report(Model model) {
        log.info("Get request /admin/report");
        //model.addAttribute("tables", restaurantTableService.findAll());
        return "admin/report";
    }
}
