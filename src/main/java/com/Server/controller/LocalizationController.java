package com.Server.controller;

import com.Server.dto.Request.LocalizationRequest;
import com.Server.dto.Response.LocalizationResponse;
import com.Server.exception.WrongDataException;
import com.Server.service.LocalizationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * LocalizationController is use to supports operations about database table Localization.
 *
 * @author Krystian Cwioro Kamil Bieniasz Damian Mierzynski.
 * @version 2.0.
 * @since 2020-04-27.
 */

@RestController
@RequestMapping(value = "/city")
@CrossOrigin
public class LocalizationController {

    /**
     * Logger use to logger on server.
     */
    private static final Logger logger = LoggerFactory.getLogger(LocalizationController.class);
    /**
     * LocationSercive operation on database table Localization
     */
    private LocalizationService localizationServiceImpl;

    /**
     * Constructor
     */
    @Autowired
    public LocalizationController(LocalizationService localizationServiceImpl) {
        this.localizationServiceImpl = localizationServiceImpl;
    }

    //Zwraca wszystkie lokalizacje

    /**
     * This method get all localization.
     * This method use endpoint /city/show-all.
     *
     * @return List<Localization> lita all localization.
     */
    @ResponseBody
    @GetMapping(value = "/show-all")
    public ResponseEntity<List<LocalizationResponse>> showAll() {
        return new ResponseEntity<>(localizationServiceImpl.findAll(), HttpStatus.OK);
    }


    //Zwraca lokazlizacje po id

    /**
     * This method get localization on id.
     * This method use endpoint /city/show-id.
     *
     * @return data localization Http.Status 200 or 400.
     * @throws WrongDataException when localization id not exist.
     */
    @ResponseBody
    @GetMapping(value = "/show-id")
    public ResponseEntity<?> showLocalizationId(@RequestParam int id) {
        logger.info("------ Successfully returned location after id ------");
        return new ResponseEntity<>(localizationServiceImpl.findByIdLocalization(id), HttpStatus.OK);
    }

    //Zwraca lokazlizacje po nazwie miasta

    /**
     * This method get localization on name city.
     * This method use endpoint /city/show-city.
     *
     * @param city data about city.
     * @return city data Http.Status 200 or 400.
     */
    @ResponseBody
    @GetMapping(value = "/show-city")
    public ResponseEntity<?> showLocalizationCity(@RequestParam String city) {
        logger.info("------ Location by city name was returned successfully ------");
        return new ResponseEntity<>(localizationServiceImpl.findByCity(city), HttpStatus.OK);
    }

    //Dodaje nowa lokalizacje

    /**
     * This method add new localization.
     * This method use endpoint /city/add.
     *
     * @param localizationRequest data new city.
     * @return Http.Status 200.
     */
    @PostMapping(value = "/add")
    public ResponseEntity<?> addCity(@RequestBody LocalizationRequest localizationRequest) {
        localizationServiceImpl.save(localizationRequest);
        logger.info("------ City location added successfully ------");
        return new ResponseEntity(HttpStatus.OK);
    }

}
