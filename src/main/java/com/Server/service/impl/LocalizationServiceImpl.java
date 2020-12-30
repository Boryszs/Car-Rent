package com.Server.service.impl;

import com.Server.exception.ExceptionRequest;
import com.Server.model.Localization;
import com.Server.repository.LocalizationRepository;
import com.Server.service.LocalizationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

/**
 * Class Service implements interface LocalizationService.
 * @author Krystian Cwioro Kamil Bieniasz Damian Mierzynski.
 * @version 1.0
 * @since 2020-12-29.
 */

@Service
@Transactional
public class LocalizationServiceImpl implements LocalizationService {

    /**localizationRepository*/
    private LocalizationRepository localizationRepository;

    @Autowired
    /**Constructor*/
    public LocalizationServiceImpl(LocalizationRepository localizationRepository) {
        this.localizationRepository = localizationRepository;
    }

    /**
     * Find car on id.
     * @param id id find car.
     * @return return data on car.
     * @throws ExceptionRequest when id localization is wrong.
     */
    @Override
    public Optional<Localization> findById(long id) throws ExceptionRequest {
        if (!localizationRepository.existsById(id)) {
            throw new ExceptionRequest("Bad id localization");
        } else {
            return Optional.of(localizationRepository.findById(id).get());
        }
    }

    /**
     * Find city on name city
     * @param city name city.
     * @return return data city.
     * @throws ExceptionRequest when wrong city name.
     */
    @Override
    public Optional<Localization> findByCity(String city) throws ExceptionRequest {
        if (!localizationRepository.existsByCity(city)) {
            throw new ExceptionRequest("Bad city localization");
        } else {
            return Optional.of(localizationRepository.findByCity(city).get());
        }
    }

    /**
     * Find all localization.
     * @return List all Localization.
     */
    @Override
    public List<Localization> findAll() {
        return localizationRepository.findAll();
    }

    /**
     * Save new localization.
     * @param localization data new localization
     * @return return new localization data.
     */
    @Override
    public Localization save(Localization localization) {
        return localizationRepository.save(localization);
    }

    /**
     * Check whether city exist.
     * @param city city name.
     * @return return true or false.
     */
    @Override
    public boolean existsByCity(String city) {
        return localizationRepository.existsByCity(city);
    }

    /**
     * Check whether city id exist.
     * @param id city id.
     * @return return true or false.
     */
    @Override
    public boolean existsById(long id) {
        return localizationRepository.existsById(id);
    }

    /**
     * Delete city on name city.
     * @param city city name.
     */
    @Override
    public void deleteByCity(String city) {
        localizationRepository.deleteByCity(city);
    }
}
