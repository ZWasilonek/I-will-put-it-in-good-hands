package pl.coderslab.charity.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.coderslab.charity.entity.Authority;
import pl.coderslab.charity.entity.generic.AuthorityType;
import pl.coderslab.charity.service.impl.generic.GenericServiceImpl;
import pl.coderslab.charity.repository.AuthorityRepository;
import pl.coderslab.charity.service.AuthorityService;

@Service
public class AuthorityServiceImpl extends GenericServiceImpl<Authority, AuthorityRepository> implements AuthorityService {

    @Autowired
    public AuthorityServiceImpl(AuthorityRepository repository) {
        super(repository);
    }

    @Override
    public Authority findByName(AuthorityType name) {
        return repository.findByName(name);
    }

}