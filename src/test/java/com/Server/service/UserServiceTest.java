package com.Server.service;

import com.Server.dto.Request.UserRequest;
import com.Server.dto.Response.CarResponse;
import com.Server.dto.Response.RoleResponse;
import com.Server.dto.Response.UserResponse;
import com.Server.entiy.Role;
import com.Server.entiy.Roles;
import com.Server.entiy.User;
import org.junit.Assert;
import org.junit.jupiter.api.*;
import org.mockito.internal.matchers.apachecommons.ReflectionEquals;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestMethodOrder(value = MethodOrderer.OrderAnnotation.class)
class UserServiceTest {

    @Autowired
    private UserService userService;
    private List<UserResponse> userResponses = new ArrayList<>();

    @BeforeEach
    void setUp() {
        UserResponse userResponse1 = UserResponse
                .builder()
                .id(1L)
                .username("user1")
                .email("user1@example.com")
                .roles(List.of(RoleResponse
                        .builder()
                        .id(1)
                        .name("ROLE_ADMIN")
                        .build()))
                .build();

        UserResponse userResponse2 = UserResponse
                .builder()
                .id(2L)
                .username("user3")
                .email("user3@example.com")
                .roles(List.of(RoleResponse
                        .builder()
                        .id(1)
                        .name("ROLE_ADMIN")
                        .build()))
                .build();

        userResponses.add(userResponse1);
        userResponses.add(userResponse2);
    }

    @Test
    @DisplayName("save new user")
    @Timeout(1)
    @Order(value = 1)
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
    @DisplayName("update user")
    @Timeout(1)
    @Order(value = 2)
    void update() {
        UserRequest userRequest = UserRequest
                .builder()
                .username("user3")
                .email("user3@example.com")
                .role(Set.of("admin"))
                .password("user123")
                .build();

        UserResponse userResponse = UserResponse
                .builder()
                .id(2L)
                .username("user3")
                .email("user3@example.com")
                .roles(List.of(RoleResponse
                        .builder()
                        .id(1)
                        .name("ROLE_ADMIN")
                        .build()))
                .build();

        userService.update(userRequest, 2L);

        Assert.assertTrue(new ReflectionEquals(userService.findById(2L)).matches(userResponse));
    }

    @Test
    @DisplayName("find user by id")
    @Timeout(1)
    @Order(value = 3)
    void findById() {
        UserResponse userResponse = UserResponse
                .builder()
                .id(1L)
                .username("user1")
                .email("user1@example.com")
                .roles(List.of(RoleResponse
                        .builder()
                        .id(1)
                        .name("ROLE_ADMIN")
                        .build()))
                .build();

        userResponses.add(userResponse);

        System.out.println(userService.findById(1L));
        Assert.assertTrue(new ReflectionEquals(userResponse).matches(userService.findById(1L)));
    }

    @Test
    @DisplayName("Find all users")
    @Timeout(1)
    @Order(value = 4)
    void findAll() {
        System.out.println(userService.findAll());
        Assert.assertArrayEquals(userService.findAll().toArray(), userResponses.toArray());
    }

    @Test
    void getReservationUser() {
    }

    @Test
    void deleteUser() {
        userService.deleteUser(2L);

        userResponses.remove(1);

        Assert.assertArrayEquals(userService.findAll().toArray(), userResponses.toArray());
    }

    @Test
    void findByReservationsIdRent() {
    }
}