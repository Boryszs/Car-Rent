package com.Server.service;

import com.Server.model.Role;
import com.Server.model.Roles;

import java.util.List;
import java.util.Optional;

public interface RoleService {
    Optional<Role> findByName(Roles name);

    Optional<Role> findById(long id);

    Role save(Role role);

    List<Role> findAll();
}
