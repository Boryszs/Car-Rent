package com.Server.service;

import com.Server.dto.Request.AddCarRequest;
import com.Server.dto.Request.EditCarRequest;
import com.Server.dto.Request.QuestionCarRequest;
import com.Server.exception.ExceptionRequest;
import com.Server.model.Car;

import java.util.List;
import java.util.Optional;

public interface CarService {
    List<Car> findAll();

    Car save(AddCarRequest car) throws ExceptionRequest;

    Optional<Car> findByIdcar(int id) throws ExceptionRequest;

    Car update(EditCarRequest car) throws ExceptionRequest;

    List<Car> getCarNotOrder(QuestionCarRequest questionCarRequest) throws ExceptionRequest;

    void deleteCar(int id) throws ExceptionRequest;

    boolean existsByIdcar(int id);

    Integer deleteByIdcar(int id) throws ExceptionRequest;

    List<Car> findByLocalizationId(Long id);

    List<Car> findByLocalizationCity(String city) throws ExceptionRequest;

}
