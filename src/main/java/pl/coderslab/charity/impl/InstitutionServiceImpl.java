package pl.coderslab.charity.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.coderslab.charity.entity.Institution;
import pl.coderslab.charity.impl.generic.GenericServiceImpl;
import pl.coderslab.charity.repository.InstitutionRepository;
import pl.coderslab.charity.service.InstitutionService;

@Service
public class InstitutionServiceImpl extends GenericServiceImpl<Institution, InstitutionRepository> implements InstitutionService<Institution> {


    @Autowired
    public InstitutionServiceImpl(InstitutionRepository repository) {
        super(repository);
    }

    @Override
    public Institution findByName(String name) {
        return repository.findByName(name);
    }
}
