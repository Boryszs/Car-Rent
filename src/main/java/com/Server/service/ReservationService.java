package com.Server.service;

import com.Server.dto.Request.AddReservationRequest;
import com.Server.dto.Response.CarReservationResponse;
import com.Server.exception.ExceptionRequest;
import com.Server.model.Reservation;

import java.util.List;
import java.util.Optional;
/**
 * Interface Service reservation to service ReservationRepository.
 * @author Krystian Cwioro Kamil Bieniasz Damian Mierzynski.
 * @version 1.0
 * @since 2020-12-29.
 */

public interface ReservationService {
    /**
     * Find reservation on id
     * @param id id reservation.
     * @return reservation data.
     */
    Optional<Reservation> findByIdrent(Long id);

    /**
     * Check whether reservation on id exist.
     * @param id id reservation.
     * @return true or false.
     */
    boolean existsByIdrent(Long id);

    /**
     * Delete reservation on id.
     * @param id id reservation.
     * @return return int on id delete reservation.
     */
    int deleteByIdrent(Long id);

    /**
     * Return Current reservation user on id user.
     * @param id id user.
     * @return List current reservation.
     * @throws ExceptionRequest when user id not exist.
     */
    List<Reservation> getCurrentReservation(Long id) throws ExceptionRequest;

    /**
     * Delete reservation
     * @param id id reservation.
     * @return int id reservation.
     * @throws ExceptionRequest when reservation not exist.
     */
    Integer deleteReservation(Long id) throws ExceptionRequest ;

    /**
     * Check whether exist reservation with id car.
     * @param id id car.
     * @return true or false.
     */
    boolean existsByCar_Idcar(int id);

    /**
     * Save new reservation
     * @param addReservationRequest data of new reservation.
     * @return data on new reservation.
     * @throws ExceptionRequest When data of request is wrong.
     */
    CarReservationResponse save(AddReservationRequest addReservationRequest) throws ExceptionRequest;

    /**
     * Method return all reservation.
     * @return List all reservation.
     */
    List<Reservation> findAll();

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
