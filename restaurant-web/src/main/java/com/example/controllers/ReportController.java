package com.example.controllers;

import javassist.tools.rmi.ObjectNotFoundException;
import lombok.extern.slf4j.Slf4j;
import model.Report;
import model.RestaurantTable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import services.ReportService;

/**
 * Created by ch on 2020-05-15
 */
@Slf4j
@Controller
public class ReportController {

    @Autowired
    ReportService reportService;

    @GetMapping("/admin/report")
    public String report(Model model) {
        log.info("Get request /admin/report");
        //model.addAttribute("tables", restaurantTableService.findAll());
        return "admin/report";
    }

    @GetMapping("/admin/report/reservationPerDay")
    @ResponseBody
    public Report reservationPerDay() {
        log.info("Get request /admin/report/reservationPerDay");
        return reportService.reservationPerDay();
    }

    @GetMapping("/admin/report/reservationPerMonth")
    @ResponseBody
    public Report reservationPerMonth() {
        log.info("Get request /admin/report/reservationPerMonth");
        return reportService.reservationPerMonth();
    }

    @GetMapping("/admin/report/reservationNumberOfPeople")
    @ResponseBody
    public Report reservationNumberOfPeople() {
        log.info("Get request /admin/report/reservationNumberOfPeople");
        return reportService.reservationNumberOfPeople();
    }

    @GetMapping("/admin/report/expenditureOnSalaries")
    @ResponseBody
    public Report expenditureOnSalaries() {
        log.info("Get request /admin/report/expenditureOnSalaries");
        return reportService.expenditureOnSalaries();
    }

    @GetMapping("/admin/report/employees")
    @ResponseBody
    public Report employees() {
        log.info("Get request /admin/report/employees");
        return reportService.employees();
    }
}
