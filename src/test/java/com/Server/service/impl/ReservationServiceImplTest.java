package com.Server.service.impl;

import com.Server.service.ReservationService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static java.util.concurrent.TimeUnit.MILLISECONDS;
/**
 * Reservation service test
 */
@SpringBootTest
class ReservationServiceImplTest {

    @Autowired
    ReservationService reservationService;

    @Test
    @DisplayName("1.5 s TEST GET ALL RESERVATION")
    @Timeout(value = 1500, unit = MILLISECONDS)
    void findAll() {
        reservationService.findAll();
    }
}
