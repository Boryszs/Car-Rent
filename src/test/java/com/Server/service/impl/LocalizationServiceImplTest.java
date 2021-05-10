package com.Server.service.impl;

import com.Server.dto.Response.LocalizationResponse;
import com.Server.exception.WrongDataException;
import com.Server.service.LocalizationService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

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

}
