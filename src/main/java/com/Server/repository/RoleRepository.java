package com.Server.repository;

import com.Server.model.Roles;
import com.Server.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Interface repository role to available connect on table database.
 * @author Krystian Cwioro Kamil Bieniasz Damian Mierzynski.
 * @version 1.0
 * @since 2020-12-29.
 */

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
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
}
