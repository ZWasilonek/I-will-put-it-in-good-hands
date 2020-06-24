package pl.coderslab.charity.service;

import pl.coderslab.charity.entity.Authority;
import pl.coderslab.charity.entity.generic.AuthorityType;
import pl.coderslab.charity.service.generic.GenericService;

public interface AuthorityService extends GenericService<Authority> {

    Authority findByName(AuthorityType name);

}