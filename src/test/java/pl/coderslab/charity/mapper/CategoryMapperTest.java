package pl.coderslab.charity.mapper;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mapstruct.factory.Mappers;
import org.springframework.test.context.junit4.SpringRunner;
import pl.coderslab.charity.dto.CategoryDTO;
import pl.coderslab.charity.entity.Category;

import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
public class CategoryMapperTest {

    CategoryMapper mapper = Mappers.getMapper(CategoryMapper.class);

    @Test
    public void givenCategoryDTO_withDiffName_toCategory_whenMaps_thenCorrect() {
        CategoryDTO dto = getCategoryDTO();
        Category entity = mapper.mapToEntity(dto);

        assertEquals(dto.getId(), entity.getId());
        assertEquals(dto.getName(), entity.getName());
    }

    @Test
    public void givenCategory_withDiffName_toCategoryDTO_whenMaps_thenCorrect() {
        Category entity = getCategoryEntity();
        CategoryDTO dto = mapper.mapToDTO(entity);

        assertNotNull(entity);
        assertNotNull(dto);
        assertEquals(dto.getId(), entity.getId());
        assertEquals(dto.getName(), entity.getName());
    }

    @Test
    public void givenCategories_withDiffName_toCategoriesDTO_whenMaps_thenCorrect() {
        Category entity = getCategoryEntity();
        Set<Category> entities = Set.of(entity);

        Set<CategoryDTO> categoryDTOS = mapper.setCategoriesToDTO(entities);
        CategoryDTO dto = categoryDTOS.iterator().next();
        assertEquals(entity.getId(), dto.getId());
        assertEquals(entity.getName(), dto.getName());
    }

    @Test
    public void givenCategoriesDTO_withDiffName_toCategories_whenMaps_thenCorrect() {
        CategoryDTO dto = getCategoryDTO();
        Set<CategoryDTO> categoryDTOS = Set.of(dto);
        Set<Category> entities = mapper.setCategoriesToEntities(categoryDTOS);
        Category entity = entities.iterator().next();
        assertEquals(entity.getId(), dto.getId());
        assertEquals(entity.getName(), dto.getName());
    }

    private Category getCategoryEntity() {
        Category category = new Category();
        category.setId(1L);
        category.setName("zabawki");
        return category;
    }

    private CategoryDTO getCategoryDTO() {
        CategoryDTO categoryDTO = new CategoryDTO();
        categoryDTO.setId(1L);
        categoryDTO.setName("zabawki");
        return categoryDTO;
    }

}