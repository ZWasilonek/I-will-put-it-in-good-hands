package pl.coderslab.charity.impl;

import org.springframework.beans.factory.annotation.Autowired;
import pl.coderslab.charity.entity.Authority;
import pl.coderslab.charity.entity.generic.AuthorityType;
import pl.coderslab.charity.impl.generic.GenericServiceImpl;
import pl.coderslab.charity.repository.AuthorityRepository;
import pl.coderslab.charity.service.AuthorityService;

public class AuthorityServiceImpl extends GenericServiceImpl<Authority, AuthorityRepository> implements AuthorityService<Authority> {

    @Autowired
    public AuthorityServiceImpl(AuthorityRepository repository) {
        super(repository);
    }

    @Override
    public Authority findByName(AuthorityType name) {
        return repository.findByName(name);
    }
}
