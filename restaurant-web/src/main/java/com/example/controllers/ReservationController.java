package com.example.controllers;

import dto.ReservationDto;
import javassist.tools.rmi.ObjectNotFoundException;
import lombok.extern.slf4j.Slf4j;
import model.Reservation;
import model.User;
import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import services.ReservationService;
import services.UserService;
import util.Utils;

import javax.validation.Valid;
import java.security.Principal;
import java.time.LocalDate;
import java.time.LocalTime;

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

        //User user1 = userService.findByEmail(user.getName());

        Reservation reservation = new Reservation();

        LocalDate date = Utils.parseDate(reservationDto.getDate());
        LocalTime startHour = Utils.parseHour(reservationDto.getStartHour());
        LocalTime endHour = Utils.parseHour(reservationDto.getEndHour());
        if(Utils.isValidDate(reservationDto.getDate(),reservationDto.getStartHour())) {
            reservation.setDate(date);
            reservation.setEndHour(endHour);
            reservation.setStartHour(startHour);
            reservation.setPersons(reservationDto.getPersons());

            reservationService.book(user, reservation);
        }
        else {
            log.error("Date bad format");
            throw new RuntimeException("Bad date format");
        }
        //reservationService
        //model.addAttribute("tables", restaurantTableService.findAll());
        return "redirect:/reservation";
    }

    @GetMapping("/admin/reservation")
    public String reservation(Model model) {
        log.info("Get request /admin/reservation");
        model.addAttribute("reservationsToConfirm", reservationService.findAllToConfirm());
        model.addAttribute("reservationsConfirmed", reservationService.findAllConfirmedForToday());
        return "admin/reservation";
    }


    @GetMapping("/admin/reservation/{id}/confirm")
    public String confirmReservation(@PathVariable String id, Model model) {
        log.info("Get Confirm reservation for reservation id " + id);

        try {
            reservationService.confirmById(Long.valueOf(id));
        }catch (ObjectNotFoundException re){
            model.addAttribute("error", re.getMessage());
        }

        return "redirect:/admin/reservation";
    }

    @GetMapping("/admin/reservation/{id}/reject")
    public String rejectReservation(@PathVariable String id, Model model) {
        log.info("Get Reject reservation for reservation id " + id);
        try {
            reservationService.rejectById(Long.valueOf(id));
        }catch (ObjectNotFoundException re){
            model.addAttribute("error", re.getMessage());
        }
        return "redirect:/admin/reservation";
    }



}
