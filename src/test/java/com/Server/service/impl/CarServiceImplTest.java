package com.Server.service.impl;

import com.Server.dto.Request.CarRequest;
import com.Server.dto.Response.CarResponse;
import com.Server.dto.Response.LocalizationResponse;
import com.Server.service.CarService;
import org.junit.Assert;
import org.junit.jupiter.api.*;
import org.mockito.internal.matchers.apachecommons.ReflectionEquals;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
@TestMethodOrder(value = MethodOrderer.OrderAnnotation.class)
class CarServiceImplTest {

    @Autowired
    private CarService carService;
    private List<CarResponse> carResponses = new ArrayList<>();

    @BeforeEach
    void setUp() {

        LocalizationResponse localizationResponseCar1 = LocalizationResponse.builder()
                .id(1L)
                .city("Tarnow")
                .build();

        CarResponse carResponse1 = CarResponse.builder()
                .idcar(1)
                .color("czarny")
                .engineCapacity(1200)
                .image("https://image.ceneostatic.pl/data/products/66661051/i-toyota-yaris-ii-2008-87km-hatchback-czarny.jpg")
                .mark("Toyota")
                .model("Yaris")
                .money(79)
                .type("hatchback")
                .yearProduction(2018)
                .localization(localizationResponseCar1)
                .build();

        carResponses.add(carResponse1);
    }


    @Test
    @DisplayName("Find all Car")
    @Timeout(1)
    @Order(value = 1)
    void findAll() {
        Assert.assertArrayEquals(carService.findAll().toArray(),carResponses.toArray());
    }

    @Test
    @DisplayName("Save Car")
    @Timeout(1)
    @Order(value = 2)
    void save() {
      CarRequest carRequest = CarRequest.builder()
                .mark("Skoda")
                .model("Octavia")
                .type("hatchback")
                .yearProduction(2020)
                .color("czarny")
                .engine(1400)
                .city("Tarnow")
                .money(120)
                .image("iamge")
                .build();

        carService.save(carRequest);
    }


    @Test
    @DisplayName("Find Car By id : 1")
    @Timeout(1)
    @Order(value = 3)
    void findByIdCar() {
        Assert.assertTrue(new ReflectionEquals(CarResponse
                .builder()
                .idcar(1)
                .color("czarny")
                .engineCapacity(1200)
                .image("https://image.ceneostatic.pl/data/products/66661051/i-toyota-yaris-ii-2008-87km-hatchback-czarny.jpg")
                .mark("Toyota")
                .model("Yaris")
                .money(79)
                .type("hatchback")
                .yearProduction(2018)
                .localization(LocalizationResponse.builder()
                        .id(1L)
                        .city("Tarnow")
                        .build())
                .build()
        ).matches(carService.findByIdCar(1)));
    }

    @Test
    @DisplayName("Find Car By Localization id : 1")
    @Timeout(1)
    @Order(value = 4)
    void findByLocalizationId() {
        Assert.assertTrue(new ReflectionEquals(List.of(CarResponse
                .builder()
                .idcar(1)
                .color("czarny")
                .engineCapacity(1200)
                .image("https://image.ceneostatic.pl/data/products/66661051/i-toyota-yaris-ii-2008-87km-hatchback-czarny.jpg")
                .mark("Toyota")
                .model("Yaris")
                .money(79)
                .type("hatchback")
                .yearProduction(2018)
                .localization(LocalizationResponse.builder()
                        .id(1L)
                        .city("Tarnow")
                        .build())
                .build())
        ).matches(carService.findByLocalizationId(1L)));
    }

    @Test
    void update() {
    }

    @Test
    void deleteByIdCar() {
    }

}