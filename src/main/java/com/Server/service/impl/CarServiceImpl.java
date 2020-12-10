package com.Server.service.impl;

import com.Server.model.Car;
import com.Server.repository.CarRepository;
import com.Server.repository.ReservationRepository;
import com.Server.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class CarServiceImpl implements CarService {

    @Autowired
    private CarRepository carRepository;
    @Autowired
    private ReservationRepository reservationRepository;

    @Override
    public List<Car> findAll() {
        return carRepository.findAll();
    }

    @Override
    public Car save(Car car) {
        return carRepository.save(car);
    }

    @Override
    public Optional<Car> findByIdcar(int id) {
        return carRepository.findByIdcar(id);
    }

    @Override
    public boolean existsByIdcar(int id) {
        return carRepository.existsByIdcar(id);
    }

    @Override
    public Integer deleteByIdcar(int id) {
        return carRepository.deleteByIdcar(id);
    }

    @Override
    public List<Car> findByLocalizationId(Long id) {
        return carRepository.findByLocalizationId(id);
    }

    @Override
    public List<Car> findByLocalizationCity(String city) {
        return carRepository.findByLocalizationCity(city);
    }


}
