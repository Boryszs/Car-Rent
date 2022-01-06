package com.Server.service.impl;

import com.Server.dto.Request.ReservationRequest;
import com.Server.dto.Response.CarResponse;
import com.Server.dto.Response.LocalizationResponse;
import com.Server.dto.Response.ReservationResponse;
import com.Server.service.ReservationService;
import org.junit.Assert;
import org.junit.jupiter.api.*;
import org.mockito.internal.matchers.apachecommons.ReflectionEquals;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
@TestMethodOrder(value = MethodOrderer.OrderAnnotation.class)
class ReservationServiceImplTest {

    @Autowired
    private ReservationService reservationService;
    private List<ReservationResponse> reservationResponses = new ArrayList<>();
    private ReservationResponse reservationResponse1;
    private ReservationResponse reservationResponse2;

    @BeforeEach
    void setUp() {
        reservationResponse1 = ReservationResponse
                .builder()
                .idRent(1L)
                .carResponse(
                        CarResponse.builder()
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
                )
                .dateFrom("2022-01-05")
                .dateTo("2022-02-26")
                .localizationEnd(LocalizationResponse.builder()
                        .id(1L)
                        .city("Tarnow")
                        .build())
                .localizationStart(LocalizationResponse.builder()
                        .id(1L)
                        .city("Tarnow")
                        .build())
                .price(158f)
                .build();
        reservationResponse2 = ReservationResponse
                .builder()
                .idRent(2L)
                .carResponse(
                        CarResponse.builder()
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
                )
                .dateTo("2022-06-20")
                .dateFrom("2022-01-02")
                .localizationEnd(LocalizationResponse.builder()
                        .id(1L)
                        .city("Tarnow")
                        .build())
                .localizationStart(LocalizationResponse.builder()
                        .id(4L)
                        .city("Nowy Targ")
                        .build())
                .price(13351f)
                .build();
        reservationResponses.add(reservationResponse1);
        reservationResponses.add(reservationResponse2);
    }

    @Test
    @DisplayName("Find Reservation By id : 1")
    @Timeout(1)
    @Order(value = 1)
    void findByIdRent() {
        Assert.assertTrue(new ReflectionEquals(ReservationResponse
                .builder()
                .idRent(1L)
                .carResponse(
                        CarResponse.builder()
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
                )
                .dateFrom("2022-01-05")
                .dateTo("2022-02-26")
                .localizationEnd(LocalizationResponse.builder()
                        .id(1L)
                        .city("Tarnow")
                        .build())
                .localizationStart(LocalizationResponse.builder()
                        .id(1L)
                        .city("Tarnow")
                        .build())
                .price(158f)
                .build()
        ).matches(reservationService.findByIdRent(1L)));
    }

    @Test
    @DisplayName("Save Reservation")
    @Timeout(1)
    @Order(value = 2)
    void save() {
        ReservationRequest reservationRequest = ReservationRequest.builder()
                .idUser(1L)
                .idCar(1)
                .dateTo("2022-06-20")
                .dateFrom("2022-01-02")
                .localizationEnd("Tarnow")
                .localizationStart("Nowy Targ")
                .build();

        reservationService.save(reservationRequest);
    }

    @Test
    @DisplayName("Find all Reservation")
    @Timeout(1)
    @Order(value = 3)
    void findAll() {
        Assert.assertArrayEquals(reservationService.findAll().toArray(),reservationResponses.toArray());
    }

    @Test
    void deleteByIdRent() {
    }

    @Test
    void getCurrentReservation() {
    }

    @Test
    void findByCarIdCar() {
    }

    @Test
    void findFirstByCarIdCarOrderByIdRentDesc() {
    }
}