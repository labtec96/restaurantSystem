package com.example.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Created by ch on 2020-06-12
 */
@Slf4j
@Controller
public class AboutController {

    @GetMapping("/about")
    public String menu() {
        log.info("Get request /about");
        return "about";
    }
}
