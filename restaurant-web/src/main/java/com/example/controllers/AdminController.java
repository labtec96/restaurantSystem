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

}
