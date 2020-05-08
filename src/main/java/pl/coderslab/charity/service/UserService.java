package pl.coderslab.charity.service;

import pl.coderslab.charity.dto.UserDTO;
import pl.coderslab.charity.entity.User;
import pl.coderslab.charity.service.generic.GenericService;

public interface UserService<T> extends GenericService<T> {
    T findByEmail(String email);
    void saveUserDTO(UserDTO userDTO);
    void setRole(User user);
}
