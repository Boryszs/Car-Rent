package com.Server.controller;

import com.Server.dto.Response.LocalizationResponse;
import com.Server.entiy.Localization;
import com.Server.repository.LocalizationRepository;
import com.Server.service.LocalizationService;
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
 * Test Localization Controller
 */
@SpringBootTest
class LocalizationControllerTest {

    @Mock
    private LocalizationService localizationServiceImpl;
    @InjectMocks
    private LocalizationController localizationController;
    protected MockMvc mockMvc;
    @Autowired
    WebApplicationContext webApplicationContext;
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    LocalizationRepository localizationRepository;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Timeout(value = 2, unit = SECONDS)
    @Test
    @DisplayName("---- GET ALL LOCALIZATION ----")
    void showAll() throws Exception {
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/city/show-all")
                .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();
        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
        String content = mvcResult.getResponse().getContentAsString();
        List<LocalizationResponse> response = objectMapper.readValue(content, new TypeReference<List<LocalizationResponse>>() {});
        assertNotEquals(response.size(), 0);
    }

    @Test
    @DisplayName("---- GET LOCALIZATION ID ----")
    void showLocalizationId() throws Exception {
        List<Localization> localizations = localizationRepository.findAll();
        for(Localization localization:localizations){
            MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/city/show-id?id="+localization.getId())
                    .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();
            int status = mvcResult.getResponse().getStatus();
            assertEquals(200, status);
            String content = mvcResult.getResponse().getContentAsString();
            LocalizationResponse response = objectMapper.readValue(content, new TypeReference<LocalizationResponse>() {});
            assertNotEquals(response, null);
        }
    }

    @Test
    @DisplayName("---- GET LOCALIZATION CITY NAME ----")
    void showLocalizationCity() throws Exception {
        List<Localization> localizations = localizationRepository.findAll();
        for(Localization localization:localizations){
            MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/city/show-city?city="+localization.getCity())
                    .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();
            int status = mvcResult.getResponse().getStatus();
            assertEquals(200, status);
            String content = mvcResult.getResponse().getContentAsString();
            LocalizationResponse response = objectMapper.readValue(content, new TypeReference<LocalizationResponse>() {});
            assertNotEquals(response, null);
        }
    }
}
