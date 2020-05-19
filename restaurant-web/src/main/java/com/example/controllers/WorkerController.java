package com.example.controllers;

import lombok.extern.slf4j.Slf4j;
import model.Manager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import services.CookService;
import services.ManagerService;
import services.WaiterService;

/**
 * Created by ch on 2020-05-15
 */
@Slf4j
@Controller
public class WorkerController {

    @Autowired
    WaiterService waiterService;

    @Autowired
    ManagerService managerService;

    @Autowired
    CookService cookService;

    @GetMapping("/admin/worker")
    public String worker(Model model) {
        log.info("Get request /admin/worker");
        model.addAttribute("managers", managerService.findAll());
        model.addAttribute("cooks", cookService.findAll());
        model.addAttribute("waiters", waiterService.findAll());
        return "admin/worker";
    }
}
