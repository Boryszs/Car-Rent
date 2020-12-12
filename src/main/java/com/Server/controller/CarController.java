package com.Server.controller;

import com.Server.dto.Request.AddCarRequest;
import com.Server.dto.Request.EditCarRequest;
import com.Server.dto.Request.QuestionCarRequest;
import com.Server.exception.ExceptionRequest;
import com.Server.model.Car;
import com.Server.model.Localization;
import com.Server.model.Reservation;
import com.Server.service.impl.CarServiceImpl;
import com.Server.service.impl.LocalizationServiceImpl;
import com.Server.service.impl.ReservationServiceImpl;
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

    @Autowired
    CarServiceImpl carServiceImpl;
    @Autowired
    ReservationServiceImpl reservationServiceImpl;
    @Autowired
    LocalizationServiceImpl localizationServiceImpl;


    //Dodawanie samochodu
    @PostMapping("/addcar")
    public ResponseEntity<?> addCar(@Valid @RequestBody AddCarRequest addCarRequest) {
        Optional<Localization> localizations = localizationServiceImpl.findByCity(addCarRequest.getCity());
        Localization localization;
        if (localizations.isPresent()) {
            localization = localizations.get();
        } else {
            try {
                throw new ExceptionRequest("Wrong localization!!!");
            } catch (ExceptionRequest e) {
                return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
            }
        }
        Car car = new Car(addCarRequest.getMark(), addCarRequest.getModel(), addCarRequest.getType(), addCarRequest.getYearProduction(), addCarRequest.getColor(), addCarRequest.getEngine(), addCarRequest.getMoney(), localization);
        carServiceImpl.save(car);
        return new ResponseEntity<>(car, HttpStatus.OK);
    }


    //Zwracanie wszystkich samochodow.
    @ResponseBody
    @GetMapping(value = "/show-car-all")
    public List<Car> showCarAll() {
        return carServiceImpl.findAll();
    }


    //Zwracanie samochodu w zaleznosci od lokazlizacji przekazywanej stringiem
    @ResponseBody
    @GetMapping(value = "/get-car-localization")
    public ResponseEntity<?> showCarLocalization(@RequestParam String city) {
        if (localizationServiceImpl.existsByCity(city)) {
            return new ResponseEntity<>(carServiceImpl.findByLocalizationCity(city), HttpStatus.OK);
        } else {
            try {
                throw new ExceptionRequest("Wrong city");
            } catch (ExceptionRequest exceptionRequest) {
                return new ResponseEntity<>(exceptionRequest.getErr(), HttpStatus.BAD_REQUEST);
            }
        }
    }

    // Usuwanie samochodu
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

    //Zwracanie samochodu po id
    @ResponseBody
    @GetMapping("/get-car")
    public ResponseEntity<?> getCar(@RequestParam int id) {
        if (carServiceImpl.existsByIdcar(id)) {
            return new ResponseEntity(carServiceImpl.findByIdcar(id).get(), HttpStatus.OK);
        } else {
            try {
                throw new ExceptionRequest("Wrong car");
            } catch (ExceptionRequest e) {
                return new ResponseEntity<>(e.getErr(), HttpStatus.BAD_REQUEST);
            }
        }
    }


    //Zwracanie samochodu dostepne
    @PostMapping("/get-cars")
    public ResponseEntity<?> checkCarNotOrderDate(@Valid @RequestBody QuestionCarRequest questionCarRequest) {
        List<Car> cars = carServiceImpl.findByLocalizationCity(questionCarRequest.getCity());
        List<Car> carsEmpty = carServiceImpl.findByLocalizationCity(questionCarRequest.getCity());
        for (Car car : cars) {
            if (reservationServiceImpl.existsByCar_Idcar(car.getIdcar())) {
                List<Reservation> reservation = reservationServiceImpl.findByCar_Idcar(car.getIdcar());
                for (Reservation reser : reservation) {
                    if (reser.getDataFrom().compareTo(questionCarRequest.getDateTo()) * questionCarRequest.getDateTo().compareTo(reser.getDataTo()) >= 0) {
                        carsEmpty.remove(car);
                    }
                    if (reser.getDataFrom().compareTo(questionCarRequest.getDateFrom()) * questionCarRequest.getDateFrom().compareTo(reser.getDataTo()) >= 0) {
                        carsEmpty.remove(car);
                    }
                    if (reser.getDataFrom().after(questionCarRequest.getDateFrom()) && reser.getDataTo().before(questionCarRequest.getDateTo())) {
                        carsEmpty.remove(car);
                    }
                }
            }
        }
        return new ResponseEntity(carsEmpty, HttpStatus.OK);
    }


    //edytowanie danych samochodu
    @PostMapping("/edit-car")
    public ResponseEntity<?> editCar(@RequestBody EditCarRequest editCarRequest) {
        Optional<Car> car = carServiceImpl.findByIdcar(editCarRequest.getIdcar());
        if (car.isPresent()) {

            car.get().setMark(editCarRequest.getMark());
            car.get().setType(editCarRequest.getType());
            car.get().setYearProduction(editCarRequest.getYearProduction());
            car.get().setColor(editCarRequest.getColor());
            car.get().setEngineCapacity(editCarRequest.getEngine());
            car.get().setLocalization((localizationServiceImpl.findByCity(car.get().getLocalization().getCity()).get()));
        } else {
            try {
                throw new ExceptionRequest("Wrong car!!!");
            } catch (ExceptionRequest e) {
                return new ResponseEntity(e.getErr(), HttpStatus.BAD_REQUEST);
            }
        }
        return new ResponseEntity<>(carServiceImpl.save(car.get()), HttpStatus.OK);
    }
}
