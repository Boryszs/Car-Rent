package com.Server.mapper.impl;

import com.Server.dto.Request.ReservationRequest;
import com.Server.dto.Response.CarResponse;
import com.Server.dto.Response.LocalizationResponse;
import com.Server.dto.Response.ReservationResponse;
import com.Server.mapper.Mapper;
import com.Server.model.Reservation;
import org.springframework.stereotype.Component;

@Component
public class ReservationMapper implements Mapper<Reservation, ReservationResponse, ReservationRequest> {
    @Override
    public ReservationResponse toDto(Reservation reservation) {
        return new ReservationResponse().builder()
                .idRent(reservation.getIdrent())
                .carResponse(new CarResponse().builder()
                        .idcar(reservation.getCar().getIdcar())
                        .mark(reservation.getCar().getMark())
                        .model(reservation.getCar().getModel())
                        .type(reservation.getCar().getType())
                        .yearProduction(reservation.getCar().getYearProduction())
                        .color(reservation.getCar().getColor())
                        .engineCapacity(reservation.getCar().getEngineCapacity())
                        .money(reservation.getCar().getMoney())
                        .image(reservation.getCar().getImage())
                        .localization(new LocalizationResponse().builder()
                                .id(reservation.getCar().getLocalization().getId())
                                .city(reservation.getCar().getLocalization().getCity()).build()
                        ).build())
                .dateTo(reservation.getDataTo().toString())
                .dateFrom(reservation.getDataFrom().toString())
                .localizationEnd(new LocalizationResponse().builder()
                        .id(reservation.getLocalizationEnd().getId())
                        .city(reservation.getLocalizationEnd().getCity())
                        .build())
                .localizationStart(new LocalizationResponse().builder()
                        .id(reservation.getLocalizationStart().getId())
                        .city(reservation.getLocalizationStart().getCity())
                        .build())
                .price(reservation.getPrice())
                .build();
    }

    @Override
    public Reservation toEntity(ReservationRequest reservationRequest) {
//        return new Reservation().builder()
//                .dataFrom(reservationRequest.getDateFrom())
//                .dataTo(reservationRequest.getDateTo())
//                .localizationStart(reservationRequest.getLocalizationStart())
//                .localizationEnd(reservationRequest.getLocalizationEnd())
//                .price()
//                .build();
        return null;
    }

    @Override
    public Reservation update(Reservation reservation, ReservationRequest reservationRequest) {
        return null;
    }
}
