package pl.coderslab.charity.mapper;

import org.junit.Test;
import org.mapstruct.factory.Mappers;
import pl.coderslab.charity.dto.CategoryDTO;
import pl.coderslab.charity.dto.DonationDTO;
import pl.coderslab.charity.entity.Category;
import pl.coderslab.charity.entity.Donation;
import static org.assertj.core.api.Assertions.assertThat;


import java.util.*;

import static org.junit.Assert.assertEquals;

public class DonationMapperTest {

    DonationMapper mapper = Mappers.getMapper(DonationMapper.class);

    @Test
    public void givenDonation_withDiffBagsQuantity_toDonationDTO_whenMaps_thenCorrect() {
        Donation entity = new Donation();
        entity.setBagsQuantity(2);

        DonationDTO dto = mapper.mapToDTO(entity);

        assertEquals(dto.getBagsQuantity(), entity.getBagsQuantity());
    }

    @Test
    public void givenDonation_withDiffCategories_toDonationDTO_whenMaps_thenCorrect() {
        Donation entity = new Donation();
//        Set<Category> categories = new TreeSet<>() {{
//            add(new Category("zabawki"));
//            add(new Category("ubrania"));
//        }};
        Category category = new Category();
        category.setId(1L);
        category.setName("ubrania");
        entity.setCategories(Set.of(category));

        DonationDTO dto = mapper.mapToDTO(entity);
        Set<Category> categoriesEntity = entity.getCategories();
        Set<CategoryDTO> categoriesDTO = dto.getCategories();

        assertThat(categoriesDTO).isEqualTo(categoriesEntity);
    }

}