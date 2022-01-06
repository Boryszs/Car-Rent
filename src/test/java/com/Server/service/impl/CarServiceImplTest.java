package com.Server.service.impl;

import com.Server.dto.Request.CarRequest;
import com.Server.dto.Response.CarResponse;
import com.Server.dto.Response.LocalizationResponse;
import com.Server.exception.WrongDataException;
import com.Server.service.CarService;
import javassist.NotFoundException;
import org.junit.Assert;
import org.junit.jupiter.api.*;
import org.mockito.internal.matchers.apachecommons.ReflectionEquals;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

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
                .city("Krakow")
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

        assertThatThrownBy(() -> carService.findByIdCar(100))
                .isInstanceOf(WrongDataException.class);
    }

    @Test
    @DisplayName("Find Car By Localization id : 1")
    @Timeout(1)
    @Order(value = 4)
    void findByLocalizationId() {
        Assert.assertArrayEquals(carService.findByLocalizationId(1L).toArray(),carResponses.toArray());
    }

    @Test
    @DisplayName("Update Car By id : 1")
    @Timeout(1)
    @Order(value = 5)
    void update() {
        CarRequest carRequest = CarRequest.builder()
                .mark("Seat")
                .model("Leon")
                .type("hatchback")
                .city("Tarnow")
                .yearProduction(2004)
                .color("szary")
                .engine(1900)
                .money(50)
                .image("image 2")
                .build();

        carService.update(2,carRequest);

        Assert.assertTrue(new ReflectionEquals(CarResponse
                .builder()
                .idcar(2)
                .color("szary")
                .engineCapacity(1900)
                .image("image 2")
                .mark("Seat")
                .model("Leon")
                .money(50)
                .type("hatchback")
                .yearProduction(2004)
                .localization(LocalizationResponse.builder()
                        .id(1L)
                        .city("Tarnow")
                        .build())
                .build()
        ).matches(carService.findByIdCar(2)));
    }

    @Test
    @DisplayName("Delete car by id : 2")
    @Timeout(1)
    @Order(value = 6)
    void deleteByIdCar() {
        carService.deleteByIdCar(2);
    }

}