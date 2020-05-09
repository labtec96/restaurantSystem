package com.example.controllers;

import dto.AddresDto;
import dto.UserDto;
import error.UserAlreadyExistException;
import lombok.extern.slf4j.Slf4j;
import model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import services.UserService;
import javax.validation.Valid;

/**
 * Created by ch on 2020-05-06
 */
@Slf4j
@Controller
public class UserController {
    @Autowired
    private UserService userService;

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
    public String registration(Model model, @ModelAttribute("user") @Valid UserDto userDto,BindingResult resultUser, @ModelAttribute("address") @Valid AddresDto addresDto, BindingResult resultAddress){
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

        return "redirect:/welcome";
    }

    @GetMapping("/login")
    public String login(Model model, String error, String logout) {
        log.info("Get Login " + error);
        if (error != null)
            model.addAttribute("error", "Your username and password is invalid.");

        if (logout != null)
            model.addAttribute("message", "You have been logged out successfully.");

        return "login";
    }

    @GetMapping({"/", "/welcome"})
    public String welcome(Model model) {
        return "welcome";
    }
}
