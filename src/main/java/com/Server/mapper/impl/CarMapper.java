package com.Server.mapper.impl;

import com.Server.dto.Request.CarRequest;
import com.Server.dto.Response.CarResponse;
import com.Server.dto.Response.LocalizationResponse;
import com.Server.entiy.Car;
import com.Server.mapper.Mapper;
import org.springframework.stereotype.Component;

/**
 * Class Mapper use to mapping Car.
 * @author Krystian Cwioro Kamil Bieniasz Damian Mierzynski.
 * @version 1.0
 * @since 2020-04-27
 */


@Component
public class CarMapper implements Mapper<Car,CarResponse,CarRequest>{

    /**
     *
     * @param car param mapped to dto
     * @return dto object
     */
    @Override
    public CarResponse toDto(final Car car) {
        return new CarResponse().builder()
                .idcar(car.getIdcar())
                .mark(car.getMark())
                .model(car.getModel())
                .type(car.getType())
                .yearProduction(car.getYearProduction())
                .color(car.getColor())
                .engineCapacity(car.getEngineCapacity())
                .money(car.getMoney())
                .image(car.getImage())
                .localization(new LocalizationResponse().builder()
                        .id(car.getLocalization().getId())
                        .city(car.getLocalization().getCity()).build()
                ).build();
    }

    /**
     *
     * @param carRequest param mapped to entity
     * @return car object
     */
    @Override
    public Car toEntity(final CarRequest carRequest) {
        return new Car().builder()
                .mark(carRequest.getMark())
                .model(carRequest.getModel())
                .type(carRequest.getType())
                .yearProduction(carRequest.getYearProduction())
                .color(carRequest.getColor())
                .engineCapacity(carRequest.getEngine())
                .money(carRequest.getMoney())
                .image(carRequest.getImage()).build();
    }

    /**
     *
     * @param car old param
     * @param carRequest new value param
     * @return new car object
     */
    @Override
    public Car update(Car car, final CarRequest carRequest) {
        car.setMark(carRequest.getMark());
        car.setModel(carRequest.getModel());
        car.setType(carRequest.getType());
        car.setYearProduction(carRequest.getYearProduction());
        car.setColor(carRequest.getColor());
        car.setEngineCapacity(carRequest.getEngine());
        car.setMoney(carRequest.getMoney());
        car.setImage(carRequest.getImage());
        return car;
    }
}
