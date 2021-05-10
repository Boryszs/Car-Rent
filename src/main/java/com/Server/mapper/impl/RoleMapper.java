package com.Server.mapper.impl;

import com.Server.dto.Request.RoleRequest;
import com.Server.dto.Response.RoleResponse;
import com.Server.entiy.Role;
import com.Server.entiy.Roles;
import com.Server.mapper.Mapper;
import org.springframework.stereotype.Component;

/**
 * Class Mapper use to mapping Role.
 * @author Krystian Cwioro Kamil Bieniasz Damian Mierzynski.
 * @version 1.0
 * @since 2020-04-27
 */

@Component
public class RoleMapper implements Mapper<Role, RoleResponse, RoleRequest> {

    /**
     * @param role param mapped to dto
     * @return dto object
     */
    @Override
    public RoleResponse toDto(Role role) {
        return new RoleResponse().builder()
                .id(role.getId())
                .name(role.getName().toString())
                .build();
    }

    /**
     * @param roleRequest param mapped to entity
     * @return entity object
     */
    @Override
    public Role toEntity(RoleRequest roleRequest) {
        return new Role().builder()
                .name(Roles.valueOf(roleRequest.getName()))
                .build();
    }

    /**
     * @param role        old param
     * @param roleRequest new param
     * @return new localization object.
     */
    @Override
    public Role update(Role role, RoleRequest roleRequest) {
         role.setName(Roles.valueOf(roleRequest.getName()));
         return role;
    }
}
