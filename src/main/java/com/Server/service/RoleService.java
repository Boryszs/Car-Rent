package com.Server.service;

import com.Server.dto.Request.RoleRequest;
import com.Server.dto.Response.RoleResponse;
import com.Server.entiy.Roles;

import java.util.List;

/**
 * Interface Service role to service RoleRepository.
 * @author Krystian Cwioro Kamil Bieniasz Damian Mierzynski.
 * @version 1.0
 * @since 2020-12-29.
 */


public interface RoleService {
    /**
     * Find role on name.
     * @param name name of role.
     * @return role data.
     */
    RoleResponse findByName(Roles name);

    /**
     * Find role on id.
     * @param id id role.
     * @return role data.
     */
    RoleResponse findById(int id);

    /**
     * Method return List of all role.
     * @return List role.
     */
    List<RoleResponse> findAll();
}
