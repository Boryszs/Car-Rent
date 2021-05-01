package com.Server.controller;

import com.Server.dto.Request.AddCarRequest;
import com.Server.dto.Request.EditCarRequest;
import com.Server.dto.Request.QuestionCarRequest;
import com.Server.dto.Response.MessageResponse;
import com.Server.exception.ExceptionRequest;
import com.Server.model.Car;
import com.Server.service.CarService;
import com.Server.service.LocalizationService;
import com.Server.service.ReservationService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 *   CarController is use to supports operations about database table Car.
 *   @author Krystian Cwioro Kamil Bieniasz Damian Mierzynski.
 *   @version 1.0.
 *   @since 2020-12-29.
 */

@RequestMapping(value = "/car")
@RestController
@CrossOrigin
public class CarController {

    /**Logger use to logger on server.*/
    private static final Logger logger = LoggerFactory.getLogger(CarController.class);
    /**carService operation on database table Car*/
    private CarService carServiceImpl;
    /**ReservationService operation on database table reservation*/
    private ReservationService reservationServiceImpl;
    /**LocationService operation on database table Localization*/
    private LocalizationService localizationServiceImpl;

    /**Constructor*/
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
     * @param addCarRequest data new car.
     * @return Data added new car Http.Status 200 or 400.
     * @throws ExceptionRequest when localization not exist
     */
    @PostMapping("/addcar")
    public ResponseEntity<?> addCar(@Valid @RequestBody AddCarRequest addCarRequest) {
        try {
            logger.info("------ Car added successfully ------");
            return new ResponseEntity<>(carServiceImpl.save(addCarRequest), HttpStatus.OK);
        } catch (ExceptionRequest exceptionRequest) {
            logger.error("------ Localization Not Exist To Add Car ------");
            return new ResponseEntity(new MessageResponse(exceptionRequest.getErr()), HttpStatus.BAD_REQUEST);
        }
    }

    //Zwraca wszystkie samochody.
    /**
     * This method get all car.
     * This method use endpoint /car/show-car-all.
     * @return List all Car Http.Status 200 or 400.
     */
    @ResponseBody
    @GetMapping(value = "/show-car-all")
    public List<Car> showCarAll() {
        return carServiceImpl.findAll();
    }

    //Zwraca samochody w zaleznosci od lokazlizacji
    /**
     * This method get car about localization
     * This method use endpoint /car/get-car-localization.
     * @param city name city.
     * @return List all Car Http.Status 200 or 400.
     * @throws ExceptionRequest when localization not exist
     */
    @ResponseBody
    @GetMapping(value = "/get-car-localization")
    public ResponseEntity<?> showCarLocalization(@RequestParam String city) {
        try {
            logger.info("------ Car locations displayed successfully ------");
            return new ResponseEntity<>(carServiceImpl.findByLocalizationCity(city), HttpStatus.OK);
        } catch (ExceptionRequest exceptionRequest) {
            logger.error("------ Localization Not Exist To Get Car ------");
            return new ResponseEntity(new MessageResponse(exceptionRequest.getErr()), HttpStatus.BAD_REQUEST);
        }
    }

    //Usuwa samochody
    /**
     * This method delete car.
     * This method use endpoint /car/delete-car.
     * @param id id car on delete
     * @return Http.Status 200 or 400.
     */
    @PostMapping("/delete-car")
    public ResponseEntity<?> deleteCar(@RequestParam int id) {
        try {
            carServiceImpl.deleteCar(id);
            logger.info("------ The car was successfully deleted ------");
            return new ResponseEntity(HttpStatus.OK);
        } catch (ExceptionRequest exceptionRequest) {
            logger.error("------ Car Not Exist Wrong Id ------");
            return new ResponseEntity<>(new MessageResponse(exceptionRequest.getErr()), HttpStatus.BAD_REQUEST);
        }
    }

    //Zwracanie samochody po id
    /**
     * This method get car about id.
     * This method use endpoint /car/get-car.
     * @param id id car.
     * @return data car Http.Status 200 or 400..
     * @exception ExceptionRequest when localization not exist
     */
    @ResponseBody
    @GetMapping("/get-car")
    public ResponseEntity<?> getCar(@RequestParam int id) {
        try {
            logger.info("------ Cars displayed successfully ------");
            return new ResponseEntity(carServiceImpl.findByIdcar(id).get(), HttpStatus.OK);
        } catch (ExceptionRequest exceptionRequest) {
            logger.error("------ Car Not Exist Wrong Id ------");
            return new ResponseEntity<>(new MessageResponse(exceptionRequest.getErr()), HttpStatus.BAD_REQUEST);
        }
    }

    //Zwraca samochody dostepne
    /**
     * This method return all available car.
     * This method use endpoint /car/get-cars.
     * @param questionCarRequest city data order.
     * @return List available car Http.Status 200 or 400.
     * @exception ExceptionRequest when localization not exist
     */
    @PostMapping("/get-cars")
    public ResponseEntity<?> checkCarNotOrderDate(@Valid @RequestBody QuestionCarRequest questionCarRequest){
        try {
            logger.info("------ Successfully displays available cars ------");
            return new ResponseEntity(carServiceImpl.getCarNotOrder(questionCarRequest), HttpStatus.OK);
        } catch (ExceptionRequest exceptionRequest) {
            logger.error("------ Localization Not Exist Wrong City Name ------");
            return new ResponseEntity<>(new MessageResponse(exceptionRequest.getErr()), HttpStatus.BAD_REQUEST);
        }
    }

    //Edytowanie danych samochodu
    /**
     * This method edit car.
     * This method use endpoint /car/edit-car.
     * @param editCarRequest new data car.
     * @return Http.Status 200 or 400..
     * @exception ExceptionRequest when localization not exist
     */
    @PostMapping("/edit-car")
    public ResponseEntity<?> editCar(@RequestBody EditCarRequest editCarRequest) {
        try {
            logger.info("------ The car was successfully edited ------");
            return new ResponseEntity<>(carServiceImpl.update(editCarRequest), HttpStatus.OK);
        } catch (ExceptionRequest exceptionRequest) {
            logger.error("------ Car Not Exist Wrong Id ------");
            return new ResponseEntity<>(new MessageResponse(exceptionRequest.getErr()), HttpStatus.BAD_REQUEST);
        }
    }
}
