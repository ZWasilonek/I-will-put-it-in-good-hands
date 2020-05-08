package pl.coderslab.charity.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import pl.coderslab.charity.dto.UserDTO;
import pl.coderslab.charity.entity.User;
import pl.coderslab.charity.impl.generic.GenericServiceImpl;
import pl.coderslab.charity.repository.UserRepository;
import pl.coderslab.charity.service.UserService;

@Service
public class UserServiceImpl extends GenericServiceImpl<User, UserRepository> implements UserService<User> {

    private final BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository repository, BCryptPasswordEncoder passwordEncoder) {
        super(repository);
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User findByEmail(String email) {
        return repository.findByEmail(email);
    }

    @Override
    public void saveUserDTO(UserDTO userDTO) {
        User user = new User();
        user.setEmail(userDTO.getEmail());
        user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        user.setFirstName(userDTO.getFirstName().toUpperCase());
        user.setLastName(userDTO.getLastName().toUpperCase());
        repository.save(user);
    }

}
