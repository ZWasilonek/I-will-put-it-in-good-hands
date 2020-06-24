package pl.coderslab.charity.mapper;

import org.junit.Test;
import org.mapstruct.factory.Mappers;
import pl.coderslab.charity.dto.AuthorityDTO;
import pl.coderslab.charity.entity.Authority;
import pl.coderslab.charity.entity.generic.AuthorityType;

import static org.junit.Assert.assertEquals;

public class AuthorityMapperTest {

    AuthorityMapper mapper = Mappers.getMapper(AuthorityMapper.class);

    @Test
    public void givenAuthority_withDiffName_toAuthorityDTO_whenMaps_thenCorrect() {
        Authority entity = new Authority();
        entity.setName(AuthorityType.ROLE_USER);
        entity.setId(1L);

        AuthorityDTO dto = mapper.mapToDTO(entity);

        assertEquals(dto.getId(), entity.getId());
        assertEquals(dto.getRoleName(), entity.getName().name());
    }

    @Test
    public void givenAuthorityDTO_withDiffName_toAuthority_whenMaps_thenCorrect() {
        AuthorityDTO dto = new AuthorityDTO();
        dto.setId(1L);
        dto.setRoleName("ROLE_USER");

        Authority entity = mapper.mapToEntity(dto);

        assertEquals(dto.getId(), entity.getId());
        assertEquals(dto.getRoleName(), entity.getName().name());
    }

}