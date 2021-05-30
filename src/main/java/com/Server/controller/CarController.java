package com.Server.controller;

import com.Server.dto.Request.CarRequest;
import com.Server.dto.Response.CarResponse;
import com.Server.exception.WrongDataException;
import com.Server.service.CarService;
import com.Server.service.LocalizationService;
import com.Server.service.ReservationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * CarController is use to supports operations about database table Car.
 *
 * @author Krystian Cwioro Kamil Bieniasz Damian Mierzynski.
 * @version 2.0.
 * @since 2020-04-27.
 */

@RequestMapping(value = "/car")
@RestController
@CrossOrigin
public class CarController {

    /**
     * Logger use to logger on server.
     */
    private static final Logger logger = LoggerFactory.getLogger(CarController.class);
    /**
     * carService operation on database table Car
     */
    private final CarService carServiceImpl;
    /**
     * ReservationService operation on database table reservation
     */
    private final ReservationService reservationServiceImpl;
    /**
     * LocationService operation on database table Localization
     */
    private final LocalizationService localizationServiceImpl;

    /**
     * Constructor
     */
    @Autowired
    public CarController(CarService carServiceImpl, ReservationService reservationServiceImpl, LocalizationService localizationServiceImpl) {
        this.carServiceImpl = carServiceImpl;
        this.reservationServiceImpl = reservationServiceImpl;
        this.localizationServiceImpl = localizationServiceImpl;
    }

    //Dodawanie samochodu

    /**
     * This method add car.
     * This method use endpoint /car/addcar.
     *
     * @param carRequest data new car.
     * @return Data added new car Http.Status 200 or 400.
     * @throws WrongDataException when localization not exist
     */
    @PostMapping("/add-car")
    public ResponseEntity<?> addCar(@Valid @RequestBody CarRequest carRequest) throws WrongDataException {
        logger.info("------ Car added successfully ------");
        carServiceImpl.save(carRequest);
        return new ResponseEntity<>(true, HttpStatus.OK);

    }

    //Zwraca wszystkie samochody.

    /**
     * This method get all car.
     * This method use endpoint /car/show-car-all.
     *
     * @return List all Car Http.Status 200 or 400.
     */
    @GetMapping(value = "/show-car-all")
    public ResponseEntity<List<CarResponse>> showCarAll() {
        logger.info("---- GET ALL CAR ----");
        return new ResponseEntity<>(carServiceImpl.findAll(), HttpStatus.OK);
    }

    //Zwraca samochody w zaleznosci od lokazlizacji

    /**
     * This method get car about localization
     * This method use endpoint /car/get-car-localization.
     *
     * @param city name city.
     * @return List all Car Http.Status 200 or 400.
     * @throws WrongDataException when localization not exist
     */
    @ResponseBody
    @GetMapping(value = "/get-car-localization")
    public ResponseEntity<?> showCarLocalization(@RequestParam String city) {
        logger.info("------ Car locations displayed successfully ------");
        return new ResponseEntity<>(carServiceImpl.findByLocalizationCity(city), HttpStatus.OK);
    }

    //Usuwa samochody

    /**
     * This method delete car.
     * This method use endpoint /car/delete-car.
     *
     * @param id id car on delete
     * @return Http.Status 200 or 400.
     */
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/delete-car")
    public ResponseEntity<?> deleteCar(@RequestParam int id) {
        carServiceImpl.deleteCar(id);
        logger.info("------ The car was successfully deleted ------");
        return new ResponseEntity(HttpStatus.OK);
    }

    //Zwracanie samochody po id

    /**
     * This method get car about id.
     * This method use endpoint /car/get-car.
     *
     * @param id id car.
     * @return data car Http.Status 200 or 400..
     * @throws WrongDataException when localization not exist
     */
    @ResponseBody
    @GetMapping("/get-car")
    public ResponseEntity<?> getCar(@RequestParam int id) throws WrongDataException {
        logger.info("------ Cars displayed successfully ------", id);
        return new ResponseEntity(carServiceImpl.findByIdCar(id), HttpStatus.OK);
    }

    //Edytowanie danych samochodu

    /**
     * This method edit car.
     * This method use endpoint /car/edit-car.
     *
     * @param carRequest new data car.
     * @return Http.Status 200 or 400..
     * @throws WrongDataException when localization not exist
     */
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/edit-car")
    public ResponseEntity<?> editCar(@RequestParam int id, @RequestBody CarRequest carRequest) {
        logger.info("------ The car was successfully edited ------");
        carServiceImpl.update(id, carRequest);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
