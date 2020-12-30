package com.Server.service;

import com.Server.dto.Request.AddCarRequest;
import com.Server.dto.Request.EditCarRequest;
import com.Server.dto.Request.QuestionCarRequest;
import com.Server.exception.ExceptionRequest;
import com.Server.model.Car;

import java.util.List;
import java.util.Optional;
/**
 * Interface Service car to service CarRepository.
 * @author Krystian Cwioro Kamil Bieniasz Damian Mierzynski.
 * @version 1.0
 * @since 2020-12-29.
 */

public interface CarService {
    /**
     * Method find all car.
     * @return List Car.
     */
    List<Car> findAll();

    /**
     * Method to save new car.
     * @param car new data car.
     * @return return new added car.
     * @throws ExceptionRequest when request has wrong localization.
     */
    Car save(AddCarRequest car) throws ExceptionRequest;

    /**
     * Method find car on id
     * @param id id on find car
     * @return car on id.
     * @throws ExceptionRequest when car not exist.
     */
    Optional<Car> findByIdcar(int id) throws ExceptionRequest;

    /**
     * Method to edit data car.
     * @param car data new car.
     * @return new data update car.
     * @throws ExceptionRequest where id car not exist.
     */
    Car update(EditCarRequest car) throws ExceptionRequest;

    /**
     * Method car not order.
     * @param questionCarRequest question on city and date reservation.
     * @return List car not order ar.
     * @throws ExceptionRequest when localization is wrong.
     */
    List<Car> getCarNotOrder(QuestionCarRequest questionCarRequest) throws ExceptionRequest;

    /**
     * Delete car on id.
     * @param id id car to delete.
     * @return return id deleting car.
     * @throws ExceptionRequest when id car is wrong.
     */
    void deleteCar(int id) throws ExceptionRequest;

    /**
     * Method check whether exist car on id.
     * @param id id car to check.
     * @return true or false.
     */
    boolean existsByIdcar(int id);

    /**
     * Delete car on id.
     * @param id id car to delete.
     * @return return id deleting car.
     * @throws ExceptionRequest when id car not exist.
     */
    Integer deleteByIdcar(int id) throws ExceptionRequest;

    /**
     * Find car on localization.
     * @param id id localization.
     * @return return List car witch id localization.
     */
    List<Car> findByLocalizationId(Long id);

    /**
     * Find car on localization on name city.
     * @param city name city.
     * @return return List car on localization city.
     * @throws ExceptionRequest when city name not exist.
     */
    List<Car> findByLocalizationCity(String city) throws ExceptionRequest;

}
