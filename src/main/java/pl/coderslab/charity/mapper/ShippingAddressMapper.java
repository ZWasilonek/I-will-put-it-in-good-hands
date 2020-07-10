package pl.coderslab.charity.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import pl.coderslab.charity.dto.ShippingAddressDTO;
import pl.coderslab.charity.entity.ShippingAddress;

@Mapper(componentModel = "spring")
public interface ShippingAddressMapper {

    @Mappings({
            @Mapping(target = "id", source = "entity.id"),
            @Mapping(target = "street", source = "entity.street"),
            @Mapping(target = "city", source = "entity.city"),
            @Mapping(target = "zipCode", source = "entity.zipCode"),
            @Mapping(target = "pickUpDate", source = "entity.pickUpDate"),
            @Mapping(target = "pickUpTime", source = "entity.pickUpTime"),
            @Mapping(target = "pickUpComment", source = "entity.pickUpComment"),
            @Mapping(target = "phoneNumber", source = "entity.phoneNumber"),
    })
    ShippingAddressDTO mapToDTO(ShippingAddress entity);

    @Mappings({
            @Mapping(source = "dto.id", target = "id"),
            @Mapping(source = "dto.street", target = "street"),
            @Mapping(source = "dto.city", target = "city"),
            @Mapping(source = "dto.zipCode", target = "zipCode"),
            @Mapping(source = "dto.pickUpDate", target = "pickUpDate"),
            @Mapping(source = "dto.pickUpTime", target = "pickUpTime"),
            @Mapping(source = "dto.pickUpComment", target = "pickUpComment"),
            @Mapping(source = "dto.phoneNumber", target = "phoneNumber"),
    })
    ShippingAddress mapToEntity(ShippingAddressDTO dto);

}