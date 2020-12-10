package com.Server.service.impl;

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

    @Autowired
    LocalizationRepository localizationRepository;

    @Override
    public Optional<Localization> findById(long id) {
        return localizationRepository.findById(id);
    }

    @Override
    public Optional<Localization> findByCity(String city) {
        return localizationRepository.findByCity(city);
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
