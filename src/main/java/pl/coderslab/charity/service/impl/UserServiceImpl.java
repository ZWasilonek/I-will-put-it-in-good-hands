package pl.coderslab.charity.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import pl.coderslab.charity.entity.Authority;
import pl.coderslab.charity.entity.User;
import pl.coderslab.charity.entity.generic.AuthorityType;
import pl.coderslab.charity.service.impl.generic.GenericServiceImpl;
import pl.coderslab.charity.repository.AuthorityRepository;
import pl.coderslab.charity.repository.UserRepository;
import pl.coderslab.charity.service.UserService;

import java.util.*;

@Service
public class UserServiceImpl extends GenericServiceImpl<User, UserRepository> implements UserService {

    private final BCryptPasswordEncoder passwordEncoder;
    private final AuthorityRepository authorityRepository;

    @Autowired
    public UserServiceImpl(UserRepository repository, BCryptPasswordEncoder passwordEncoder, AuthorityRepository authorityRepository) {
        super(repository);
        this.passwordEncoder = passwordEncoder;
        this.authorityRepository = authorityRepository;
    }

    @Override
    public User findByEmail(String email) {
        return repository.findByEmail(email);
    }

    @Override
    public void saveUser(User user) {
        User newUser = new User();
        newUser.setEmail(user.getEmail());
        newUser.setPassword(passwordEncoder.encode(user.getPassword()));
        newUser.setFirstName(user.getFirstName().toUpperCase());
        newUser.setLastName(user.getLastName().toUpperCase());
        repository.save(newUser);
        setRole(newUser);
    }

    @Override
    public void setRole(User user) {
        Optional<User> admin = repository.findById(1L);
        Authority roleAdmin = authorityRepository.findByName(AuthorityType.ROLE_ADMIN);
        Authority roleUser = authorityRepository.findByName(AuthorityType.ROLE_USER);
        if (admin.isPresent()) {
            if (!user.getEmail().equals(admin.get().getEmail()))
                user.getAuthorities().add(roleUser);
        } else {
            user.getAuthorities().add(roleAdmin);
            user.getAuthorities().add(roleUser);
        }
        repository.save(user);
    }

}