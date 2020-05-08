package pl.coderslab.charity.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.coderslab.charity.entity.Category;
import pl.coderslab.charity.impl.generic.GenericServiceImpl;
import pl.coderslab.charity.repository.CategoryRepository;
import pl.coderslab.charity.service.CategoryService;

@Service
public class CategoryServiceImpl extends GenericServiceImpl<Category, CategoryRepository> implements CategoryService<Category> {

    @Autowired
    public CategoryServiceImpl(CategoryRepository repository) {
        super(repository);
    }

    @Override
    public Category findByName(String name) {
        return repository.findByName(name);
    }
}
