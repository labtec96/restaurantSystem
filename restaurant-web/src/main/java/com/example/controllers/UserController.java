package com.example.controllers;

import dto.AddresDto;
import dto.UserDto;
import error.UserAlreadyExistException;
import lombok.extern.slf4j.Slf4j;
import model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import registration.OnRegistrationCompleteEvent;
import services.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.security.Principal;

/**
 * Created by ch on 2020-05-06
 */
@Slf4j
@Controller
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    ApplicationEventPublisher eventPublisher;

    @GetMapping("/registration")
    public String registration(Model model) {
        log.info("Get request /registration");
        UserDto userDto = new UserDto();
        AddresDto addresDto = new AddresDto();
        model.addAttribute("user", userDto);
        model.addAttribute("address",addresDto);
        return "registration";
    }


    @PostMapping("/registration")
    public String registration(Model model, @ModelAttribute("user") @Valid UserDto userDto,BindingResult resultUser, @ModelAttribute("address") @Valid AddresDto addresDto, BindingResult resultAddress, HttpServletRequest request){
        log.info("Post request /registration");
       /* userValidator.validate(userForm, bindingResult);
        if (bindingResult.hasErrors()) {
            return "registration";
        }

        userService.save(userForm);

        securityService.autoLogin(userForm.getUsername(), userForm.getPasswordConfirm());
*/

        try {
            log.info("Controller Starting to register user");
            User registered = userService.registerNewUserAccount(userDto,addresDto);
            String appUrl = request.getContextPath();
            eventPublisher.publishEvent(new OnRegistrationCompleteEvent(registered,
                    request.getLocale(), appUrl));
        } catch (UserAlreadyExistException uaeEx) {
            resultUser.rejectValue("email", null , "Istnieje już użytkownik z takim emailem");
        }

        if (resultUser.hasErrors()){
            resultUser.getAllErrors().forEach(objectError -> {
                log.error(objectError.toString());
                log.error(objectError.getObjectName());
            });
            return "registration";
        }

        return "redirect:/login";
    }

    @GetMapping("/login")
    public String login(Model model, String error, String logout) {
        if (error != null)
            model.addAttribute("error", "Login i hasło się nie zgadzają");

        if (logout != null)
            model.addAttribute("message", "Zostałeś wylogowane poprawnie");

        return "login";
    }

    @GetMapping("/userReservation")
    public String reservation(Principal user, Model model) {
        log.info("Get request /reservation");

        User user1 = userService.findByEmail(user.getName());

        model.addAttribute("reservations", user1.getReservations());

        return "reservation";
    }

    @GetMapping("/admin/user/{id}/delete")
    public String deleteTable(@PathVariable String id, Model model) {
        log.info("Get Delete User: Deleting user id " + id);
        userService.deleteById(Long.valueOf(id));
        return "redirect:/admin/worker";
    }

    @GetMapping({"/", "/welcome", "/homepage.html", "/homepage"})
    public String welcome(Model model) {
        return "welcome";
    }
}
