package com.Server.mapper.impl;

import com.Server.dto.Request.UserRequest;
import com.Server.dto.Response.RoleResponse;
import com.Server.dto.Response.UserResponse;
import com.Server.mapper.Mapper;
import com.Server.entiy.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

/**
 * Class Mapper use to mapping user.
 *
 * @author Krystian Cwioro Kamil Bieniasz Damian Mierzynski.
 * @version 1.0
 * @since 2020-04-27
 */

@Component
public class UserMapper implements Mapper<User, UserResponse, UserRequest> {

    /**
     * @param user param mapped to dto
     * @return dto object
     */
    @Override
    public UserResponse toDto(User user) {
        return new UserResponse().builder()
                .id(user.getId())
                .username(user.getUsername())
                .email(user.getEmail())
                .roles(user.getRoles().parallelStream().map(role -> new RoleResponse().builder().id(role.getId()).name(role.getName().toString()).build()).collect(Collectors.toList()))
                .build();

    }

    /**
     * @param userRequest param mapped to entity
     * @return entity object
     */
    @Override
    public User toEntity(UserRequest userRequest) {
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        return new User().builder()
                .username(userRequest.getUsername())
                .email(userRequest.getEmail())
                .password(passwordEncoder.encode(userRequest.getPassword()))
                .build();
    }

    /**
     * @param user        old param
     * @param userRequest new param
     * @return new localization object.
     */
    @Override
    public User update(User user, UserRequest userRequest) {
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        user.setUsername(userRequest.getUsername());
        user.setPassword(passwordEncoder.encode(userRequest.getPassword()));
        user.setEmail(userRequest.getEmail());
        return user;
    }
}
