package com.Server.service.impl;

import com.Server.dto.Request.AddCarRequest;
import com.Server.dto.Request.EditCarRequest;
import com.Server.dto.Request.QuestionCarRequest;
import com.Server.exception.ExceptionRequest;
import com.Server.model.Car;
import com.Server.model.Localization;
import com.Server.model.Reservation;
import com.Server.repository.CarRepository;
import com.Server.repository.LocalizationRepository;
import com.Server.repository.ReservationRepository;
import com.Server.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class CarServiceImpl implements CarService {

    private CarRepository carRepository;
    private ReservationRepository reservationRepository;
    private LocalizationRepository localizationRepository;


    @Autowired
    public CarServiceImpl(CarRepository carRepository, ReservationRepository reservationRepository, LocalizationRepository localizationRepository) {
        this.carRepository = carRepository;
        this.reservationRepository = reservationRepository;
        this.localizationRepository = localizationRepository;
    }


    @Override
    public List<Car> findAll() {
        return carRepository.findAll();
    }

    @Override
    public Car save(AddCarRequest addCarRequest) throws ExceptionRequest {
        if (!localizationRepository.existsByCity(addCarRequest.getCity())) {
            throw new ExceptionRequest("Wrong localization!!!");
        }
        Localization localization = localizationRepository.findByCity(addCarRequest.getCity()).get();
        Car car = new Car(addCarRequest.getMark(), addCarRequest.getModel(), addCarRequest.getType(), addCarRequest.getYearProduction(), addCarRequest.getColor(), addCarRequest.getEngine(), addCarRequest.getMoney(), localization, addCarRequest.getImage());
        return carRepository.save(car);

    }

    @Override
    public Optional<Car> findByIdcar(int id) throws ExceptionRequest {
        if (carRepository.existsByIdcar(id)) {
            return carRepository.findByIdcar(id);
        } else {
            throw new ExceptionRequest("Wrong car");
        }

    }

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

    @Override
    public boolean existsByIdcar(int id) {
        return carRepository.existsByIdcar(id);
    }

    @Override
    public Integer deleteByIdcar(int id) {
        return carRepository.deleteByIdcar(id);
    }

    @Override
    public List<Car> findByLocalizationId(Long id) {
        return carRepository.findByLocalizationId(id);
    }

    @Override
    public List<Car> findByLocalizationCity(String city) throws ExceptionRequest {
        if (localizationRepository.existsByCity(city)) {
            return carRepository.findByLocalizationCity(city);
        } else {
            throw new ExceptionRequest("Wrong city");
        }
    }


}
