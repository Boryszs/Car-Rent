package com.Server.repository;

import com.Server.entiy.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Interface repository reservation to available connect on table database.
 * @author Krystian Cwioro Kamil Bieniasz Damian Mierzynski.
 * @version 1.0
 * @since 2020-12-29.
 */

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Integer> {
    /**
     * Find reservation on id
     * @param id id reservation.
     * @return reservation data.
     */
    Optional<Reservation> findByIdrent(Long id);

    /**
     * Check whether reservation on id car exist.
     * @param id id car.
     * @return true or false.
     */
    boolean existsByCar_Idcar(int id);

    /**
     * Check whether reservation on id exist.
     * @param id id reservation.
     * @return true or false.
     */
    boolean existsByIdrent(Long id);

    @Query("SELECT r FROM Reservation r WHERE r.user.id =:id and r.dataFrom <= CURRENT_TIMESTAMP and r.dataTo >= CURRENT_TIMESTAMP ")
    List<Reservation> findCurrent(@Param("id") Long id);
    /**
     * Delete reservation on id.
     * @param id id reservation.
     * @return return int on id delete reservation.
     */
    int deleteByIdrent(Long id);

    /**
     * find a reservation on id car
     * @param id id car
     * @return return List Reservation.
     */
    List<Reservation> findByCar_Idcar(int id);

    /**
     * Find the Last reservation on car
     * @param id id car.
     * @return data reservation.
     */
    Optional<Reservation> findFirstByCarIdcarOrderByIdrentDesc(int id);

}
