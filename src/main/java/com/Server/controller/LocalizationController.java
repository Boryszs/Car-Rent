package com.Server.controller;

import com.Server.dto.Request.CityRequest;
import com.Server.dto.Response.MessageResponse;
import com.Server.exception.ExceptionRequest;
import com.Server.model.Localization;
import com.Server.repository.LocalizationRepository;
import com.Server.service.LocalizationService;
import com.Server.service.impl.LocalizationServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/city")
@CrossOrigin
public class LocalizationController {

    private static final Logger logger = LoggerFactory.getLogger(LocalizationController.class);
    private LocalizationService localizationServiceImpl;

    @Autowired
    public LocalizationController(LocalizationService localizationServiceImpl) {
        this.localizationServiceImpl = localizationServiceImpl;
    }


    //Zwraca wszystkie lokalizacje
    @ResponseBody
    @GetMapping(value = "/show-all")
    public List<Localization> showAll() {
        return localizationServiceImpl.findAll();
    }


    //Zwraca lokazlizacje po id
    @ResponseBody
    @GetMapping(value = "/show-id")
    public ResponseEntity<?> showLocalizationId(@RequestParam int id) {
        try {
            return new ResponseEntity<>(localizationServiceImpl.findById(id).get(), HttpStatus.OK);
        } catch (ExceptionRequest exceptionRequest) {
            logger.error("------ Localization Id City Not Exist To Get ------");
            return new ResponseEntity(new MessageResponse(exceptionRequest.getErr()), HttpStatus.BAD_REQUEST);
        }
    }

    //Zwraca lokazlizacje po nazwie miasta
    @ResponseBody
    @GetMapping(value = "/show-city")
    public ResponseEntity<?> showLocalizationCity(@RequestParam String city) {
        try {
            return new ResponseEntity<>(localizationServiceImpl.findByCity(city).get(), HttpStatus.OK);
        } catch (ExceptionRequest exceptionRequest) {
            logger.error("------ Localization Name City Not Exist To Get ------");
            return new ResponseEntity(new MessageResponse(exceptionRequest.getErr()), HttpStatus.BAD_REQUEST);
        }
    }


    //Dodaje nowa lokalizacje
    @PostMapping(value = "/add")
    public ResponseEntity<?> addcity(@RequestBody CityRequest cityRequest) {
        localizationServiceImpl.save(new Localization(cityRequest.getCity()));
        return new ResponseEntity(HttpStatus.OK);
    }

}
