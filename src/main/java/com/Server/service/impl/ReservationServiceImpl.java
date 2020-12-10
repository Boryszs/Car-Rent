package com.Server.service.impl;

import com.Server.model.Reservation;
import com.Server.repository.ReservationRepository;
import com.Server.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ReservationServiceImpl implements ReservationService {
    @Autowired
    ReservationRepository reservationRepository;

    @Override
    public Optional<Reservation> findByIdrent(Long id) {
        return reservationRepository.findByIdrent(id);
    }

    @Override
    public boolean existsByIdrent(Long id) {
        return reservationRepository.existsByIdrent(id);
    }

    @Override
    public int deleteByIdrent(Long id) {
        return reservationRepository.deleteByIdrent(id);
    }

    @Override
    public boolean existsByCar_Idcar(int id) {
        return reservationRepository.existsByCar_Idcar(id);
    }

    @Override
    public Reservation save(Reservation reservation) {
        return reservationRepository.save(reservation);
    }

    @Override
    public List<Reservation> findAll() {
        return reservationRepository.findAll();
    }

    @Override
    public List<Reservation> findByCar_Idcar(int id) {
        return reservationRepository.findByCar_Idcar(id);
    }

    @Override
    public Optional<Reservation> findFirstByCarIdcarOrderByIdrentDesc(int id) {
        return reservationRepository.findFirstByCarIdcarOrderByIdrentDesc(id);
    }
}
