package registration.listener;

import lombok.extern.slf4j.Slf4j;
import model.User;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationListener;
import org.springframework.context.MessageSource;
import org.springframework.core.env.Environment;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;
import registration.OnRegistrationCompleteEvent;
import services.UserService;

import java.util.UUID;

/**
 * Created by ch on 2020-05-11
 */
@Slf4j
@Component
public class RegistrationListener implements ApplicationListener<OnRegistrationCompleteEvent> {
    
    @Autowired
    private UserService userService;

    @Qualifier("messageSource")
    @Autowired
    private MessageSource messages;

    @Autowired
    private JavaMailSender mailSender;

    @Override
    public void onApplicationEvent(OnRegistrationCompleteEvent event) {
        log.info("New RegistrationListener ");
        this.confirmRegistration(event);
    }

    private void confirmRegistration(OnRegistrationCompleteEvent event) {
        try {
            User user = event.getUser();
            log.info("Sending confirm email to email adres: " + user.getEmail());
            String token = UUID.randomUUID().toString();
            userService.createVerificationToken(user, token);

            String recipientAddress = user.getEmail();
            String subject = "Potwierdzenie rejestracji";
            String confirmationUrl
                    = event.getAppUrl() + "/registrationConfirm?token=" + token;
            String message = messages.getMessage("message.regSucc", null, event.getLocale());

            SimpleMailMessage email = new SimpleMailMessage();
            email.setTo(recipientAddress);
            email.setSubject(subject);
            email.setText(message + "\r\n" + "http://localhost:8080" + confirmationUrl);
            mailSender.send(email);
        }catch (Exception e){
            System.out.println(e.toString());
            log.error(e.getMessage());
        }
    }
}
