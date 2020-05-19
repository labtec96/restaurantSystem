package com.example.controllers;

import dto.ManagerDto;
import dto.WaiterDto;
import error.UserAlreadyExistException;
import lombok.extern.slf4j.Slf4j;
import model.Manager;
import model.Waiter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import registration.OnRegistrationCompleteEvent;
import services.ManagerService;
import services.WaiterService;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

/**
 * Created by ch on 2020-05-19
 */
@Slf4j
@Controller
public class ManagerController {
    @Autowired
    ManagerService managerService;

    @Autowired
    ApplicationEventPublisher eventPublisher;

    @PostMapping("/admin/manager/registration")
    public String registration(Model model, @ModelAttribute("manager") @Valid ManagerDto managerDto, BindingResult resultManager, HttpServletRequest request){
        try {
            log.info("Controller Starting to register new manager");
            Manager registered = managerService.registerNewManagerAccount(managerDto);
            String appUrl = request.getContextPath();
            eventPublisher.publishEvent(new OnRegistrationCompleteEvent(registered,
                    request.getLocale(), appUrl));
        } catch (UserAlreadyExistException uaeEx) {
            resultManager.rejectValue("email", null , "Istnieje już użytkownik z takim emailem");
        }

        if (resultManager.hasErrors()){
            resultManager.getAllErrors().forEach(objectError -> {
                log.error(objectError.toString());
                log.error(objectError.getObjectName());
            });
            return "redirect:/admin/worker";
        }

        return "redirect:/admin/worker";
    }
}
