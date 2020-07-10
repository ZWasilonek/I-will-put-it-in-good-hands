package pl.coderslab.charity.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import pl.coderslab.charity.dto.AuthorityDTO;
import pl.coderslab.charity.entity.Authority;
import java.util.Set;

@Mapper(componentModel = "spring")
public interface AuthorityMapper {

    @Mappings({
            @Mapping(source = "entity.id", target = "id"),
            @Mapping(source = "entity.name", target = "roleName")
    })
    AuthorityDTO mapToDTO(Authority entity);

    @Mappings({
            @Mapping(source = "dto.id", target = "id"),
            @Mapping(source = "dto.roleName", target = "name")
    })
    Authority mapToEntity(AuthorityDTO dto);

    Set<AuthorityDTO> mapAuthoritiesToDTO(Set<Authority> authorities);
    Set<Authority> mapAuthoritiesToEntities(Set<AuthorityDTO> dtoSet);

}