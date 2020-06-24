package pl.coderslab.charity.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import pl.coderslab.charity.dto.CategoryDTO;
import pl.coderslab.charity.service.CategoryService;

@Component
public class CategoryConverter implements Converter<String, CategoryDTO> {

    CategoryService categoryService;
    CategoryMapper categoryMapper;

    @Autowired
    public CategoryConverter(CategoryService categoryService, CategoryMapper categoryMapper) {
        this.categoryService = categoryService;
        this.categoryMapper = categoryMapper;
    }

    public CategoryDTO convert(String source) {
        return categoryMapper.mapToDTO(categoryService.findById(Long.valueOf(source)));
    }

}