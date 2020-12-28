package com.Server.service;

import com.Server.dto.Request.AddReservationRequest;
import com.Server.dto.Response.CarReservationResponse;
import com.Server.exception.ExceptionRequest;
import com.Server.model.Reservation;

import java.util.List;
import java.util.Optional;

public interface ReservationService {
    Optional<Reservation> findByIdrent(Long id);

    boolean existsByIdrent(Long id);

    int deleteByIdrent(Long id);

    List<Reservation> getCurrentReservation(Long id) throws ExceptionRequest;

    Integer deleteReservation(Long id) throws ExceptionRequest ;

    boolean existsByCar_Idcar(int id);

    CarReservationResponse save(AddReservationRequest addReservationRequest) throws ExceptionRequest;

    List<Reservation> findAll();

    List<Reservation> findByCar_Idcar(int id);

    Optional<Reservation> findFirstByCarIdcarOrderByIdrentDesc(int id);
}
