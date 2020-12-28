package com.Server.service.impl;

import com.Server.dto.Request.AddReservationRequest;
import com.Server.dto.Response.CarReservationResponse;
import com.Server.dto.Response.MessageResponse;
import com.Server.exception.ExceptionRequest;
import com.Server.model.Car;
import com.Server.model.Reservation;
import com.Server.model.User;
import com.Server.repository.CarRepository;
import com.Server.repository.LocalizationRepository;
import com.Server.repository.ReservationRepository;
import com.Server.repository.UserRepository;
import com.Server.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.sql.Date;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ReservationServiceImpl implements ReservationService {

    private ReservationRepository reservationRepository;
    private UserRepository userRepository;
    private CarRepository carRepository;
    private LocalizationRepository localizationRepository;
    private SendMail sendMail;

    @Autowired
    public ReservationServiceImpl(ReservationRepository reservationRepository, UserRepository userRepository, CarRepository carRepository, LocalizationRepository localizationRepository, SendMail sendMail) {
        this.reservationRepository = reservationRepository;
        this.userRepository = userRepository;
        this.carRepository = carRepository;
        this.localizationRepository = localizationRepository;
        this.sendMail = sendMail;
    }


    @Override
    public Optional<Reservation> findByIdrent(Long id) {
        return reservationRepository.findByIdrent(id);
    }

    @Override
    public boolean existsByIdrent(Long id) {
        return reservationRepository.existsByIdrent(id);
    }

    @Override
    public int deleteByIdrent(Long id){
        return reservationRepository.deleteByIdrent(id);
    }

    @Override
    public List<Reservation> getCurrentReservation(Long id) throws ExceptionRequest {
        if (!userRepository.existsById(id)) {
            throw new ExceptionRequest("User not exist !!!");
        } else {
            User user = userRepository.findById(id).get();
            List<Reservation> reservations = user.getReservations();
            List<Reservation> reservationCurrent = new LinkedList<>();
            LocalDate date =LocalDate.now();
            for (Reservation reservation : reservations) {
                if (reservation.getDataFrom().compareTo(Date.valueOf(date)) * Date.valueOf(date).compareTo(reservation.getDataTo()) >= 0 ) {
                    reservationCurrent.add(reservation);
                }
            }
            return reservationCurrent;
        }
    }

    @Override
    public Integer deleteReservation(Long id) throws ExceptionRequest {
        if (reservationRepository.existsByIdrent(id)) {
            User user = userRepository.findByReservations_Idrent(id);
            for (int i = 0; i < user.getReservations().size(); i++) {
                if (user.getReservations().get(i).getIdrent() == id) {
                    user.getReservations().remove(i);
                }
            }
            userRepository.save(user);
            return  reservationRepository.deleteByIdrent(id);
        } else {
            throw new ExceptionRequest("Reservation not exist !!!");
        }
    }

    @Override
    public boolean existsByCar_Idcar(int id) {
        return reservationRepository.existsByCar_Idcar(id);
    }

    @Override
    public CarReservationResponse save(AddReservationRequest addReservationRequest) throws ExceptionRequest {
        if (!carRepository.existsByIdcar(addReservationRequest.getId_car())) {
            throw new ExceptionRequest("Wrong car!!!");
        }
        if (!userRepository.existsById(addReservationRequest.getId_user())) {
            throw new ExceptionRequest("User not exist!!!");
        }
        if (!localizationRepository.existsByCity(addReservationRequest.getLocalization_start())) {
            throw new ExceptionRequest("Localization start not exist!!!");
        }
        if (!localizationRepository.existsByCity(addReservationRequest.getLocalization_end())) {
            throw new ExceptionRequest("Localization end not exist!!!");
        } else {
            User user = userRepository.findById(addReservationRequest.getId_user()).get();
            Car car = carRepository.findByIdcar(addReservationRequest.getId_car()).get();
            LocalDate dateBefore = LocalDate.parse(addReservationRequest.getDatefrom().toString());
            LocalDate dateAfter = LocalDate.parse(addReservationRequest.getDateto().toString());
            long noOfDaysBetween = ChronoUnit.DAYS.between(dateBefore, dateAfter);
            Reservation reservations = new Reservation(carRepository.findByIdcar(addReservationRequest.getId_car()).get(), user, Date.valueOf(addReservationRequest.getDatefrom()), Date.valueOf(addReservationRequest.getDateto()), localizationRepository.findByCity(addReservationRequest.getLocalization_start()).get(), localizationRepository.findByCity(addReservationRequest.getLocalization_end()).get(), (noOfDaysBetween * car.getMoney()));
            car.setLocalization(localizationRepository.findByCity(addReservationRequest.getLocalization_end()).get());
            carRepository.save(car);
            reservationRepository.save(reservations);
            user.setReservations(reservations);
            userRepository.save(user);
            //sendMail.sendMail(user.getUsername(), "Thank you for order car:" + car.getMark() + " " + car.getModel() + " for " + noOfDaysBetween + " days in localization " + car.getLocalization().getCity() + " for prices: " + (noOfDaysBetween * car.getMoney()));
            return new CarReservationResponse(reservations.getCar().getMark(), reservations.getCar().getModel(), reservations.getLocalizationStart().getCity(), reservations.getLocalizationEnd().getCity(), reservations.getDataTo(), reservations.getDataFrom(), reservations.getPrice());
        }

    }

    @Override
    public List<Reservation> findAll() {
        return reservationRepository.findAll();
    }

    @Override
    public List<Reservation> findByCar_Idcar(int id) {
        return reservationRepository.findByCar_Idcar(id);
    }

    @Override
    public Optional<Reservation> findFirstByCarIdcarOrderByIdrentDesc(int id) {
        return reservationRepository.findFirstByCarIdcarOrderByIdrentDesc(id);
    }
}
