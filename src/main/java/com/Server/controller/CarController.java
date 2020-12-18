package com.Server.controller;

import com.Server.dto.Request.AddCarRequest;
import com.Server.dto.Request.EditCarRequest;
import com.Server.dto.Request.QuestionCarRequest;
import com.Server.dto.Response.MessageResponse;
import com.Server.exception.ExceptionRequest;
import com.Server.model.Car;
import com.Server.model.Localization;
import com.Server.model.Reservation;
import com.Server.service.CarService;
import com.Server.service.LocalizationService;
import com.Server.service.ReservationService;
import com.Server.service.impl.CarServiceImpl;
import com.Server.service.impl.LocalizationServiceImpl;
import com.Server.service.impl.ReservationServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@RequestMapping(value = "/car")
@RestController
@CrossOrigin
public class CarController {

    private static final Logger logger = LoggerFactory.getLogger(CarController.class);
    private CarService carServiceImpl;
    private ReservationService reservationServiceImpl;
    private LocalizationService localizationServiceImpl;

    @Autowired
    public CarController(CarService carServiceImpl, ReservationService reservationServiceImpl, LocalizationService localizationServiceImpl) {
        this.carServiceImpl = carServiceImpl;
        this.reservationServiceImpl = reservationServiceImpl;
        this.localizationServiceImpl = localizationServiceImpl;
    }


    //Dodawanie samochodu
    @PostMapping("/addcar")
    public ResponseEntity<?> addCar(@Valid @RequestBody AddCarRequest addCarRequest) {
        try {
            return new ResponseEntity<>(carServiceImpl.save(addCarRequest), HttpStatus.OK);
        } catch (ExceptionRequest exceptionRequest) {
            logger.error("------ Localization Not Exist To Add Car ------");
            return new ResponseEntity(new MessageResponse(exceptionRequest.getErr()), HttpStatus.BAD_REQUEST);
        }
    }


    //Zwraca wszystkie samochody.
    @ResponseBody
    @GetMapping(value = "/show-car-all")
    public List<Car> showCarAll() {
        return carServiceImpl.findAll();
    }


    //Zwraca samochody w zaleznosci od lokazlizacji
    @ResponseBody
    @GetMapping(value = "/get-car-localization")
    public ResponseEntity<?> showCarLocalization(@RequestParam String city) {
        try {
            return new ResponseEntity<>(carServiceImpl.findByLocalizationCity(city), HttpStatus.OK);
        } catch (ExceptionRequest exceptionRequest) {
            logger.error("------ Localization Not Exist To Get Car ------");
            return new ResponseEntity(new MessageResponse(exceptionRequest.getErr()), HttpStatus.BAD_REQUEST);
        }
    }


    //TODO FIX DELETE
    //Usuwa samochody
    @PostMapping("/delete-car")
    public ResponseEntity<?> deleteCar(@RequestParam int id) {
        if (carServiceImpl.existsByIdcar(id)) {
            carServiceImpl.deleteByIdcar(id);
            return new ResponseEntity(HttpStatus.OK);
        } else {
            try {
                throw new ExceptionRequest("Wrong car");
            } catch (ExceptionRequest e) {
                return new ResponseEntity<>(e.getErr(), HttpStatus.BAD_REQUEST);
            }
        }
    }


    //Zwracanie samochody po id
    @ResponseBody
    @GetMapping("/get-car")
    public ResponseEntity<?> getCar(@RequestParam int id) {
        try {
            return new ResponseEntity(carServiceImpl.findByIdcar(id).get(), HttpStatus.OK);
        } catch (ExceptionRequest exceptionRequest) {
            logger.error("------ Car Not Exist Wrong Id ------");
            return new ResponseEntity<>(new MessageResponse(exceptionRequest.getErr()), HttpStatus.BAD_REQUEST);
        }
    }


    //Zwraca samochody dostepne
    @PostMapping("/get-cars")
    public ResponseEntity<?> checkCarNotOrderDate(@Valid @RequestBody QuestionCarRequest questionCarRequest){
        try {
            return new ResponseEntity(carServiceImpl.getCarNotOrder(questionCarRequest), HttpStatus.OK);
        } catch (ExceptionRequest exceptionRequest) {
            logger.error("------ Localization Not Exist Wrong City Name ------");
            return new ResponseEntity<>(new MessageResponse(exceptionRequest.getErr()), HttpStatus.BAD_REQUEST);
        }
    }


    //Edytowanie danych samochodu
    @PostMapping("/edit-car")
    public ResponseEntity<?> editCar(@RequestBody EditCarRequest editCarRequest) {
        try {
            return new ResponseEntity<>(carServiceImpl.update(editCarRequest), HttpStatus.OK);
        } catch (ExceptionRequest exceptionRequest) {
            logger.error("------ Car Not Exist Wrong Id ------");
            return new ResponseEntity<>(new MessageResponse(exceptionRequest.getErr()), HttpStatus.BAD_REQUEST);
        }
    }
}
