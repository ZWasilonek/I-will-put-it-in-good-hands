package pl.coderslab.charity.mapper;

import org.junit.Test;
import org.mapstruct.factory.Mappers;
import pl.coderslab.charity.dto.CategoryDTO;
import pl.coderslab.charity.entity.Category;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class CategoryMapperTest {

    CategoryMapper mapper = Mappers.getMapper(CategoryMapper.class);

    @Test
    public void givenCategoryDTO_withDiffName_toCategory_whenMaps_thenCorrect() {
        CategoryDTO dto = new CategoryDTO();
        dto.setId(1L);
        dto.setName("zabawki");

        Category entity = mapper.mapToEntity(dto);

        assertEquals(dto.getId(), entity.getId());
        assertEquals(dto.getName(), entity.getName());
    }

    @Test
    public void givenCategory_withDiffName_toCategoryDTO_whenMaps_thenCorrect() {
        Category entity = new Category();
        entity.setId(1L);
        entity.setName("zabawki");

        CategoryDTO dto = mapper.mapToDTO(entity);

        assertNotNull(entity);
        assertNotNull(dto);
        assertEquals(dto.getId(), entity.getId());
        assertEquals(dto.getName(), entity.getName());
    }

}