package pl.coderslab.charity.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import pl.coderslab.charity.dto.UserDTO;
import pl.coderslab.charity.entity.User;

import java.util.Set;

@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mappings({
            @Mapping(target = "id", source = "dto.id"),
            @Mapping(target = "firstName", source = "dto.firstName"),
            @Mapping(target = "lastName", source = "dto.lastName"),
            @Mapping(target = "password", source = "dto.password"),
            @Mapping(target = "email", source = "dto.email"),
            @Mapping(target = "authorities", source = "dto.authorities"),
    })
    User mapToEntity(UserDTO dto);

    @Mappings({
            @Mapping(target = "id", source = "entity.id"),
            @Mapping(target = "firstName", source = "entity.firstName"),
            @Mapping(target = "lastName", source = "entity.lastName"),
            @Mapping(target = "password", source = "entity.password"),
            @Mapping(target = "email", source = "entity.email"),
            @Mapping(target = "authorities", source = "entity.authorities"),
    })
    UserDTO mapToDTO(User entity);

    Set<UserDTO> setUsersToDTO(Set<User> entities);
    Set<User> setUsersToEntities(Set<UserDTO> dtoSet);

}