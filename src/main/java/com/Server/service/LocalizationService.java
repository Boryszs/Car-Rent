package com.Server.service;

import com.Server.exception.ExceptionRequest;
import com.Server.model.Localization;

import java.util.List;
import java.util.Optional;

public interface LocalizationService {
    Optional<Localization> findById(long id) throws ExceptionRequest;

    Optional<Localization> findByCity(String city) throws ExceptionRequest;

    List<Localization> findAll();

    Localization save(Localization localization);

    boolean existsByCity(String city);

    boolean existsById(long city);

    void deleteByCity(String city);
}
