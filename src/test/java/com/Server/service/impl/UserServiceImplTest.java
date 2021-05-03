package com.Server.service.impl;

import com.Server.exception.WrongDataException;
import com.Server.service.UserService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static java.util.concurrent.TimeUnit.SECONDS;
import static org.junit.jupiter.api.Assertions.*;
/**
 *  User service test
 */
@SpringBootTest
class UserServiceImplTest {

    @Autowired
    UserService userService;

    @Test
    @DisplayName("---- FIND BY USERNAME ----")
    void findByUsername() {
        userService.findAll().forEach(userResponse -> assertEquals(userService.findByUsername(userResponse.getUsername()).hashCode(), userResponse.hashCode()));
    }

    @Test
    @DisplayName("---- EXIST BY USERNAME ----")
    void existsByUsername() {
        userService.findAll().forEach(userResponse -> assertTrue(userService.existsByUsername(userResponse.getUsername())));
    }

    @Test
    @DisplayName("---- EXIST BY EMAIL USERNAME ----")
    void existsByEmail() {
        userService.findAll().forEach(userResponse -> assertTrue(userService.existsByEmail(userResponse.getUsername())));
    }

    @Test
    @DisplayName("---- FIND BY EMAIL USERNAME ----")
    void findByEmail() {
        userService.findAll().forEach(userResponse -> assertEquals(userService.findByEmail(userResponse.getEmail()).hashCode(),userResponse.hashCode()));
    }

    @Test
    @DisplayName("---- FIND BY ID USERNAME ----")
    void findById() {
        userService.findAll().forEach(userResponse -> {
            try {
                assertEquals(userService.findById(userResponse.getId()).hashCode(),userResponse.hashCode());
            } catch (WrongDataException wrongDataException) {
                wrongDataException.printStackTrace();
            }
        });

        assertThrows(WrongDataException.class, () -> userService.findById(-1L));
    }

    @Test
    @DisplayName("---- GET ALL USERNAME ----")
    @Timeout(value = 1,unit = SECONDS)
    void findAll() {
        userService.findAll();
    }

    @Test
    @DisplayName("---- EXIST BY ID USERNAME ----")
    void existsById() {
        userService.findAll().forEach(userResponse -> assertTrue(userService.existsById(userResponse.getId())));

    }
}
