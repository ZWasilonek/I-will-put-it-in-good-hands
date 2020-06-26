package pl.coderslab.charity.mapper;

import org.junit.Test;
import org.mapstruct.factory.Mappers;
import pl.coderslab.charity.dto.AuthorityDTO;
import pl.coderslab.charity.entity.Authority;
import pl.coderslab.charity.entity.generic.AuthorityType;

import java.util.Set;

import static org.junit.Assert.assertEquals;

public class AuthorityMapperTest {

    AuthorityMapper mapper = Mappers.getMapper(AuthorityMapper.class);

    @Test
    public void givenAuthority_withDiffName_toAuthorityDTO_whenMaps_thenCorrect() {
        Authority entity = getAuthorityEntity();
        AuthorityDTO dto = mapper.mapToDTO(entity);

        assertEquals(dto.getId(), entity.getId());
        assertEquals(dto.getRoleName(), entity.getName().name());
    }

    @Test
    public void givenAuthorityDTO_withDiffName_toAuthority_whenMaps_thenCorrect() {
        AuthorityDTO dto = getAuthorityDTO();
        Authority entity = mapper.mapToEntity(dto);

        assertEquals(dto.getId(), entity.getId());
        assertEquals(dto.getRoleName(), entity.getName().name());
    }

    @Test
    public void givenAuthorities_withDiffName_toAuthoritiesDTO_whenMaps_thenCorrect() {
        Authority entity = getAuthorityEntity();
        Set<Authority> entities = Set.of(entity);
        Set<AuthorityDTO> authorityDTOS = (Set<AuthorityDTO>) mapper.mapAuthoritiesToDTO(entities);
        AuthorityDTO authorityDTO = authorityDTOS.stream().findFirst().get();
        assertEquals(authorityDTO.getRoleName(), entity.getName().name());
    }

    @Test
    public void givenAuthoritiesDTO_withDiffName_toAuthorities_whenMaps_thenCorrect() {
        AuthorityDTO dto = getAuthorityDTO();
        Set<AuthorityDTO> authorityDTOs = Set.of(dto);
        Set<Authority> entities = mapper.mapAuthoritiesToEntities(authorityDTOs);
        Authority entity = entities.iterator().next();
        assertEquals(entity.getId(), dto.getId());
        assertEquals(entity.getName().name(), dto.getRoleName());
    }

    private Authority getAuthorityEntity() {
        Authority authority = new Authority();
        authority.setId(1L);
        authority.setName(AuthorityType.ROLE_USER);
        return authority;
    }

    private AuthorityDTO getAuthorityDTO() {
        AuthorityDTO authorityDTO = new AuthorityDTO();
        authorityDTO.setId(1L);
        authorityDTO.setRoleName("ROLE_USER");
        return authorityDTO;
    }

}