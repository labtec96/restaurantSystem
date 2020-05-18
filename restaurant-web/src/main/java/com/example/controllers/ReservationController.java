package com.example.controllers;

import dto.ReservationDto;
import lombok.extern.slf4j.Slf4j;
import model.User;
import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import services.ReservationService;
import services.UserService;

import javax.validation.Valid;
import java.security.Principal;

/**
 * Created by ch on 2020-05-15
 */
@Slf4j
@Controller
public class ReservationController {

    @Autowired
    UserService userService;

    @Autowired
    ReservationService reservationService;

    @GetMapping("/reservation")
    public String getReservation(Model model) {
        log.info("Get request /reservation");
        //model.addAttribute("tables", restaurantTableService.findAll());
        return "reservation";
    }

    @PostMapping("/reservation/book")
    public String getReservation(Principal user, Model model,  @ModelAttribute("reservation") @Valid ReservationDto reservationDto) {
        log.info("Get request /reservation/book");

        User user1 = userService.findByEmail(user.getName());

        reservationService.book(user,reservationDto);

        //reservationService
        //model.addAttribute("tables", restaurantTableService.findAll());
        return "redirect:/reservation";
    }

    @GetMapping("/admin/reservation")
    public String reservation(Model model) {
        log.info("Get request /admin/reservation");
        //model.addAttribute("tables", restaurantTableService.findAll());
        return "admin/reservation";
    }
}
