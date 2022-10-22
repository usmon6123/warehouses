package uz.ataboyev.warehouse.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import uz.ataboyev.warehouse.entity.Role;
import uz.ataboyev.warehouse.payload.RoleDto;
import uz.ataboyev.warehouse.payload.RoleResDto;

@Mapper(componentModel = "spring")
public interface RoleMapper {

    @Mapping(target = "id",ignore = true)
    Role dtoToRole(RoleDto roleDto);

    @Mapping(target = "id",ignore = true)
    void updateRoleOutThePermissions(@MappingTarget Role role, RoleDto roleDto);

    RoleResDto roleToRseDto(Role role);

}
