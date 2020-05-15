package com.example.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Created by ch on 2020-05-14
 */
@Slf4j
@Controller
public class AdminController {

    @GetMapping("/admin")
    public String admin(Model model) {
        return "admin/admin";
    }

    @GetMapping("/admin/report")
    public String report(Model model) {
        return "report";
    }

    @GetMapping("/admin/worker")
    public String worker(Model model) {
        return "worker";
    }

    @GetMapping("/admin/reservation")
    public String reservation(Model model) {
        return "reservation";
    }

    @GetMapping("/admin/menu")
    public String menu(Model model) {
        return "menu";
    }

}
