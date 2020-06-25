package pl.coderslab.charity.mapper;

import org.mapstruct.Mapper;
import pl.coderslab.charity.dto.InstitutionDTO;
import pl.coderslab.charity.entity.Institution;

import java.util.Set;

@Mapper(componentModel = "spring")
public interface InstitutionMapper {

    Institution mapToEntity(InstitutionDTO dto);
    InstitutionDTO mapToDTO(Institution entity);

    Set<InstitutionDTO> setInstitutionsToDTO(Set<Institution> entities);
    Set<Institution> setInstitutionsToEntities(Set<InstitutionDTO> dtoSet);

}