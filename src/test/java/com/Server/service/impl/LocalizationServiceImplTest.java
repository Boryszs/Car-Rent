package com.Server.service.impl;

import com.Server.dto.Response.LocalizationResponse;
import com.Server.exception.WrongDataException;
import com.Server.service.LocalizationService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static java.util.concurrent.TimeUnit.SECONDS;
import static org.junit.jupiter.api.Assertions.*;
/**
 * Localization service test
 */
@SpringBootTest
class LocalizationServiceImplTest {

    @Autowired
    LocalizationService localizationService;

    @Test
    @DisplayName("---- FIND ID LOCALIZATION ----")
    void findByIdLocalization() throws WrongDataException {
        for(LocalizationResponse localization:localizationService.findAll())
            assertEquals(localizationService.findByIdLocalization(localization.getId()).hashCode(), localization.hashCode());

        assertThrows(WrongDataException.class, () -> localizationService.findByIdLocalization(1000));

    }

    @Test
    @DisplayName("---- FIND CITY NAME LOCALIZATION ----")
    void findByCity() throws WrongDataException {
        for(LocalizationResponse localization:localizationService.findAll())
            assertEquals(localizationService.findByCity(localization.getCity()).hashCode(), localization.hashCode());
        assertThrows(WrongDataException.class, () -> localizationService.findByCity("Hamburg"));
    }

    @Test
    @Timeout(value = 2,unit = SECONDS)
    @DisplayName("---- FIND ALL LOCALIZATION ----")
    void findAll() {
        assertNotEquals(localizationService.findAll().size(),0);
    }

    @Test
    @DisplayName("---- EXIST CITY NAME LOCALIZATION ----")
    void existsByCity() {
        List<LocalizationResponse> localizationResponses= localizationService.findAll();
        localizationResponses.forEach(localizationResponse -> assertTrue(localizationService.existsByCity(localizationResponse.getCity())));
        assertFalse(localizationService.existsByCity("Hamburg"));
    }

    @Test
    @DisplayName("---- EXIST ID LOCALIZATION ----")
    void existsById() {
        List<LocalizationResponse> localizationResponses= localizationService.findAll();
        localizationResponses.forEach(localizationResponse -> assertTrue(localizationService.existsById(localizationResponse.getId())));
        assertFalse(localizationService.existsById(10000));
    }
}
