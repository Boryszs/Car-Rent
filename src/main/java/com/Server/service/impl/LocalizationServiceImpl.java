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

@Service
@Transactional
public class LocalizationServiceImpl implements LocalizationService {

    private LocalizationRepository localizationRepository;

    @Autowired
    public LocalizationServiceImpl(LocalizationRepository localizationRepository) {
        this.localizationRepository = localizationRepository;
    }

    
    @Override
    public Optional<Localization> findById(long id) throws ExceptionRequest {
        if (!localizationRepository.existsById(id)) {
            throw new ExceptionRequest("Bad id localization");
        } else {
            return Optional.of(localizationRepository.findById(id).get());
        }
    }

    @Override
    public Optional<Localization> findByCity(String city) throws ExceptionRequest {
        if (!localizationRepository.existsByCity(city)) {
            throw new ExceptionRequest("Bad city localization");
        } else {
            return Optional.of(localizationRepository.findByCity(city).get());
        }
    }

    @Override
    public List<Localization> findAll() {
        return localizationRepository.findAll();
    }

    @Override
    public Localization save(Localization localization) {
        return localizationRepository.save(localization);
    }

    @Override
    public boolean existsByCity(String city) {
        return localizationRepository.existsByCity(city);
    }

    @Override
    public boolean existsById(long id) {
        return localizationRepository.existsById(id);
    }

    @Override
    public void deleteByCity(String city) {
        localizationRepository.deleteByCity(city);
    }
}
