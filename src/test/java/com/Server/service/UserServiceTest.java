package com.Server.service;

import com.Server.dto.Request.UserRequest;
import com.Server.dto.Response.CarResponse;
import com.Server.dto.Response.RoleResponse;
import com.Server.dto.Response.UserResponse;
import com.Server.entiy.User;
import org.junit.Assert;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.mockito.internal.matchers.apachecommons.ReflectionEquals;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestMethodOrder(value = MethodOrderer.OrderAnnotation.class)
class UserServiceTest {

    @Autowired
    private UserService userService;


    @Test
    void findByUsername() {
    }

    @Test
    void getReservationUser() {
    }

    @Test
    void deleteUser() {
    }

    @Test
    void update() {
    }

    @Test
    void testUpdate() {
    }

    @Test
    void findByEmail() {
    }

    @Test
    void findById() {
        Assert.assertTrue(new ReflectionEquals(UserResponse
                .builder()
                .id(1L)
                .username("user1")
                .email("user1@example.com")
                .roles(List.of(RoleResponse
                        .builder()
                        .id(1)
                        .name("ROLE_ADMIN")
                        .build()))
                .build()
        ).matches(userService.findById(1L)));
    }

    @Test
    void save() {
        UserRequest userRequest = UserRequest.builder()
                .username("user2")
                .email("user2@example.com")
                .role(Set.of("ROLE_USER"))
                .password("user123")
                .build();

        userService.save(userRequest);

        List<UserResponse> users = userService.findAll();

        Assert.assertTrue(users.size() == 2);
    }

    @Test
    void findAll() {

    }

    @Test
    void findByReservationsIdRent() {
    }
}