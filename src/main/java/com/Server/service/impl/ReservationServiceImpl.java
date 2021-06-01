package com.Server.service.impl;

import com.Server.dto.Request.ReservationRequest;
import com.Server.dto.Response.ReservationResponse;
import com.Server.entiy.Car;
import com.Server.entiy.Reservation;
import com.Server.entiy.User;
import com.Server.exception.WrongDataException;
import com.Server.mapper.Mapper;
import com.Server.repository.CarRepository;
import com.Server.repository.LocalizationRepository;
import com.Server.repository.ReservationRepository;
import com.Server.repository.UserRepository;
import com.Server.service.ReservationService;
import com.Server.service.SendMail;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.sql.Date;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Class Service implements interface LocalizationService.
 *
 * @author Krystian Cwioro Kamil Bieniasz Damian Mierzynski.
 * @version 2.0.
 * @since 2020-04-27.
 */

@Service
@Slf4j
@Transactional
public class ReservationServiceImpl implements ReservationService {

    /**
     * reservationRepository
     */
    private final ReservationRepository reservationRepository;
    /**
     * userRepository
     */
    private final UserRepository userRepository;
    /**
     * carRepository
     */
    private final CarRepository carRepository;
    /**
     * localizationRepository
     */
    private final LocalizationRepository localizationRepository;
    /**
     * sendMail
     */
    private final SendMailImpl sendMailImpl;
    /**
     * Reservation mapper
     */
    private final Mapper<Reservation, ReservationResponse, ReservationRequest> reservationMapper;
    /**
    * sendMail mail sender
    */
    private final SendMail sendMail;

    /**
     *  @param reservationRepository
     * @param userRepository
     * @param carRepository
     * @param localizationRepository
     * @param sendMailImpl
     * @param reservationMapper
     * @param sendMail
     */
    @Autowired
    /**Constructor*/
    public ReservationServiceImpl(ReservationRepository reservationRepository, UserRepository userRepository, CarRepository carRepository, LocalizationRepository localizationRepository, SendMailImpl sendMailImpl, Mapper<Reservation, ReservationResponse, ReservationRequest> reservationMapper, SendMail sendMail) {
        this.reservationRepository = reservationRepository;
        this.userRepository = userRepository;
        this.carRepository = carRepository;
        this.localizationRepository = localizationRepository;
        this.sendMailImpl = sendMailImpl;
        this.reservationMapper = reservationMapper;
        this.sendMail = sendMail;
    }

    /**
     * Find reservation on id
     *
     * @param id id reservation.
     * @return reservation data.
     */
    @Override
    public ReservationResponse findByIdRent(Long id) {
        log.info("---- FIND RENT ID "+id+" ----");
        return reservationMapper.toDto(reservationRepository.findByIdrent(id).get());
    }


    /**
     * Delete reservation on id.
     *
     * @param id id reservation.
     * @return return int on id delete reservation.
     */
    @Override
    public void deleteByIdRent(Long id) {
        log.info("---- DELETE RENT ID "+id+" ----");
        reservationRepository.deleteByIdrent(id);
    }

    /**
     * Return Current reservation user on id user.
     *
     * @param id id user.
     * @return List current reservation.
     * @throws WrongDataException when user id not exist.
     */
    @Override
    public List<ReservationResponse> getCurrentReservation(Long id) throws WrongDataException {
        if (!userRepository.existsById(id)) {
            log.error("---- USER NOT EXIST ----");
            throw new WrongDataException("User not exist !!!");
        }
        log.info("---- FIND CURRENT RENT USER ID "+id+" ----");
        return reservationRepository.findCurrent(id).stream().map(reservation -> reservationMapper.toDto(reservation)).collect(Collectors.toList());
    }


    /**
     * Save new reservation
     *
     * @param reservationRequest data of new reservation.
     * @return data on new reservation.
     * @throws WrongDataException When data of request is wrong.
     */
    @Override
    public Reservation save(ReservationRequest reservationRequest) throws WrongDataException {
        if (!carRepository.existsByIdcar(reservationRequest.getIdCar())) {
            log.error("---- WRONG CITY ----");
            throw new WrongDataException("Wrong car!!!");
        }
        if (!userRepository.existsById(reservationRequest.getIdUser())) {
            log.error("---- USER NOT EXIST ----");
            throw new WrongDataException("User not exist!!!");
        }
        if (!localizationRepository.existsByCity(reservationRequest.getLocalizationStart())) {
            log.error("---- LOCALIZATION NOT EXIST ----");
            throw new WrongDataException("Localization start not exist!!!");
        }
        if (!localizationRepository.existsByCity(reservationRequest.getLocalizationEnd())) {
            log.error("---- LOCALIZATION NOT EXIST ----");
            throw new WrongDataException("Localization end not exist!!!");
        } else {
            User user = userRepository.findById(reservationRequest.getIdUser()).get();
            Car car = carRepository.findByIdcar(reservationRequest.getIdCar()).get();
            LocalDate dateBefore = LocalDate.parse(reservationRequest.getDateFrom());
            LocalDate dateAfter = LocalDate.parse(reservationRequest.getDateTo());
            long noOfDaysBetween = ChronoUnit.DAYS.between(dateBefore, dateAfter);
            Reservation reservations = new Reservation(carRepository.findByIdcar(reservationRequest.getIdCar()).get(), user, Date.valueOf(reservationRequest.getDateFrom()), Date.valueOf(reservationRequest.getDateTo()), localizationRepository.findByCity(reservationRequest.getLocalizationStart()).get(), localizationRepository.findByCity(reservationRequest.getLocalizationEnd()).get(), (noOfDaysBetween * car.getMoney()));
            //car.setLocalization(localizationRepository.findByCity(reservationRequest.getLocalizationEnd()).get()); Change localization on end = reservation
            //carRepository.save(car);
            reservationRepository.save(reservations);
            user.setReservations(reservations);
            userRepository.save(user);
            log.info("---- SAVE RENT ID ----");

            sendMail.sendMail(user.getId(), "Thank you for order car:" + car.getMark() + " " + car.getModel() + " for " + noOfDaysBetween + " days in localization " + car.getLocalization().getCity() + " for prices: " + (noOfDaysBetween * car.getMoney()));
            return reservations;
        }

    }


    /**
     * Method return all reservation.
     *
     * @return List all reservation.
     */
    @Override
    public List<ReservationResponse> findAll() {
        log.info("---- FIND ALL RESERVATION ----");
        return reservationRepository.findAll().stream().map(reservation -> reservationMapper.toDto(reservation)).collect(Collectors.toList());
    }


    /**
     * find a reservation on id car
     *
     * @param id id car
     * @return return List Reservation.
     */
    @Override
    public List<ReservationResponse> findByCarIdCar(int id) {
        log.info("---- FIND RESERVATION ON ID CAR ----");
        return reservationRepository.findByCar_Idcar(id).stream().map(reservation -> reservationMapper.toDto(reservation)).collect(Collectors.toList());
    }

    /**
     * Find the Last reservation on car
     *
     * @param id id car.
     * @return data reservation.
     */
    @Override
    public ReservationResponse findFirstByCarIdCarOrderByIdRentDesc(int id) {
        log.info("---- FIND LAST RESERVATION ON CAR ----");
        return reservationMapper.toDto(reservationRepository.findFirstByCarIdcarOrderByIdrentDesc(id).get());
    }
}
