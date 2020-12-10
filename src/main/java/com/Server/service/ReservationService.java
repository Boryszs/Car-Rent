package com.Server.service;

import com.Server.model.Reservation;

import java.util.List;
import java.util.Optional;

public interface ReservationService {
    Optional<Reservation> findByIdrent(Long id);

    boolean existsByIdrent(Long id);

    int deleteByIdrent(Long id);

    boolean existsByCar_Idcar(int id);

    Reservation save(Reservation reservation);

    List<Reservation> findAll();

    List<Reservation> findByCar_Idcar(int id);

    Optional<Reservation> findFirstByCarIdcarOrderByIdrentDesc(int id);
}
