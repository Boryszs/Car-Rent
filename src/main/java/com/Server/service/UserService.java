package com.Server.service;

import com.Server.dto.Request.EditUser;
import com.Server.dto.Request.RegisterRequest;
import com.Server.exception.ExceptionRequest;
import com.Server.model.Reservation;
import com.Server.model.User;

import java.util.List;
import java.util.Optional;

/**
 * Interface Service user to service UserRepository.
 * @author Krystian Cwioro Kamil Bieniasz Damian Mierzynski.
 * @version 1.0
 * @since 2020-12-29.
 */


public interface UserService {
    /**
     * Find the user on username.
     * @param username username on user.
     * @return data on user.
     */
    Optional<User> findByUsername(String username);

    /**
     * Check whether user exist on username.
     * @param username username on user.
     * @return true or false.
     */
    Boolean existsByUsername(String username);

    /**
     * Return reservation user.
     * @param id id user.
     * @return List reservation.
     * @throws ExceptionRequest when id user is wrong.
     */
    List<Reservation> getReservationUser(Long id) throws ExceptionRequest;

    /**
     * Delete user on id.
     * @param id id user.
     * @throws ExceptionRequest when id user is wrong.
     */
    void deleteUser(Long id) throws ExceptionRequest;

    /**
     * Update user.
     * @param user new user data.
     * @return return new data on user.
     */
    User update(User user);

    /**
     * Update user.
     * @param editUser new user data.
     * @return return new data on user.
     * @throws ExceptionRequest when request data user is wrong.
     */
    User update(EditUser editUser) throws ExceptionRequest;

    /**
     * Check whether user exist on email.
     * @param email email on user.
     * @return true or false.
     */
    Boolean existsByEmail(String email);

    /**
     * Find user on email.
     * @param email email user.
     * @return data user.
     */
    Optional<User> findByEmail(String email);

    /**
     * Find user on id.
     * @param id is user.
     * @return user data
     * @throws ExceptionRequest when id is wrong
     */
    Optional<User> findById(Long id) throws ExceptionRequest;

    /**
     * Save new user data.
     * @param user user data.
     * @return new data user.
     * @throws ExceptionRequest when request data user register is wrong.
     */
    User save(RegisterRequest user) throws ExceptionRequest;

    /**
     * Get all user.
     * @return List of all user.
     */
    List<User> findAll();

    /**
     * Find Reservation user on id.
     * @param id is reservation user.
     * @return User data with list reservation.
     */
    User findByReservationsIdRent(Long id);

    /**
     * Check whether user on id exist.
     * @param id id user.
     * @return true or false.
     */
    Boolean existsById(long id);
}
