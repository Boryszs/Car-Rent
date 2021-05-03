package com.Server.service.impl;

import com.Server.dto.Request.LocalizationRequest;
import com.Server.dto.Response.LocalizationResponse;
import com.Server.exception.WrongDataException;
import com.Server.mapper.Mapper;
import com.Server.entiy.Localization;
import com.Server.repository.LocalizationRepository;
import com.Server.service.LocalizationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Class Service implements interface LocalizationService.
 * @author Krystian Cwioro Kamil Bieniasz Damian Mierzynski.
 * @version 2.0.
 * @since 2020-04-27.
 */

@Service
@Transactional
public class LocalizationServiceImpl implements LocalizationService {

    /**localizationRepository*/
    private final LocalizationRepository localizationRepository;
    private final Mapper<Localization, LocalizationResponse, LocalizationRequest> localizationMapper;

    @Autowired
    /**Constructor*/
    public LocalizationServiceImpl(LocalizationRepository localizationRepository, Mapper<Localization, LocalizationResponse, LocalizationRequest> localizationMapper) {
        this.localizationRepository = localizationRepository;
        this.localizationMapper = localizationMapper;
    }

    /**
     * Find car on id.
     * @param id id find car.
     * @return return data on car.
     * @throws WrongDataException when id localization is wrong.
     */
    @Override
    public LocalizationResponse findByIdLocalization(long id) throws WrongDataException {
        if (!localizationRepository.existsById(id)) {
            throw new WrongDataException("Bad id localization");
        } else {
            return localizationMapper.toDto(localizationRepository.findById(id).get());
        }
    }

    /**
     * Find city on name city
     * @param city name city.
     * @return return data city.
     * @throws WrongDataException when wrong city name.
     */
    @Override
    public LocalizationResponse findByCity(String city) throws WrongDataException {
        if (!localizationRepository.existsByCity(city)) {
            throw new WrongDataException("Bad city localization");
        } else {
            return localizationMapper.toDto(localizationRepository.findByCity(city).get());
        }
    }

    /**
     * Find all localization.
     * @return List all Localization.
     */
    @Override
    public List<LocalizationResponse> findAll() {
        return localizationRepository.findAll().parallelStream().map(localization -> localizationMapper.toDto(localization)).collect(Collectors.toList());
    }

    /**
     * Save new localization.
     * @param localizationRequest data new localization
     * @return return new localization data.
     */
    @Override
    public void save(LocalizationRequest localizationRequest) {
        localizationRepository.save(localizationMapper.toEntity(localizationRequest));
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
