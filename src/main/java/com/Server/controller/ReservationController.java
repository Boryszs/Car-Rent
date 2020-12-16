package com.Server.controller;

import com.Server.dto.Request.AddReservationRequest;
import com.Server.dto.Response.CarReservationResponse;
import com.Server.dto.Response.MessageResponse;
import com.Server.exception.ExceptionRequest;
import com.Server.model.Car;
import com.Server.model.Reservation;
import com.Server.model.User;
import com.Server.service.impl.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.sql.Date;
import java.util.LinkedList;
import java.util.List;

@RequestMapping(value = "/reservation")
@RestController
@CrossOrigin
public class ReservationController {

    @Autowired
    ReservationServiceImpl reservationServiceImpl;
    @Autowired
    UserServiceImpl userServiceImpl;
    @Autowired
    CarServiceImpl carServiceImpl;
    @Autowired
    LocalizationServiceImpl localizationServiceImpl;
    @Autowired
    SendMail sendMail;

    //Zwracanie rezerwacji
    @ResponseBody
    @GetMapping(value = "/show")
    public List<Reservation> getReservations() {
        return reservationServiceImpl.findAll();
    }


    @PostMapping(value = "/delete")
    public ResponseEntity<?> deleteReservation(@RequestParam Long id) {
        if (reservationServiceImpl.existsByIdrent(id)) {
            User user = userServiceImpl.findByReservations_Idrent(id);
            for (int i = 0; i < user.getReservations().size(); i++) {
                if (user.getReservations().get(i).getIdrent() == id) {
                    user.getReservations().remove(i);
                }
            }
            userServiceImpl.save(user);
            reservationServiceImpl.deleteByIdrent(id);
            return new ResponseEntity(user, HttpStatus.OK);

        } else {
            return new ResponseEntity(new MessageResponse("Brak takiego zamowienia"), HttpStatus.BAD_REQUEST);
        }
    }


    //Zwracanie rezerwacji po id
    @ResponseBody
    @GetMapping(value = "/get")
    public ResponseEntity<?> getReservation(@RequestParam Long id) {
        if (reservationServiceImpl.existsByIdrent(id)) {
            User user = userServiceImpl.findById(id).get();
            List<Reservation> reservations = user.getReservations();
            return new ResponseEntity(reservations, HttpStatus.OK);
        } else {
            try {
                throw new ExceptionRequest("Reservation not exist!!!");
            } catch (ExceptionRequest exceptionRequest) {
                return new ResponseEntity(new MessageResponse(exceptionRequest.getErr()), HttpStatus.BAD_REQUEST);
            }
        }
    }

    //Dodawanie rezerwacji
    @PostMapping(value = "/add")
    public ResponseEntity<?> addReservation(@Valid @RequestBody AddReservationRequest addReservationRequest) {

        if (carServiceImpl.existsByIdcar(addReservationRequest.getId_car())) {

            User user = userServiceImpl.findById(addReservationRequest.getId_user()).get();
            Car car = carServiceImpl.findByIdcar(addReservationRequest.getId_car()).get();
            LocalDate dateBefore = LocalDate.parse(addReservationRequest.getDatefrom().toString());
            LocalDate dateAfter = LocalDate.parse(addReservationRequest.getDateto().toString());
            long noOfDaysBetween = ChronoUnit.DAYS.between(dateBefore, dateAfter);
            Reservation reservations = new Reservation(carServiceImpl.findByIdcar(addReservationRequest.getId_car()).get(), user, Date.valueOf(addReservationRequest.getDatefrom()), Date.valueOf(addReservationRequest.getDateto()), localizationServiceImpl.findByCity(addReservationRequest.getLocalization_start()).get(), localizationServiceImpl.findByCity(addReservationRequest.getLocalization_end()).get(), (noOfDaysBetween * car.getMoney()));
            car.setLocalization(localizationServiceImpl.findByCity(addReservationRequest.getLocalization_end()).get());
            carServiceImpl.save(car);
            reservationServiceImpl.save(reservations);
            user.setReservations(reservations);
            userServiceImpl.save(user);
            sendMail.sendMail(user.getUsername(), "Thank you for order car:" + car.getMark() + " " + car.getModel() + " for " + noOfDaysBetween + " days in localization " + car.getLocalization().getCity() + " for prices: " + (noOfDaysBetween * car.getMoney()));
            return new ResponseEntity(new CarReservationResponse(reservations.getCar().getMark(), reservations.getCar().getModel(), reservations.getLocalizationStart().getCity(), reservations.getLocalizationEnd().getCity(), reservations.getDataTo(), reservations.getDataFrom(), reservations.getPrice()), HttpStatus.OK);
        } else {
            try {
                throw new ExceptionRequest("Wrong car!!!");
            } catch (ExceptionRequest exceptionRequest) {
                return new ResponseEntity(new MessageResponse(exceptionRequest.getErr()), HttpStatus.BAD_REQUEST);
            }
        }
    }

    @ResponseBody
    @GetMapping(value = "/get-all-user")
    public ResponseEntity<?> getReservationById(@RequestParam Long id) {
            User user = userServiceImpl.findById(id).get();
            List<Reservation> reservations = user.getReservations();
            List<Reservation> reservationCurrent = new LinkedList<>();
            java.util.Date date = new java.util.Date();
            for (Reservation reservation : reservations) {
                if (reservation.getDataFrom().compareTo(date) * date.compareTo(reservation.getDataTo()) >= 0) {
                    reservationCurrent.add(reservation);
                }
            }
            return new ResponseEntity(reservationCurrent, HttpStatus.OK);
    }
}