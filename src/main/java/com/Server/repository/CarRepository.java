package com.Server.repository;

import com.Server.model.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CarRepository extends JpaRepository<Car, Integer> {
    Optional<Car> findByIdcar(int id);

    boolean existsByIdcar(int id);

    Integer deleteByIdcar(int id);

    List<Car> findByLocalizationId(Long id);

    List<Car> findByLocalizationCity(String city);
}