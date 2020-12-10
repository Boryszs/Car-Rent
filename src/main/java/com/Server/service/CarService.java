package com.Server.service;

import com.Server.model.Car;

import java.util.List;
import java.util.Optional;

public interface CarService {
    List<Car> findAll();

    Car save(Car car);

    Optional<Car> findByIdcar(int id);

    boolean existsByIdcar(int id);

    Integer deleteByIdcar(int id);

    List<Car> findByLocalizationId(Long id);

    List<Car> findByLocalizationCity(String city);

}
