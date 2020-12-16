package com.Server.dto.Request;


import org.hibernate.annotations.Type;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.Date;


public class AddReservationRequest {
    @NotNull
    @Min(1)
    private Long id_user;
    @NotNull
    @Min(1)
    private int id_car;

    private String dateto;

    private String datefrom;
    @NotBlank
    private String localization_end;
    @NotBlank
    private String localization_start;

    public AddReservationRequest() {
    }

    public AddReservationRequest(Long id_user, int id_car, String dateto, String datefrom, String localization_end, String localization_start) {
        this.id_user = id_user;
        this.id_car = id_car;
        this.dateto = dateto;
        this.datefrom = datefrom;
        this.localization_end = localization_end;
        this.localization_start = localization_start;
    }

    public Long getId_user() {
        return id_user;
    }

    public int getId_car() {
        return id_car;
    }

    public String getDateto() {
        return dateto;
    }

    public String getDatefrom() {
        return datefrom;
    }

    public String getLocalization_end() {
        return localization_end;
    }

    public String getLocalization_start() {
        return localization_start;
    }

    public void setId_user(Long id_user) {
        this.id_user = id_user;
    }

    public void setId_car(int id_car) {
        this.id_car = id_car;
    }

    public void setDateto(String dateto) {
        this.dateto = dateto;
    }

    public void setDatefrom(String datefrom) {
        this.datefrom = datefrom;
    }

    public void setLocalization_end(String localization_end) {
        this.localization_end = localization_end;
    }

    public void setLocalization_start(String localization_start) {
        this.localization_start = localization_start;
    }
}
