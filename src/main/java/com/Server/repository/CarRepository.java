package com.Server.repository;

import com.Server.entiy.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Interface repository car to available connect on table database.
 * @author Krystian Cwioro Kamil Bieniasz Damian Mierzynski.
 * @version 1.0
 * @since 2020-12-29.
 */

@Repository
public interface CarRepository extends JpaRepository<Car, Integer> {
    /**
     * Method find car on id
     * @param id id on find car
     * @return car on id.
     */
    Optional<Car> findByIdcar(int id);

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
     */
    Integer deleteByIdcar(int id);

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
     */
    List<Car> findByLocalizationCity(String city);
}
