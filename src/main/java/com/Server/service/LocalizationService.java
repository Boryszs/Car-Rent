package com.Server.service;

import com.Server.dto.Request.LocalizationRequest;
import com.Server.dto.Response.LocalizationResponse;
import com.Server.exception.WrongDataException;

import java.util.List;


/**
 * Interface Service localization to service LocalizationRepository.
 * @author Krystian Cwioro Kamil Bieniasz Damian Mierzynski.
 * @version 2.0.
 * @since 2020-04-27.
 */

public interface LocalizationService {

    /**
     * Find car on id.
     * @param id id find car.
     * @return return data on car.
     * @throws WrongDataException when id localization is wrong.
     */
    LocalizationResponse findByIdLocalization(long id) throws WrongDataException;

    /**
     * Find city on name city
     * @param city name city.
     * @return return data city.
     * @throws WrongDataException when wrong city name.
     */
    LocalizationResponse findByCity(String city) throws WrongDataException;

    /**
     * Find all localization.
     * @return List all Localization.
     */
    List<LocalizationResponse> findAll();

    /**
     * Save new localization.
     * @param localizationRequest data new localization
     * @return return new localization data.
     */
    void save(LocalizationRequest localizationRequest);

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
