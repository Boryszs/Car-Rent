package com.Server.service.impl;

import com.Server.dto.Response.CarResponse;
import com.Server.entiy.Localization;
import com.Server.exception.WrongDataException;
import com.Server.repository.LocalizationRepository;
import com.Server.service.CarService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static java.util.concurrent.TimeUnit.SECONDS;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Car service test
 */
@SpringBootTest
class CarServiceImplTest {

    @Autowired
    private LocalizationRepository localizationRepository;
    @Autowired
    private CarService carService;

    @Test
    @DisplayName("---- FIND ALL CAR ----")
    @Timeout(value = 2,unit = SECONDS)
    void findAll() {
        assertNotEquals(carService.findAll().size(), 0);
    }

    @Test
    @DisplayName("---- FIND ALL ID ----")
    void findByIdCar() throws WrongDataException {
        for (CarResponse car : carService.findAll())
            assertEquals(carService.findByIdCar(car.getIdcar()).hashCode(), car.hashCode());
        assertThrows(WrongDataException.class, () -> carService.findByIdCar(10000));
    }

    @Test
    @DisplayName("---- EXIST CAR ----")
    void existsByIdCar() {
        assertFalse(carService.existsByIdCar(1000));
        assertTrue(carService.existsByIdCar(10));
    }

    @Test
    @DisplayName("---- FIND ON LOCALIZATION ID ----")
    void findByLocalizationId() {
        localizationRepository.findAll().forEach(localization -> assertNotEquals(carService.findByLocalizationId(localization.getId()).size(), 0));
    }

    @Test
    @DisplayName("---- FIND ON LOCALIZATION CITY NAME ----")
    void findByLocalizationCity() {
        for (Localization localization : localizationRepository.findAll()) {
            try {
                assertNotEquals(carService.findByLocalizationCity(localization.getCity()).size(), 0);
            } catch (WrongDataException wrongDataException) {
                wrongDataException.printStackTrace();
            }
        }

        assertThrows(WrongDataException.class, () -> carService.findByLocalizationCity("Hamburg"));
    }
}
