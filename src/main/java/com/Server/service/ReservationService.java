package com.Server.service;

import com.Server.dto.Request.AddReservationRequest;
import com.Server.dto.Response.CarReservationResponse;
import com.Server.exception.WrongDataException;
import com.Server.model.Reservation;

import java.util.List;
import java.util.Optional;
/**
 * Interface Service reservation to service ReservationRepository.
 * @author Krystian Cwioro Kamil Bieniasz Damian Mierzynski.
 * @version 2.0.
 * @since 2020-04-27.
 */

public interface ReservationService {
    /**
     * Find reservation on id
     * @param id id reservation.
     * @return reservation data.
     */
    Optional<Reservation> findByIdRent(Long id);

    /**
     * Check whether reservation on id exist.
     * @param id id reservation.
     * @return true or false.
     */
    boolean existsByIdRent(Long id);

    /**
     * Delete reservation on id.
     * @param id id reservation.
     * @return return int on id delete reservation.
     */
    int deleteByIdRent(Long id);

    /**
     * Return Current reservation user on id user.
     * @param id id user.
     * @return List current reservation.
     * @throws WrongDataException when user id not exist.
     */
    List<Reservation> getCurrentReservation(Long id) throws WrongDataException;

    /**
     * Delete reservation
     * @param id id reservation.
     * @return int id reservation.
     * @throws WrongDataException when reservation not exist.
     */
    Integer deleteReservation(Long id) throws WrongDataException;

    /**
     * Check whether exist reservation with id car.
     * @param id id car.
     * @return true or false.
     */
    boolean existsByCarIdCar(int id);

    /**
     * Save new reservation
     * @param addReservationRequest data of new reservation.
     * @return data on new reservation.
     * @throws WrongDataException When data of request is wrong.
     */
    CarReservationResponse save(AddReservationRequest addReservationRequest) throws WrongDataException;

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
    List<Reservation> findByCarIdCar(int id);

    /**
     * Find the Last reservation on car
     * @param id id car.
     * @return data reservation.
     */
    Optional<Reservation> findFirstByCarIdCarOrderByIdRentDesc(int id);
}
