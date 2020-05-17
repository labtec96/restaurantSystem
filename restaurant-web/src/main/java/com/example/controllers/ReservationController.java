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
public class ReservationController {

    @GetMapping("/reservation")
    public String getReservation(Model model) {
        log.info("Get request /reservation");
        //model.addAttribute("tables", restaurantTableService.findAll());
        return "reservation";
    }

    @GetMapping("/admin/reservation")
    public String reservation(Model model) {
        log.info("Get request /admin/reservation");
        //model.addAttribute("tables", restaurantTableService.findAll());
        return "admin/reservation";
    }
}
