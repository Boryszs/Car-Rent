package com.Server.service.impl;

import com.Server.dto.Request.LocalizationRequest;
import com.Server.dto.Response.LocalizationResponse;
import com.Server.exception.WrongDataException;
import com.Server.service.LocalizationService;
import org.junit.Assert;
import org.junit.jupiter.api.*;
import org.mockito.internal.matchers.apachecommons.ReflectionEquals;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.groups.Tuple.tuple;

@SpringBootTest
@TestMethodOrder(value = MethodOrderer.OrderAnnotation.class)
class LocalizationServiceImplTest {

    @Autowired
    private LocalizationService localizationService;

    @Test
    @DisplayName("Find all Localization")
    @Timeout(1)
    @Order(value = 1)
    void findAll() {
        org.assertj.core.api.Assertions.assertThat(localizationService.findAll())
                .extracting(LocalizationResponse::getId,LocalizationResponse::getCity)
                .containsExactly(
                        tuple(1L, "Tarnow"),
                        tuple(2L, "Krakow"),
                        tuple(3L, "Rzeszow"),
                        tuple(4L, "Nowy Targ"));
    }

    @Test
    @DisplayName("Find Localization By id : 1")
    @Timeout(1)
    @Order(value = 2)
    void findByIdLocalization() {
        Assert.assertTrue(new ReflectionEquals(LocalizationResponse
                .builder()
                .id(1L)
                .city("Tarnow")
                .build()
        ).matches(localizationService.findByIdLocalization(1)));

        assertThatThrownBy(() -> localizationService.findByIdLocalization(100))
                .isInstanceOf(WrongDataException.class);
    }

    @Test
    @DisplayName("Find Localization By City : Tarnow")
    @Timeout(1)
    @Order(value = 3)
    void findByCity() {
        Assert.assertTrue(new ReflectionEquals(LocalizationResponse
                .builder()
                .id(1L)
                .city("Tarnow")
                .build()
        ).matches(localizationService.findByCity("Tarnow")));

        assertThatThrownBy(() -> localizationService.findByCity("Gdansk"))
                .isInstanceOf(WrongDataException.class);
    }

    @Test
    @DisplayName("Save localization Poznań")
    @Timeout(1)
    @Order(value = 4)
    void save() {

        LocalizationRequest localization = LocalizationRequest.builder()
                .city("Poznań")
                .build();

        localizationService.save(localization);

        Assert.assertTrue(new ReflectionEquals(LocalizationResponse
                .builder()
                .id(5L)
                .city("Poznań")
                .build()
        ).matches(localizationService.findByIdLocalization(5)));
    }

    @Test
    @DisplayName("delete localization Poznań")
    @Timeout(1)
    @Order(value = 4)
    void deleteByCity() {
        localizationService.deleteByCity("Poznań");

        assertThatThrownBy(() -> localizationService.findByIdLocalization(5))
                .isInstanceOf(WrongDataException.class);
    }
}