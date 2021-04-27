package com.Server.service.impl;

import com.Server.dto.Request.CarRequest;
import com.Server.dto.Response.CarResponse;
import com.Server.exception.WrongDataException;
import com.Server.mapper.Mapper;
import com.Server.model.Car;
import com.Server.model.Localization;
import com.Server.model.Reservation;
import com.Server.repository.CarRepository;
import com.Server.repository.LocalizationRepository;
import com.Server.repository.ReservationRepository;
import com.Server.repository.UserRepository;
import com.Server.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Class Service implements interface CarService.
 * @author Krystian Cwioro Kamil Bieniasz Damian Mierzynski.
 * @version 2.0.
 * @since 2020-04-27.
 */
@Service
@Transactional
public class CarServiceImpl implements CarService {

    /**carRepository*/
    private final CarRepository carRepository;
    /**reservationRepository*/
    private final ReservationRepository reservationRepository;
    /**localizationRepository*/
    private final LocalizationRepository localizationRepository;
    /**userRepository*/
    private final UserRepository  userRepository;
    /**CarMapper*/
    private final Mapper<Car,CarResponse,CarRequest> carMapper;

    @Autowired
    /**Constructor*/
    public CarServiceImpl(CarRepository carRepository, ReservationRepository reservationRepository, LocalizationRepository localizationRepository, UserRepository userRepository, Mapper<Car,CarResponse,CarRequest> carMapper) {
        this.carRepository = carRepository;
        this.reservationRepository = reservationRepository;
        this.localizationRepository = localizationRepository;
        this.userRepository = userRepository;
        this.carMapper = carMapper;
    }

    /**
     * Method find all car.
     * @return List Car.
     */
    @Override
    public List<CarResponse> findAll() {
        return carRepository.findAll().parallelStream().map(car -> carMapper.toDto(car)).collect(Collectors.toList());
    }

    /**
     * Method to save new car.
     * @param carRequest new data car.
     * @return return new added car.
     * @throws WrongDataException when request has wrong localization.
     */
    @Override
    public void save(CarRequest carRequest) throws WrongDataException {
        if (!localizationRepository.existsByCity(carRequest.getCity())) {
            throw new WrongDataException("Wrong localization!!!");
        }
        Localization localization = localizationRepository.findByCity(carRequest.getCity()).get();
        Car car = carMapper.toEntity(carRequest);
        car.setLocalization(localization);
        carRepository.save(car);
    }

    /**
     * Method find car on id
     * @param id id on find car
     * @return car on id.
     * @throws WrongDataException when car not exist.
     */
    @Override
    public CarResponse findByIdCar(int id) throws WrongDataException {
        if (carRepository.existsByIdcar(id)) {
            return carMapper.toDto(carRepository.findByIdcar(id).get());
        } else {
            throw new WrongDataException("Wrong car");
        }

    }

    /**
     * Method to edit data car.
     * @param carRequest data new car.
     * @return new data update car.
     * @throws WrongDataException where id car not exist.
     */
    @Override
    public Car update(int id,CarRequest carRequest) throws WrongDataException {
        if (carRepository.existsByIdcar(id)) {
            Car car = carRepository.findByIdcar(id).get();
            car = carMapper.update(car,carRequest);
            car.setLocalization((localizationRepository.findByCity(car.getLocalization().getCity()).get()));
            return carRepository.save(car);
        } else {
            throw new WrongDataException("Wrong car!!!");
        }
    }

    /**
     * Delete car on id.
     * @param id id car to delete.
     * @return return id deleting car.
     * @throws WrongDataException when id car is wrong.
     */
    @Override
    public void deleteCar(int id) throws WrongDataException {
        if (!carRepository.existsByIdcar(id)) {
            throw new WrongDataException("Wrong car");
        } else {
            carRepository.deleteByIdcar(id);
        }
    }

    /**
     * Method check whether exist car on id.
     * @param id id car to check.
     * @return true or false.
     */
    @Override
    public boolean existsByIdCar(int id) {
        return carRepository.existsByIdcar(id);
    }

    /**
     * Delete car on id.
     * @param id id car to delete.
     * @return return id deleting car.
     * @throws WrongDataException when id car not exist.
     */
    @Override
    public Integer deleteByIdCar(int id) {
        return carRepository.deleteByIdcar(id);
    }

    /**
     * Find car on localization.
     * @param id id localization.
     * @return return List car witch id localization.
     */
    @Override
    public List<CarResponse> findByLocalizationId(Long id) {
        return carRepository.findByLocalizationId(id).parallelStream().map(car -> carMapper.toDto(car)).collect(Collectors.toList());
    }

    /**
     * Find car on localization on name city.
     * @param city name city.
     * @return return List car on localization city.
     * @throws WrongDataException when city name not exist.
     */
    @Override
    public List<CarResponse> findByLocalizationCity(String city) throws WrongDataException {
        if (localizationRepository.existsByCity(city)) {
            return carRepository.findByLocalizationCity(city).parallelStream().map(car -> carMapper.toDto(car)).collect(Collectors.toList());
        } else {
            throw new WrongDataException("Wrong city");
        }
    }

}
