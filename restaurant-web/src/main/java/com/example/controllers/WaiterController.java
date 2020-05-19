package com.example.controllers;

import dto.AddresDto;
import dto.UserDto;
import dto.WaiterDto;
import error.UserAlreadyExistException;
import lombok.extern.slf4j.Slf4j;
import model.User;
import model.Waiter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import registration.OnRegistrationCompleteEvent;
import services.WaiterService;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

/**
 * Created by ch on 2020-05-19
 */
@Slf4j
@Controller
public class WaiterController {

    @Autowired
    WaiterService waiterService;

    @Autowired
    ApplicationEventPublisher eventPublisher;

    @PostMapping("/admin/waiter/registration")
    public String registration(Model model, @ModelAttribute("waiter") @Valid WaiterDto waiterDto, BindingResult resultWaiter, HttpServletRequest request){
        try {
            log.info("Controller Starting to register new waiter");
            Waiter registered = waiterService.registerNewUserAccount(waiterDto);
            String appUrl = request.getContextPath();
            eventPublisher.publishEvent(new OnRegistrationCompleteEvent(registered,
                    request.getLocale(), appUrl));
        } catch (UserAlreadyExistException uaeEx) {
            resultWaiter.rejectValue("email", null , "Istnieje już użytkownik z takim emailem");
        }

        if (resultWaiter.hasErrors()){
            resultWaiter.getAllErrors().forEach(objectError -> {
                log.error(objectError.toString());
                log.error(objectError.getObjectName());
            });
            return "redirect:/admin/worker";
        }

        return "redirect:/admin/worker";
    }
}
