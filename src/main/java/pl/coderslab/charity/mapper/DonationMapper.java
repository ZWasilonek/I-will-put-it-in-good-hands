package pl.coderslab.charity.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import pl.coderslab.charity.dto.DonationDTO;
import pl.coderslab.charity.entity.Donation;

import java.util.Set;

@Mapper(uses = {CategoryMapper.class}, componentModel = "spring")
public interface DonationMapper {

    @Mappings({
            @Mapping(target = "id", source = "entity.id"),
            @Mapping(target = "bagsQuantity", source = "entity.bagsQuantity"),
            @Mapping(target = "categories", source = "entity.categories"),
            @Mapping(target = "institution", source = "entity.institution"),
            @Mapping(target = "shippingAddress", source = "entity.shippingAddress")
    })
    DonationDTO mapToDTO(Donation entity);

    @Mappings({
            @Mapping(target = "id", source = "dto.id"),
            @Mapping(target = "bagsQuantity", source = "dto.bagsQuantity"),
            @Mapping(target = "categories", source = "dto.categories"),
            @Mapping(target = "institution", source = "dto.institution"),
            @Mapping(target = "shippingAddress", source = "dto.shippingAddress")
    })
    Donation mapToEntity(DonationDTO dto);

    Set<Donation> setDonationsToEntities(Set<DonationDTO> dtoSet);
    Set<DonationDTO> setDonationsToDTO(Set<Donation> entities);

}