package com.Server.service.impl;

import com.Server.dto.Request.AddCarRequest;
import com.Server.dto.Request.EditCarRequest;
import com.Server.dto.Request.QuestionCarRequest;
import com.Server.exception.ExceptionRequest;
import com.Server.model.Car;
import com.Server.model.Localization;
import com.Server.model.Reservation;
import com.Server.model.User;
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
import java.util.Optional;
import java.util.stream.IntStream;
/**
 * Class Service implements interface CarService.
 * @author Krystian Cwioro Kamil Bieniasz Damian Mierzynski.
 * @version 1.0
 * @since 2020-12-29.
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

    @Autowired
    /**Constructor*/
    public CarServiceImpl(CarRepository carRepository, ReservationRepository reservationRepository, LocalizationRepository localizationRepository, UserRepository userRepository) {
        this.carRepository = carRepository;
        this.reservationRepository = reservationRepository;
        this.localizationRepository = localizationRepository;
        this.userRepository = userRepository;
    }

    /**
     * Method find all car.
     * @return List Car.
     */
    @Override
    public List<Car> findAll() {
        return carRepository.findAll();
    }

    /**
     * Method to save new car.
     * @param addCarRequest new data car.
     * @return return new added car.
     * @throws ExceptionRequest when request has wrong localization.
     */
    @Override
    public Car save(AddCarRequest addCarRequest) throws ExceptionRequest {
        if (!localizationRepository.existsByCity(addCarRequest.getCity())) {
            throw new ExceptionRequest("Wrong localization!!!");
        }
        Localization localization = localizationRepository.findByCity(addCarRequest.getCity()).get();
        Car car = new Car(addCarRequest.getMark(), addCarRequest.getModel(), addCarRequest.getType(), addCarRequest.getYearProduction(), addCarRequest.getColor(), addCarRequest.getEngine(), addCarRequest.getMoney(), localization, addCarRequest.getImage());
        return carRepository.save(car);

    }

    /**
     * Method find car on id
     * @param id id on find car
     * @return car on id.
     * @throws ExceptionRequest when car not exist.
     */
    @Override
    public Optional<Car> findByIdCar(int id) throws ExceptionRequest {
        if (carRepository.existsByIdcar(id)) {
            return carRepository.findByIdcar(id);
        } else {
            throw new ExceptionRequest("Wrong car");
        }

    }

    /**
     * Method to edit data car.
     * @param editCar data new car.
     * @return new data update car.
     * @throws ExceptionRequest where id car not exist.
     */
    @Override
    public Car update(EditCarRequest editCar) throws ExceptionRequest {
        if (carRepository.existsByIdcar(editCar.getIdcar())) {
            Car car = carRepository.findByIdcar(editCar.getIdcar()).get();
            car.setMark(editCar.getMark());
            car.setType(editCar.getType());
            car.setModel(editCar.getModel());
            car.setYearProduction(editCar.getYearProduction());
            car.setColor(editCar.getColor());
            car.setEngineCapacity(editCar.getEngine());
            car.setImage(editCar.getImage());
            car.setLocalization((localizationRepository.findByCity(car.getLocalization().getCity()).get()));
            return carRepository.save(car);
        } else {
            throw new ExceptionRequest("Wrong car!!!");
        }
    }

    /**
     * Method car not order.
     * @param questionCarRequest question on city and date reservation.
     * @return List car not order ar.
     * @throws ExceptionRequest when localization is wrong.
     */
    @Override
    public List<Car> getCarNotOrder(QuestionCarRequest questionCarRequest) throws ExceptionRequest {
        if(!localizationRepository.existsByCity(questionCarRequest.getCity())){
            throw new ExceptionRequest("Wrong localization!!!");
        }else {
            List<Car> cars = carRepository.findByLocalizationCity(questionCarRequest.getCity());
            List<Car> carsEmpty = carRepository.findByLocalizationCity(questionCarRequest.getCity());
            for (Car car : cars) {
                if (reservationRepository.existsByCar_Idcar(car.getIdcar())) {
                    List<Reservation> reservation = reservationRepository.findByCar_Idcar(car.getIdcar());
                    for (Reservation reser : reservation) {
                        if (reser.getDataFrom().compareTo(new Date(questionCarRequest.getDateTo())) * new Date(questionCarRequest.getDateTo()).compareTo(reser.getDataTo()) >= 0) {
                            carsEmpty.remove(car);
                        }
                        if (reser.getDataFrom().compareTo(new Date(questionCarRequest.getDateFrom())) * new Date(questionCarRequest.getDateFrom()).compareTo(reser.getDataTo()) >= 0) {
                            carsEmpty.remove(car);
                        }
                        if (reser.getDataFrom().after(new Date(questionCarRequest.getDateFrom())) && reser.getDataTo().before(new Date(questionCarRequest.getDateTo()))) {
                            carsEmpty.remove(car);
                        }
                    }
                }
            }
            return carsEmpty;
        }
    }

    /**
     * Delete car on id.
     * @param id id car to delete.
     * @return return id deleting car.
     * @throws ExceptionRequest when id car is wrong.
     */
    @Override
    public void deleteCar(int id) throws ExceptionRequest {
        if (!carRepository.existsByIdcar(id)) {
            throw new ExceptionRequest("Wrong car");
        } else {
            List<Reservation> reservations = reservationRepository.findAll();
            IntStream.range(0, reservations.size()).filter(i -> reservations.get(i).getCar().getIdcar() == id).forEachOrdered(i -> {
                User user = userRepository.findByReservations_Idrent(reservations.get(i).getIdrent());
                user.getReservations().remove(reservations.get(i));
                userRepository.save(user);
                reservationRepository.deleteByIdrent(reservations.get(i).getIdrent());
            });
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
     * @throws ExceptionRequest when id car not exist.
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
    public List<Car> findByLocalizationId(Long id) {
        return carRepository.findByLocalizationId(id);
    }

    /**
     * Find car on localization on name city.
     * @param city name city.
     * @return return List car on localization city.
     * @throws ExceptionRequest when city name not exist.
     */
    @Override
    public List<Car> findByLocalizationCity(String city) throws ExceptionRequest {
        if (localizationRepository.existsByCity(city)) {
            return carRepository.findByLocalizationCity(city);
        } else {
            throw new ExceptionRequest("Wrong city");
        }
    }


}
