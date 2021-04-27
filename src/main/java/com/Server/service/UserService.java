package com.Server.service;

import com.Server.dto.Request.EditUser;
import com.Server.dto.Request.RegisterRequest;
import com.Server.exception.WrongDataException;
import com.Server.model.Reservation;
import com.Server.model.User;

import java.util.List;
import java.util.Optional;

/**
 * Interface Service user to service UserRepository.
 * @author Krystian Cwioro Kamil Bieniasz Damian Mierzynski.
 * @version 2.0.
 * @since 2020-04-27.
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
     * @throws WrongDataException when id user is wrong.
     */
    List<Reservation> getReservationUser(Long id) throws WrongDataException;

    /**
     * Delete user on id.
     * @param id id user.
     * @throws WrongDataException when id user is wrong.
     */
    void deleteUser(Long id) throws WrongDataException;

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
     * @throws WrongDataException when request data user is wrong.
     */
    User update(EditUser editUser) throws WrongDataException;

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
     * @throws WrongDataException when id is wrong
     */
    Optional<User> findById(Long id) throws WrongDataException;

    /**
     * Save new user data.
     * @param user user data.
     * @return new data user.
     * @throws WrongDataException when request data user register is wrong.
     */
    User save(RegisterRequest user) throws WrongDataException;

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
