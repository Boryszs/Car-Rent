package com.Server.controller;

import com.Server.dto.Response.CarResponse;
import com.Server.service.CarService;
import com.Server.service.LocalizationService;
import com.Server.service.ReservationService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.List;

import static java.util.concurrent.TimeUnit.SECONDS;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

/**
 * Test Car Controller
 */
@SpringBootTest
class CarControllerTest {

    @Mock
    private CarService carServiceImpl;
    @Mock
    private ReservationService reservationServiceImpl;
    @Mock
    private LocalizationService localizationServiceImpl;
    @InjectMocks
    private CarController carController;
    protected MockMvc mockMvc;
    @Autowired
    WebApplicationContext webApplicationContext;
    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Timeout(value = 3, unit = SECONDS)
    @Test
    @DisplayName("---- GET ALL CARS ----")
    void showCarAll() throws Exception {
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/car/show-car-all")
                .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();
        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
        String content = mvcResult.getResponse().getContentAsString();
        List<CarResponse> response = objectMapper.readValue(content, new TypeReference<List<CarResponse>>() {
        });
        assertNotEquals(response.size(), 0);
    }

    @Test
    @DisplayName("---- GET CAR ON ID ----")
    void getCar() throws Exception {
        List<Integer> cars = List.of(2, 3, 4, 5, -1);
        for (Integer carId : cars) {
            MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/car/get-car?id=" + carId)
                    .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();
            int status = mvcResult.getResponse().getStatus();
            if (carId != -1) {
                assertEquals(200, status);
                String content = mvcResult.getResponse().getContentAsString();
                CarResponse response = objectMapper.readValue(content, new TypeReference<CarResponse>() {
                });
                assertNotEquals(response, null);
            }else{
                assertEquals(400, status);
            }
        }
    }
}
