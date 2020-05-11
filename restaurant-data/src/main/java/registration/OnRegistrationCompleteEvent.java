package registration;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import model.User;
import org.springframework.context.ApplicationEvent;

import java.util.Locale;

/**
 * Created by ch on 2020-05-11
 */
@Slf4j
public class OnRegistrationCompleteEvent extends ApplicationEvent {
    private String appUrl;
    private Locale locale;
    private User user;

    public OnRegistrationCompleteEvent(
            User user, Locale locale, String appUrl) {
        super(user);

        this.user = user;
        this.locale = locale;
        this.appUrl = appUrl;
    }

    public String getAppUrl() {
        return appUrl;
    }

    public Locale getLocale() {
        return locale;
    }

    public User getUser() {
        return user;
    }
}