package com.Server.controller;

import com.Server.dto.Request.ReservationRequest;
import com.Server.dto.Response.MessageResponse;
import com.Server.dto.Response.ReservationResponse;
import com.Server.entiy.Reservation;
import com.Server.exception.WrongDataException;
import com.Server.service.*;
import com.Server.service.impl.SendMailImpl;
import com.google.common.io.ByteStreams;
import com.itextpdf.text.DocumentException;
import jdk.incubator.jpackage.internal.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * ReservationController is use to supports operations about database table Reservation.
 *
 * @author Krystian Cwioro Kamil Bieniasz Damian Mierzynski.
 * @version 2.0.
 * @since 2020-04-27.
 */

@RequestMapping(value = "/reservation")
@RestController
@CrossOrigin
public class ReservationController {
    /**
     * Logger use to logger on server.
     */
    private static final Logger logger = LoggerFactory.getLogger(ReservationController.class);
    /**
     * Pdf generated
     */
    private final PdfResume pdfResume;
    /**
     * ReservationService operation on database table Reservation
     */
    private final ReservationService reservationServiceImpl;
    /**
     * UserService operation on database table User
     */
    private final UserService userServiceImpl;
    /**
     * CarService operation on database table Car
     */
    private final CarService carServiceImpl;
    /**
     * LocationSercive operation on database table Localization
     */
    private final LocalizationService localizationServiceImpl;
    /**
     * SendMail use to send mail
     */
    private final SendMailImpl sendMailImpl;

    /**
     * Constructor
     */
    @Autowired
    public ReservationController(PdfResume pdfResume, ReservationService reservationServiceImpl, UserService userServiceImpl, CarService carServiceImpl, LocalizationService localizationServiceImpl, SendMailImpl sendMailImpl) {
        this.pdfResume = pdfResume;
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
     *
     * @return List Reservation all.
     */
    @ResponseBody
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping(value = "/show")
    public ResponseEntity<List<ReservationResponse>> getReservations() {
        return new ResponseEntity<>(reservationServiceImpl.findAll(), HttpStatus.OK);
    }


    /**
     * This method delete reservation.
     * This method use endpoint /reservation/delete.
     *
     * @param id id reservation.
     * @return return id delete reservation Http.Status 200 or 400.
     * @throws WrongDataException when reservation id not exist.
     */
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping(value = "/delete")
    public ResponseEntity<?> deleteReservation(@RequestParam Long id) {
        logger.info("------ The reservation was successfully deleted ------");
        reservationServiceImpl.deleteByIdRent(id);
        return new ResponseEntity(HttpStatus.OK);
    }

    //Zwracanie rezerwacji po id usera

    /**
     * This method get all reservation user about id user.
     * This method use endpoint /reservation/get.
     *
     * @param id id user.
     * @return list reservation Http.Status 200 or 400.
     * @throws WrongDataException when reservation id not exist.
     */
    @ResponseBody
    @GetMapping(value = "/get")
    public ResponseEntity<?> getReservation(@RequestParam Long id) {
        logger.info("------ Reservations displayed successfully ------");
        return new ResponseEntity(userServiceImpl.getReservationUser(id), HttpStatus.OK);
    }

    //Generowanie PDf

    /**
     * This method gnerate resume on reservation
     * This method use endpoint /reservation/pdf.
     *
     * @param reservationRequest data to create resume
     */
    @PostMapping(value = "/add-pdf")
    public ResponseEntity<?> addReservationGenerateFile(HttpServletResponse response, @Valid @RequestBody ReservationRequest reservationRequest) throws IOException, DocumentException {
        logger.info("------ Reservations added generate pdf successfully ------");
        Reservation reservation = reservationServiceImpl.save(reservationRequest);
        String filename = new SimpleDateFormat("dd-M-yyyy hh:mm:ss").format(new Date()).toString();
        response.setContentType("application/pdf");
        response.setHeader("Content-Disposition", "attachment; filename=" + filename + ".pdf");
        response.setHeader("filename", filename + ".pdf");
        InputStream inputStream = pdfResume.generatePdf(reservation.getIdrent());
        ByteArrayResource resource = new ByteArrayResource(ByteStreams.toByteArray(inputStream));
        return new ResponseEntity<>(resource, HttpStatus.OK);
    }

    //Dodawanie rezerwacji

    /**
     * This method add new reservation user.
     * This method use endpoint /reservation/add.
     *
     * @param reservationRequest data new reservation.
     * @return return data new reservation Http.Status 200 or 400.
     * @throws WrongDataException when reservation add error.
     */

    @ResponseBody
    @PostMapping(value = "/add")
    public ResponseEntity<?> addReservation(HttpServletResponse response, @Valid @RequestBody ReservationRequest reservationRequest) throws IOException, DocumentException {
        logger.info("------ Reservations added successfully ------");
        reservationServiceImpl.save(reservationRequest);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    //Zwraca aktualne rezerwacje usera po id usera

    /**
     * This method get all current reservation user about id user.
     * This method use endpoint /reservation/get-all-user.
     *
     * @param id id user.
     * @return list reservation Http.Status 200 or 400.
     * @throws WrongDataException when user not exist.
     */
    @ResponseBody
    @GetMapping(value = "/get-all-user")
    public ResponseEntity<?> getReservationById(@RequestParam Long id) {
        logger.info("------ Successfully displayed user bookings ------");
        return new ResponseEntity(reservationServiceImpl.getCurrentReservation(id), HttpStatus.OK);
    }
}
