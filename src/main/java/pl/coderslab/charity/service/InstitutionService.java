package pl.coderslab.charity.service;

import pl.coderslab.charity.entity.Institution;
import pl.coderslab.charity.service.generic.GenericService;

public interface InstitutionService extends GenericService<Institution> {

    Institution findByName(String name);

}