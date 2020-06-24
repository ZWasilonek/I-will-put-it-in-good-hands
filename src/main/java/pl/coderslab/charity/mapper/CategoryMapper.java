package pl.coderslab.charity.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import pl.coderslab.charity.dto.CategoryDTO;
import pl.coderslab.charity.entity.Category;

import java.util.Set;

@Mapper(componentModel = "spring")
public interface CategoryMapper {

//    @Mappings({
//        @Mapping(target = "id", source = "entity.id"),
//        @Mapping(target = "name", source = "entity.name")
//    })
    CategoryDTO mapToDTO(Category entity);
//    @Mappings({
//        @Mapping(target = "id", source = "dto.id"),
//        @Mapping(target = "name", source = "dto.name")
//    })
    Category mapToEntity(CategoryDTO dto);

    Set<Category> setCategoriesToEntities(Set<CategoryDTO> dtoSet);
    Set<CategoryDTO> setCategoriesToDTO(Set<Category> entities);

}