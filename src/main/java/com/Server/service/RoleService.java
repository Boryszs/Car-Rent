package com.Server.service;

import com.Server.model.Role;
import com.Server.model.Roles;

import java.util.List;
import java.util.Optional;

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
    Optional<Role> findByName(Roles name);

    /**
     * Find role on id.
     * @param id id role.
     * @return role data.
     */
    Optional<Role> findById(long id);

    /**
     * Method save new role.
     * @param role data of new role.
     * @return data new role.
     */
    Role save(Role role);

    /**
     * Method return List of all role.
     * @return List role.
     */
    List<Role> findAll();
}
