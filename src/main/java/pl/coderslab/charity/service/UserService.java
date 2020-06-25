package pl.coderslab.charity.service;

import pl.coderslab.charity.entity.User;
import pl.coderslab.charity.service.generic.GenericService;

public interface UserService extends GenericService<User> {

    User findByEmail(String email);
    void saveUser(User user);
    void setRole(User user);

}