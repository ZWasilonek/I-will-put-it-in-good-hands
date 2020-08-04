package pl.coderslab.charity.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import pl.coderslab.charity.entity.Authority;
import pl.coderslab.charity.entity.User;
import pl.coderslab.charity.service.impl.generic.GenericServiceImpl;
import pl.coderslab.charity.repository.UserRepository;

import java.util.HashSet;
import java.util.Set;

@Service
public class UserDetailsServiceImpl extends GenericServiceImpl<User, UserRepository> implements UserDetailsService {

    @Autowired
    public UserDetailsServiceImpl(UserRepository repository) {
        super(repository);
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = repository.findByEmail(email);

        if (user != null && user.isEnabled()) {
            Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
            for (Authority role : user.getAuthorities()){
                grantedAuthorities.add(new SimpleGrantedAuthority(role.getName().toString()));
            }
            return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), grantedAuthorities);
        } else {
            throw new UsernameNotFoundException("User not found.");
        }
    }

}