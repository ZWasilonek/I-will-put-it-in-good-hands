package pl.coderslab.charity.service;

import pl.coderslab.charity.entity.Category;
import pl.coderslab.charity.service.generic.GenericService;

public interface CategoryService<T> extends GenericService<T> {

    Category findByName(String name);
}
