package com.Server.service;

import com.Server.dto.Request.RegisterRequest;
import com.Server.exception.ExceptionRequest;
import com.Server.model.Reservation;
import com.Server.model.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    Optional<User> findByUsername(String username);

    Boolean existsByUsername(String username);

    List<Reservation> getReservationUser(Long id) throws ExceptionRequest;

    Boolean existsByEmail(String email);

    Optional<User> findByEmail(String email);

    Optional<User> findById(Long id) throws ExceptionRequest;

    User save(RegisterRequest user) throws ExceptionRequest;

    List<User> findAll();

    User findByReservations_Idrent(Long id);

    Boolean existsById(long id);
}
