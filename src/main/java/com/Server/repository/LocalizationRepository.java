package com.Server.repository;

import com.Server.model.Localization;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LocalizationRepository extends JpaRepository<Localization, Long> {
    Optional<Localization> findById(Long id);

    Optional<Localization> findByCity(String city);

    boolean existsByCity(String city);

    boolean existsById(Long id);

    void deleteByCity(String city);
}