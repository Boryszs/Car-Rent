package com.Server.service.impl;

import com.Server.dto.Response.UserResponse;
import com.Server.entiy.User;
import com.Server.exception.WrongDataException;
import com.Server.repository.UserRepository;
import com.Server.service.UserService;
import org.junit.jupiter.api.Assertions;
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
    @Autowired
    UserRepository userRepository;

    @Test
    @DisplayName("---- FIND BY USERNAME ----")
    void findByUsername() {
        userRepository.findAll().stream().map(user -> user.compareTo(userService.findByUsername(user.getUsername()).get())).forEach(Assertions::assertTrue);
    }

    @Test
    @DisplayName("---- EXIST BY USERNAME ----")
    void existsByUsername() {
        userService.findAll().forEach(userResponse -> assertTrue(userService.existsByUsername(userResponse.getUsername())));
    }

    @Test
    @DisplayName("---- EXIST BY EMAIL USERNAME ----")
    void existsByEmail() {
        for (UserResponse userResponse : userService.findAll()) {
            assertTrue(userService.existsByEmail(userResponse.getEmail()));
        }
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
