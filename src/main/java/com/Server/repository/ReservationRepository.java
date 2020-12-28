package com.Server.repository;

import com.Server.model.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Integer> {
    Optional<Reservation> findByIdrent(Long id);

    boolean existsByCar_Idcar(int id);

    boolean existsByIdrent(Long id);

    int deleteByIdrent(Long id);

    List<Reservation> findByCar_Idcar(int id);

    Optional<Reservation> findFirstByCarIdcarOrderByIdrentDesc(int id);

}