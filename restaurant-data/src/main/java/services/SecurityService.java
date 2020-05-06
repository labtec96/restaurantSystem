package services;

/**
 * Created by ch on 2020-05-06
 */
public interface SecurityService {
    String findLoggedInUsername();

    void autoLogin(String username, String password);
}
