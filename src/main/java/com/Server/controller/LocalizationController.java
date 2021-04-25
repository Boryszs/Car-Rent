package com.Server.controller;

import com.Server.dto.Request.CityRequest;
import com.Server.dto.Response.MessageResponse;
import com.Server.exception.ExceptionRequest;
import com.Server.model.Localization;
import com.Server.service.LocalizationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 *   LocalizationController is use to supports operations about database table Localization.
 *   @author Krystian Cwioro Kamil Bieniasz Damian Mierzynski.
 *   @version 1.0.
 *   @since 2020-12-29.
 */

@RestController
@RequestMapping(value = "/city")
@CrossOrigin
public class LocalizationController {

    /**Logger use to logger on server.*/
    private static final Logger logger = LoggerFactory.getLogger(LocalizationController.class);
    /**LocationSercive operation on database table Localization*/
    private LocalizationService localizationServiceImpl;

    /**Constructor*/
    @Autowired
    public LocalizationController(LocalizationService localizationServiceImpl) {
        this.localizationServiceImpl = localizationServiceImpl;
    }

    //Zwraca wszystkie lokalizacje
    /**
     * This method get all localization.
     * This method use endpoint /city/show-all.
     * @return List<Localization> lita all localization.
     */
    @ResponseBody
    @GetMapping(value = "/show-all")
    public List<Localization> showAll() {
        return localizationServiceImpl.findAll();
    }


    //Zwraca lokazlizacje po id
    /**
     * This method get localization on id.
     * This method use endpoint /city/show-id.
     * @return data localization Http.Status 200 or 400.
     * @exception ExceptionRequest when localization id not exist.
     */
    @ResponseBody
    @GetMapping(value = "/show-id")
    public ResponseEntity<?> showLocalizationId(@RequestParam int id) {
        try {
            logger.info("------ Successfully returned location after id ------");
            return new ResponseEntity<>(localizationServiceImpl.findById(id).get(), HttpStatus.OK);
        } catch (ExceptionRequest exceptionRequest) {
            logger.error("------ Localization Id City Not Exist To Get ------");
            return new ResponseEntity(new MessageResponse(exceptionRequest.getError()), HttpStatus.BAD_REQUEST);
        }
    }

    //Zwraca lokazlizacje po nazwie miasta
    /**
     * This method get localization on name city.
     * This method use endpoint /city/show-city.
     * @param city data about city.
     * @return city data Http.Status 200 or 400.
     */
    @ResponseBody
    @GetMapping(value = "/show-city")
    public ResponseEntity<?> showLocalizationCity(@RequestParam String city) {
        try {
            logger.info("------ Location by city name was returned successfully ------");
            return new ResponseEntity<>(localizationServiceImpl.findByCity(city).get(), HttpStatus.OK);
        } catch (ExceptionRequest exceptionRequest) {
            logger.error("------ Localization Name City Not Exist To Get ------");
            return new ResponseEntity(new MessageResponse(exceptionRequest.getError()), HttpStatus.BAD_REQUEST);
        }
    }

    //Dodaje nowa lokalizacje
    /**
     * This method add new localization.
     * This method use endpoint /city/add.
     * @param cityRequest data new city.
     * @return Http.Status 200.
     */
    @PostMapping(value = "/add")
    public ResponseEntity<?> addcity(@RequestBody CityRequest cityRequest) {
        localizationServiceImpl.save(new Localization(cityRequest.getCity()));
        logger.info("------ City location added successfully ------");
        return new ResponseEntity(HttpStatus.OK);
    }

}
