package pl.coderslab.charity.service;

import pl.coderslab.charity.entity.Authority;
import pl.coderslab.charity.entity.generic.AuthorityType;
import pl.coderslab.charity.service.generic.GenericService;

public interface AuthorityService<T> extends GenericService<T> {
    Authority findByName(AuthorityType name);
}
