package com.Server.controller;

import com.Server.dto.Request.AddReservationRequest;
import com.Server.dto.Response.MessageResponse;
import com.Server.exception.ExceptionRequest;
import com.Server.model.Reservation;
import com.Server.model.User;
import com.Server.service.CarService;
import com.Server.service.LocalizationService;
import com.Server.service.ReservationService;
import com.Server.service.UserService;
import com.Server.service.impl.SendMail;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RequestMapping(value = "/reservation")
@RestController
@CrossOrigin
public class ReservationController {

    private static final Logger logger = LoggerFactory.getLogger(ReservationController.class);
    private ReservationService reservationServiceImpl;
    private UserService userServiceImpl;
    private CarService carServiceImpl;
    private LocalizationService localizationServiceImpl;
    private SendMail sendMail;

    @Autowired
    public ReservationController(ReservationService reservationServiceImpl, UserService userServiceImpl, CarService carServiceImpl, LocalizationService localizationServiceImpl, SendMail sendMail) {
        this.reservationServiceImpl = reservationServiceImpl;
        this.userServiceImpl = userServiceImpl;
        this.carServiceImpl = carServiceImpl;
        this.localizationServiceImpl = localizationServiceImpl;
        this.sendMail = sendMail;
    }


    //Zwracanie rezerwacji wszystkich
    @ResponseBody
    @GetMapping(value = "/show")
    public List<Reservation> getReservations() {
        return reservationServiceImpl.findAll();
    }


    //TODO FIX DELETE
    @PostMapping(value = "/delete")
    public ResponseEntity<?> deleteReservation(@RequestParam Long id) {
        if (reservationServiceImpl.existsByIdrent(id)) {
            User user = userServiceImpl.findByReservations_Idrent(id);
            for (int i = 0; i < user.getReservations().size(); i++) {
                if (user.getReservations().get(i).getIdrent() == id) {
                    user.getReservations().remove(i);
                }
            }
            //userServiceImpl.save(user);
            reservationServiceImpl.deleteByIdrent(id);
            return new ResponseEntity(user, HttpStatus.OK);

        } else {
            return new ResponseEntity(new MessageResponse("Brak takiego zamowienia"), HttpStatus.BAD_REQUEST);
        }
    }


    //Zwracanie rezerwacji po id usera
    @ResponseBody
    @GetMapping(value = "/get")
    public ResponseEntity<?> getReservation(@RequestParam Long id) {
        try {
            return new ResponseEntity(userServiceImpl.getReservationUser(id), HttpStatus.OK);
        } catch (ExceptionRequest exceptionRequest) {
            logger.error("------ Reservation Id Not Exist To Get ------");
            return new ResponseEntity(new MessageResponse(exceptionRequest.getErr()), HttpStatus.BAD_REQUEST);
        }
    }

    //Dodawanie rezerwacji
    @ResponseBody
    @PostMapping(value = "/add")
    public ResponseEntity<?> addReservation(@Valid @RequestBody AddReservationRequest addReservationRequest){
        try {
            return new ResponseEntity(reservationServiceImpl.save(addReservationRequest), HttpStatus.OK);
        } catch (ExceptionRequest exceptionRequest) {
            logger.error("------ Reservation Add Error ------");
            return new ResponseEntity(new MessageResponse(exceptionRequest.getErr()), HttpStatus.BAD_REQUEST);
        }
    }

    //Zwraca aktualne rezerwacje usera po id usera
    @ResponseBody
    @GetMapping(value = "/get-all-user")
    public ResponseEntity<?> getReservationById(@RequestParam Long id){
        try {
            return new ResponseEntity(reservationServiceImpl.getCurrentReservation(id), HttpStatus.OK);
        } catch (ExceptionRequest exceptionRequest) {
            logger.error("------ User Not Exist ------");
            return new ResponseEntity(new MessageResponse(exceptionRequest.getErr()), HttpStatus.BAD_REQUEST);
        }
    }
}