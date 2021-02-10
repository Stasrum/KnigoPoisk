package com.geekbrains.knigopoisk.dto.mappers;

import com.geekbrains.knigopoisk.dto.RoleDto;
import com.geekbrains.knigopoisk.entities.Role;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface RoleMapper {
    Role getRoleFromRoleDto(RoleDto roleDto);
    RoleDto getRoleDtoFromRole(Role role);

    List<Role> getRoleListFromRoleDtoList(List<RoleDto> roleDtoList);
    List<RoleDto> getRoleDtoListFromRoleList(List<Role> roleList);

}
