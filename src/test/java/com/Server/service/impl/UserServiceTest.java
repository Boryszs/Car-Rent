package com.Server.service.impl;

import com.Server.dto.Request.UserRequest;
import com.Server.dto.Response.*;
import com.Server.entiy.Role;
import com.Server.entiy.Roles;
import com.Server.entiy.User;
import com.Server.service.UserService;
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
        ReservationResponse reservationResponse = ReservationResponse
                .builder()
                .idRent(1L)
                .carResponse(CarResponse
                        .builder()
                        .idcar(1)
                        .mark("Toyota")
                        .model("Yaris")
                        .type("hatchback")
                        .yearProduction(2018)
                        .color("czarny")
                        .engineCapacity(1200)
                        .money(79)
                        .image("https://image.ceneostatic.pl/data/products/66661051/i-toyota-yaris-ii-2008-87km-hatchback-czarny.jpg")
                        .localization(LocalizationResponse
                                .builder()
                                .id(1L)
                                .city("Tarnow")
                                .build())
                        .build())
                .dateTo("2022-02-26")
                .dateFrom("2022-01-05")
                .localizationEnd(LocalizationResponse
                        .builder()
                        .id(1L)
                        .city("Tarnow")
                        .build())
                .localizationStart(LocalizationResponse
                        .builder()
                        .id(1L)
                        .city("Tarnow")
                        .build())
                .price(158f)
                .build();

        System.out.println(userService.getReservationUser(1L));
        Assert.assertArrayEquals(userService.getReservationUser(1L).toArray(), List.of(reservationResponse).toArray());
    }

    @Test
    void deleteUser() {
        userService.deleteUser(2L);

        userResponses.remove(1);

        Assert.assertArrayEquals(userService.findAll().toArray(), userResponses.toArray());
    }

    @Test
    void findByReservationsIdRent() {
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

       Assert.assertTrue(new ReflectionEquals(userService.findByReservationsIdRent(1L)).matches(userResponse));
    }
}