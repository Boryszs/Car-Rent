package com.Server.dto.Request;

import org.hibernate.annotations.Type;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

public class QuestionCarRequest {

    @NotBlank
    private String city;
    @Temporal(TemporalType.TIMESTAMP)
    @Type(type = "date")
    @NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date dateFrom;
    @Temporal(TemporalType.TIMESTAMP)
    @Type(type = "date")
    @NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date dateTo;

    public QuestionCarRequest() {
    }

    public QuestionCarRequest(String city, Date dateFrom, Date dateTo) {
        this.city = city;
        this.dateFrom = dateFrom;
        this.dateTo = dateTo;
    }

    public String getCity() {
        return city;
    }

    public Date getDateFrom() {
        return dateFrom;
    }

    public Date getDateTo() {
        return dateTo;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setDateFrom(Date dateFrom) {
        this.dateFrom = dateFrom;
    }

    public void setDateTo(Date dateTo) {
        this.dateTo = dateTo;
    }
}
