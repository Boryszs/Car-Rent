package com.Server.service.impl;

import com.Server.model.Role;
import com.Server.model.Roles;
import com.Server.repository.RoleRepository;
import com.Server.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

/**
 * Class Service implements interface RoleService.
 * @author Krystian Cwioro Kamil Bieniasz Damian Mierzynski.
 * @version 1.0
 * @since 2020-12-29.
 */

@Service
@Transactional
public class RoleServiceImpl implements RoleService {

    /**roleRepository*/
    private RoleRepository roleRepository;

    @Autowired
    /**Constructor*/
    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    /**
     * Find role on name.
     * @param name name of role.
     * @return role data.
     */
    @Override
    public Optional<Role> findByName(Roles name) {
        return roleRepository.findByName(name);
    }

    /**
     * Find role on id.
     * @param id role.
     * @return role data.
     */
    @Override
    public Optional<Role> findById(long id) {
        return roleRepository.findById(id);
    }

    /**
     * Method save new role.
     * @param role data of new role.
     * @return data new role.
     */
    @Override
    public Role save(Role role) {
        return roleRepository.save(role);
    }

    /**
     * Method return List of all role.
     * @return List role.
     */
    @Override
    public List<Role> findAll() {
        return roleRepository.findAll();
    }
}
