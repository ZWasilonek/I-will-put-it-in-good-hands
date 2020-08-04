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
    public User saveUser(User user) {
        User newUser = new User();
        newUser.setEmail(user.getEmail());
        newUser.setPassword(passwordEncoder.encode(user.getPassword()));
        newUser.setFirstName(user.getFirstName().toUpperCase());
        newUser.setLastName(user.getLastName().toUpperCase());
        newUser.setEnabled(true);
        repository.save(newUser);
        setRole(newUser);
        return newUser;
    }

    @Override
    public User updateUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setFirstName(user.getFirstName().toUpperCase());
        user.setLastName(user.getLastName().toUpperCase());
        repository.save(user);
        return user;
    }

    @Override
    public boolean disableUser(Long userId) {
        Optional<User> foundedUser = repository.findById(userId);
        if (foundedUser.isPresent()) {
            foundedUser.get().setEnabled(false);
            repository.save(foundedUser.get());
            return true;
        }
        return false;
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