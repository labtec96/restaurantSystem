package com.example.controllers;

import com.example.dto.UserDto;
import com.example.validators.UserValidator;
import lombok.extern.slf4j.Slf4j;
import model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import services.SecurityService;
import services.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

/**
 * Created by ch on 2020-05-06
 */
@Slf4j
@Controller
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private SecurityService securityService;

    @Autowired
    private UserValidator userValidator;

    @GetMapping("/registration")
    public String registration(Model model) {
        log.info("Get request /registration");
        UserDto userDto = new UserDto();
        model.addAttribute("user", userDto);
        return "registration";
    }


    @PostMapping("/registration")
    public String registration(@ModelAttribute("user") @Valid UserDto userDto, HttpServletRequest request, Errors errors) {
        log.info("Post request /registration");
       /* userValidator.validate(userForm, bindingResult);
        if (bindingResult.hasErrors()) {
            return "registration";
        }

        userService.save(userForm);

        securityService.autoLogin(userForm.getUsername(), userForm.getPasswordConfirm());
*/

        return "redirect:/welcome";
    }

    @GetMapping("/login")
    public String login(Model model, String error, String logout) {
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
