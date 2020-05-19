package com.example.controllers;

import lombok.extern.slf4j.Slf4j;
import model.User;
import model.VerificationToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.WebRequest;
import repositories.VerificationTokenRepository;
import services.UserService;

import java.util.Calendar;
import java.util.Locale;

/**
 * Created by ch on 2020-05-11
 */
@Slf4j
@Controller
public class RegistrationController {

    @Autowired
    private UserService userService;

    @Autowired
    VerificationTokenRepository verificationTokenRepository;

    @GetMapping("/registrationConfirm")
    public String confirmRegistration
            (WebRequest request, Model model, @RequestParam("token") String token) {

        Locale locale = request.getLocale();

        VerificationToken verificationToken = userService.getVerificationToken(token);
        if (verificationToken == null) {
            log.info("invalid token");
            String message = "Niepoprawny token";
            model.addAttribute("message", message);
            return "redirect:/badUser?lang=" + locale.getLanguage();
        }

        User user = verificationToken.getUser();
        Calendar cal = Calendar.getInstance();
        if ((verificationToken.getExpiryDate().getTime() - cal.getTime().getTime()) <= 0) {
            log.info("The token has expired");
            String messageValue = "Token wygasł";
            model.addAttribute("message", messageValue);
            return "redirect:/badUser?lang=" + locale.getLanguage();
        }

        user.setEnabled(true);

        verificationTokenRepository.delete(verificationToken);
        userService.saveRegisteredUser(user);
        return "redirect:/login";
    }
}
