package com.Server.controller;

import com.Server.dto.Request.ReservationRequest;
import com.Server.dto.Response.MessageResponse;
import com.Server.dto.Response.ReservationResponse;
import com.Server.exception.WrongDataException;
import com.Server.model.Reservation;
import com.Server.service.CarService;
import com.Server.service.LocalizationService;
import com.Server.service.ReservationService;
import com.Server.service.UserService;
import com.Server.service.impl.SendMailImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 *   ReservationController is use to supports operations about database table Reservation.
 *   @author Krystian Cwioro Kamil Bieniasz Damian Mierzynski.
 *   @version 2.0.
 *   @since 2020-04-27.
 */

@RequestMapping(value = "/reservation")
@RestController
@CrossOrigin
public class ReservationController {
    /**Logger use to logger on server.*/
    private static final Logger logger = LoggerFactory.getLogger(ReservationController.class);
    /**ReservationService operation on database table Reservation*/
    private final ReservationService reservationServiceImpl;
    /**UserService operation on database table User*/
    private final UserService userServiceImpl;
    /**CarService operation on database table Car*/
    private final CarService carServiceImpl;
    /**LocationSercive operation on database table Localization*/
    private final LocalizationService localizationServiceImpl;
    /**SendMail use to send mail*/
    private final SendMailImpl sendMailImpl;

    /**Constructor*/
    @Autowired
    public ReservationController(ReservationService reservationServiceImpl, UserService userServiceImpl, CarService carServiceImpl, LocalizationService localizationServiceImpl, SendMailImpl sendMailImpl) {
        this.reservationServiceImpl = reservationServiceImpl;
        this.userServiceImpl = userServiceImpl;
        this.carServiceImpl = carServiceImpl;
        this.localizationServiceImpl = localizationServiceImpl;
        this.sendMailImpl = sendMailImpl;
    }

    //Zwracanie rezerwacji wszystkich
    /**
     * This method get all reservation.
     * This method use endpoint /reservation/show.
     * @return List Reservation all.
     */
    @ResponseBody
    @GetMapping(value = "/show")
    public List<ReservationResponse> getReservations() {
        return reservationServiceImpl.findAll();
    }


    /**
     * This method delete reservation.
     * This method use endpoint /reservation/delete.
     * @param id id reservation.
     * @return return id delete reservation Http.Status 200 or 400.
     * @exception WrongDataException when reservation id not exist.
     */
    @PostMapping(value = "/delete")
    public ResponseEntity<?> deleteReservation(@RequestParam Long id){
        //try {
            logger.info("------ The reservation was successfully deleted ------");
            reservationServiceImpl.deleteByIdRent(id);
            return new ResponseEntity(HttpStatus.OK);
//        } catch (WrongDataException wrongDataException) {
//            logger.error("------ Reservation Id Not Exist To Delete ------");
//            return new ResponseEntity(new MessageResponse(wrongDataException.getError()), HttpStatus.BAD_REQUEST);
//        }
    }

    //Zwracanie rezerwacji po id usera
    /**
     * This method get all reservation user about id user.
     * This method use endpoint /reservation/get.
     * @param id id user.
     * @return list reservation Http.Status 200 or 400.
     * @exception WrongDataException when reservation id not exist.
     */
    @ResponseBody
    @GetMapping(value = "/get")
    public ResponseEntity<?> getReservation(@RequestParam Long id) {
        try {
            logger.info("------ Reservations displayed successfully ------");
            return new ResponseEntity(userServiceImpl.getReservationUser(id), HttpStatus.OK);
        } catch (WrongDataException wrongDataException) {
            logger.error("------ Reservation Id Not Exist To Get ------");
            return new ResponseEntity(new MessageResponse(wrongDataException.getError()), HttpStatus.BAD_REQUEST);
        }
    }

    //Dodawanie rezerwacji

    /**
     * This method add new reservation user.
     * This method use endpoint /reservation/add.
     * @param reservationRequest data new reservation.
     * @return return data new reservation Http.Status 200 or 400.
     * @exception WrongDataException when reservation add error.
     */
    @ResponseBody
    @PostMapping(value = "/add")
    public ResponseEntity<?> addReservation(@Valid @RequestBody ReservationRequest reservationRequest){
        try {
            logger.info("------ Reservations added successfully ------");
            reservationServiceImpl.save(reservationRequest);
            return new ResponseEntity(HttpStatus.OK);
        } catch (WrongDataException wrongDataException) {
            logger.error("------ Reservation Add Error ------");
            return new ResponseEntity(new MessageResponse(wrongDataException.getError()), HttpStatus.BAD_REQUEST);
        }
    }

    //Zwraca aktualne rezerwacje usera po id usera
    /**
     * This method get all current reservation user about id user.
     * This method use endpoint /reservation/get-all-user.
     * @param id id user.
     * @return list reservation Http.Status 200 or 400.
     * @exception WrongDataException when user not exist.
     */
    @ResponseBody
    @GetMapping(value = "/get-all-user")
    public ResponseEntity<?> getReservationById(@RequestParam Long id){
        try {
            logger.info("------ Successfully displayed user bookings ------");
            return new ResponseEntity(reservationServiceImpl.getCurrentReservation(id), HttpStatus.OK);
        } catch (WrongDataException wrongDataException) {
            logger.error("------ User Not Exist ------");
            return new ResponseEntity(new MessageResponse(wrongDataException.getError()), HttpStatus.BAD_REQUEST);
        }
    }
}
