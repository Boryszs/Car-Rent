package com.Server.service;

import com.Server.exception.ExceptionRequest;
import com.Server.model.Localization;

import java.util.List;
import java.util.Optional;


/**
 * Interface Service localization to service LocalizationRepository.
 * @author Krystian Cwioro Kamil Bieniasz Damian Mierzynski.
 * @version 1.0
 * @since 2020-12-29.
 */

public interface LocalizationService {

    /**
     * Find car on id.
     * @param id id find car.
     * @return return data on car.
     * @throws ExceptionRequest when id localization is wrong.
     */
    Optional<Localization> findById(long id) throws ExceptionRequest;

    /**
     * Find city on name city
     * @param city name city.
     * @return return data city.
     * @throws ExceptionRequest when wrong city name.
     */
    Optional<Localization> findByCity(String city) throws ExceptionRequest;

    /**
     * Find all localization.
     * @return List all Localization.
     */
    List<Localization> findAll();

    /**
     * Save new localization.
     * @param localization data new localization
     * @return return new localization data.
     */
    Localization save(Localization localization);

    /**
     * Check whether city exist.
     * @param city city name.
     * @return return true or false.
     */
    boolean existsByCity(String city);

    /**
     * Check whether city id exist.
     * @param city city id.
     * @return return true or false.
     */
    boolean existsById(long city);

    /**
     * Delete city on name city.
     * @param city city name.
     */
    void deleteByCity(String city);
}
