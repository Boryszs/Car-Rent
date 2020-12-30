package com.Server.repository;

import com.Server.model.Localization;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Interface repository localization to available connect on table database.
 * @author Krystian Cwioro Kamil Bieniasz Damian Mierzynski.
 * @version 1.0
 * @since 2020-12-29.
 */

@Repository
public interface LocalizationRepository extends JpaRepository<Localization, Long> {
    /**
     * Find car on id.
     * @param id id find car.
     * @return return data on car.
     */
    Optional<Localization> findById(Long id);

    /**
     * Find city on name city
     * @param city name city.
     * @return return data city.
     */
    Optional<Localization> findByCity(String city);

    /**
     * Check whether city exist.
     * @param city city name.
     * @return return true or false.
     */
    boolean existsByCity(String city);

    /**
     * Check whether city id exist.
     * @param id city id.
     * @return return true or false.
     */
    boolean existsById(Long id);

    /**
     * Delete city on name city.
     * @param city city name.
     */
    void deleteByCity(String city);
}