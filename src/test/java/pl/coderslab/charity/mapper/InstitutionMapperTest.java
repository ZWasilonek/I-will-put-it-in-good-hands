package pl.coderslab.charity.mapper;

import org.junit.Test;
import org.mapstruct.factory.Mappers;
import pl.coderslab.charity.dto.InstitutionDTO;
import pl.coderslab.charity.entity.Institution;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class InstitutionMapperTest {

    InstitutionMapper mapper = Mappers.getMapper(InstitutionMapper.class);

    @Test
    public void givenInstitutionDTOwithDiffNametoInstitution_whenMaps_thenCorrect() {
        InstitutionDTO dto = new InstitutionDTO(1L, "Fundacja do TESTU", "Test description");
        Institution entity = mapper.mapToEntity(dto);

        assertEquals(dto.getId(), entity.getId());
        assertEquals(dto.getName(), entity.getName());
        assertEquals(dto.getDescription(), entity.getDescription());
    }

    @Test
    public void givenInstitutionwithDiffNametoInstitytionDTO_whenMaps_thenCorrect() {
        Institution entity = new Institution("Fundacja do TESTU", "Test description");
        entity.setId(1L);
        InstitutionDTO dto = mapper.mapToDTO(entity);

        assertNotNull(entity);
        assertNotNull(dto);
        assertEquals(dto.getId(), entity.getId());
        assertEquals(dto.getName(), entity.getName());
    }

}