package com.example.controllers;

import dto.CookDto;
import error.UserAlreadyExistException;
import lombok.extern.slf4j.Slf4j;
import model.Cook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import registration.OnRegistrationCompleteEvent;
import services.CookService;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

/**
 * Created by ch on 2020-05-19
 */
@Slf4j
@Controller
public class CookController {

    @Autowired
    CookService cookService;

    @Autowired
    ApplicationEventPublisher eventPublisher;

    @PostMapping("/admin/cook/registration")
    public String registration(Model model, @ModelAttribute("cook") @Valid CookDto cookDto, BindingResult resultCook, HttpServletRequest request){
        try {
            log.info("Controller Starting to register new cook");
            Cook registered = cookService.registerNewCookAccount(cookDto);
            String appUrl = request.getContextPath();
            eventPublisher.publishEvent(new OnRegistrationCompleteEvent(registered,
                    request.getLocale(), appUrl));
        } catch (UserAlreadyExistException uaeEx) {
            resultCook.rejectValue("email", null , "Istnieje już użytkownik z takim emailem");
        }

        if (resultCook.hasErrors()){
            resultCook.getAllErrors().forEach(objectError -> {
                log.error(objectError.toString());
                log.error(objectError.getObjectName());
            });
            return "redirect:/admin/worker";
        }

        return "redirect:/admin/worker";
    }
}
