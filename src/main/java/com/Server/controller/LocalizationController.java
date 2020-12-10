package com.Server.controller;

import com.Server.dto.Request.CityRequest;
import com.Server.exception.ExceptionRequest;
import com.Server.model.Localization;
import com.Server.repository.LocalizationRepository;
import com.Server.service.impl.LocalizationServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/city")
@CrossOrigin
public class LocalizationController {
    @Autowired
    LocalizationServiceImpl localizationServiceImpl;


    //Zwracanie lokalizacji wszystkich
    @ResponseBody
    @GetMapping(value = "/show-all")
    public List<Localization> showAll() {
        return localizationServiceImpl.findAll();
    }


    //Zwracanie lokazlizacji po id
    @ResponseBody
    @GetMapping(value = "/show-id")
    public ResponseEntity<?> showLocalizationId(@RequestParam int id) {

        if(localizationServiceImpl.existsById(id)) {
            return new ResponseEntity<>(localizationServiceImpl.findById(id).get(), HttpStatus.OK);
        }else
        {
            try {
                throw new ExceptionRequest("Bad id localization");
            } catch (ExceptionRequest exceptionRequest) {
                return new ResponseEntity(exceptionRequest.getErr(), HttpStatus.BAD_REQUEST);
            }
        }
    }


    //Zwracanie lokazlizacji po nazwie miasta
    @ResponseBody
    @GetMapping(value = "/show-city")
    public ResponseEntity<?> showLocalizationCity(@RequestParam String city) {
        if(localizationServiceImpl.existsByCity(city)) {
            return new ResponseEntity<>(localizationServiceImpl.findByCity(city).get(),HttpStatus.OK);
        }else{
            try {
                throw new ExceptionRequest("Bad city localization");
            } catch (ExceptionRequest exceptionRequest) {
                return new ResponseEntity(exceptionRequest.getErr(), HttpStatus.BAD_REQUEST);
            }
        }
    }


    //Dodawanie lokalizacji
    @PostMapping(value = "/add")
    public ResponseEntity<?> addcity(@RequestBody CityRequest cityRequest) {
        localizationServiceImpl.save(new Localization(cityRequest.getCity()));
        return new ResponseEntity(HttpStatus.OK);
    }

}
