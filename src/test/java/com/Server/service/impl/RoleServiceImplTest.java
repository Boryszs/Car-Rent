package com.Server.service.impl;

import com.Server.entiy.Roles;
import com.Server.service.RoleService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static java.util.concurrent.TimeUnit.MILLISECONDS;
import static org.junit.jupiter.api.Assertions.assertEquals;
/**
 *  Role service test
 */
@SpringBootTest
class RoleServiceImplTest {

    @Autowired
    RoleService roleService;

    @Test
    @DisplayName("---- FIND BY NAME ROLE ----")
    void findByName() {
        roleService.findAll().forEach(roleResponse -> assertEquals(roleService.findByName(Roles.valueOf(roleResponse.getName())).hashCode(), roleResponse.hashCode()));
    }

    @Test
    @DisplayName("---- FIND BY ID ROLE ----")
    void findById() {
        roleService.findAll().forEach(roleResponse -> assertEquals(roleService.findById(roleResponse.getId()).hashCode(), roleResponse.hashCode()));
    }

    @Test
    @DisplayName("---- FIND ALL ROLE ----")
    @Timeout(value = 500 ,unit = MILLISECONDS)
    void findAll() {
        roleService.findAll();
    }
}
