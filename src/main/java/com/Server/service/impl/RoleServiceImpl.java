package com.Server.service.impl;

import com.Server.dto.Request.RoleRequest;
import com.Server.dto.Response.RoleResponse;
import com.Server.mapper.Mapper;
import com.Server.entiy.Role;
import com.Server.entiy.Roles;
import com.Server.repository.RoleRepository;
import com.Server.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Class Service implements interface RoleService.
 *
 * @author Krystian Cwioro Kamil Bieniasz Damian Mierzynski.
 * @version 2.0
 * @since 2020-12-29.
 */

@Service
@Transactional
public class RoleServiceImpl implements RoleService {

    /**
     * roleRepository
     */
    private final RoleRepository roleRepository;
    /**
     * role mapper
     */
    private final Mapper<Role, RoleResponse, RoleRequest> roleMapper;

    @Autowired
    /**Constructor*/
    public RoleServiceImpl(RoleRepository roleRepository, Mapper<Role, RoleResponse, RoleRequest> roleMapper) {
        this.roleRepository = roleRepository;
        this.roleMapper = roleMapper;
    }

    /**
     * Find role on name.
     *
     * @param name name of role.
     * @return role data.
     */
    @Override
    public RoleResponse findByName(Roles name) {
        return roleMapper.toDto(roleRepository.findByName(name).get());
    }

    /**
     * Find role on id.
     *
     * @param id role.
     * @return role data.
     */
    @Override
    public RoleResponse findById(int id) {
        return roleMapper.toDto(roleRepository.findById(id).get());
    }

    /**
     * Method save new role.
     *
     * @param roleRequest data of new role.
     * @return data new role.
     */
    @Override
    public void save(RoleRequest roleRequest) {
         roleRepository.save(roleMapper.toEntity(roleRequest));
    }

    /**
     * Method return List of all role.
     *
     * @return List role.
     */
    @Override
    public List<RoleResponse> findAll() {
        return roleRepository.findAll().parallelStream().map(role -> roleMapper.toDto(role)).collect(Collectors.toList());
    }
}
