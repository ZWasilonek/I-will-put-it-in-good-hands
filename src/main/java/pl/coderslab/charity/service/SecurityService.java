package pl.coderslab.charity.service;

public interface SecurityService {
    String findLoggedInUsername();

    void autoLogin(String username, String password);
}
