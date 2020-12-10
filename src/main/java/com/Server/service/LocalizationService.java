package com.Server.service;

import com.Server.model.Localization;

import java.util.List;
import java.util.Optional;

public interface LocalizationService {
    Optional<Localization> findById(long id);

    Optional<Localization> findByCity(String city);

    List<Localization> findAll();

    Localization save(Localization localization);

    boolean existsByCity(String city);

    boolean existsById(long city);

    void deleteByCity(String city);
}
