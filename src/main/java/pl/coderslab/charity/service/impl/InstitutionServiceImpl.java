package pl.coderslab.charity.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.coderslab.charity.entity.Institution;
import pl.coderslab.charity.service.impl.generic.GenericServiceImpl;
import pl.coderslab.charity.repository.InstitutionRepository;
import pl.coderslab.charity.service.InstitutionService;

import java.util.Set;
import java.util.TreeSet;

@Service
public class InstitutionServiceImpl extends GenericServiceImpl<Institution, InstitutionRepository> implements InstitutionService {

    @Autowired
    public InstitutionServiceImpl(InstitutionRepository repository) {
        super(repository);
    }

    @Override
    public Institution findByName(String name) {
        return repository.findByName(name);
    }

    @Override
    public Set<Institution> findAll() {
        return new TreeSet<>(repository.findAll());
    }

}