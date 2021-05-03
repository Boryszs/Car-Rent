package com.Server.repository;

import com.Server.entiy.Reservation;
import com.Server.entiy.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

/**
 * Interface repository user to available connect on table database.
 *
 * @author Krystian Cwioro Kamil Bieniasz Damian Mierzynski.
 * @version 1.0
 * @since 2020-12-29.
 */


@Transactional
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    /**
     * Find the user on username.
     *
     * @param username username on user.
     * @return data on user.
     */
    Optional<User> findByUsername(String username);

    /**
     * Check whether user exist on username.
     *
     * @param username username on user.
     * @return true or false.
     */
    Boolean existsByUsername(String username);

    /**
     * Check whether user exist on email.
     *
     * @param email email on user.
     * @return true or false.
     */
    Boolean existsByEmail(String email);

    /**
     * Find user on email.
     *
     * @param email email user.
     * @return data user.
     */
    Optional<User> findByEmail(String email);

    /**
     * Delete user on id.
     *
     * @param id id user.
     */
    void deleteById(Long id);

    /**
     * Find Reservation user on id.
     *
     * @param id is reservation user.
     * @return User data with list reservation.
     */
    User findByReservations_Idrent(Long id);

    /**
     * Check whether user on id exist.
     *
     * @param id id user.
     * @return true or false.
     */
    Boolean existsById(long id);

    /**
     * Find user on id.
     *
     * @param id is user.
     * @return user data
     */
    Optional<User> findById(Long id);

    /**
     * Method find all reservation user
     * @param id id user
     * @return reservation user on id
     */
    @Query("SELECT r FROM Reservation r WHERE r.user.id =:id")
    List<Reservation> getReservationUser(@Param("id") Long id);


}
