package com.Server.service.impl;

import com.Server.dto.Response.RoleResponse;
import com.Server.entiy.Roles;
import com.Server.service.RoleService;
import org.junit.Assert;
import org.junit.jupiter.api.*;
import org.mockito.internal.matchers.apachecommons.ReflectionEquals;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.groups.Tuple.tuple;

@SpringBootTest
@TestMethodOrder(value = MethodOrderer.OrderAnnotation.class)
class RoleServiceImplTest {

    @Autowired
    private RoleService roleService;

    @Test
    @DisplayName("Find role user by name")
    @Timeout(1)
    @Order(value = 1)
    void findByName() {
        Assert.assertTrue(new ReflectionEquals(
                RoleResponse.builder()
                        .id(2)
                        .name("ROLE_USER")
                        .build()
        ).matches(roleService.findByName(Roles.valueOf("ROLE_USER"))));
    }

    @Test
    @DisplayName("Find role user by id")
    @Timeout(1)
    @Order(value = 2)
    void findById() {
        Assert.assertTrue(new ReflectionEquals(
                RoleResponse.builder()
                        .id(2)
                        .name("ROLE_USER")
                        .build()
        ).matches(roleService.findByName(Roles.valueOf("ROLE_USER"))));
    }


    @Test
    @DisplayName("Find all role")
    @Timeout(1)
    @Order(value = 4)
    void findAll() {
        org.assertj.core.api.Assertions.assertThat(roleService.findAll())
                .extracting(RoleResponse::getId, RoleResponse::getName)
                .containsExactly(
                        tuple(1, "ROLE_ADMIN"),
                        tuple(2, "ROLE_USER")
                );

    }
}
