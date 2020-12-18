package com.Server.dto.Request;

import javax.validation.constraints.NotBlank;


public class QuestionCarRequest {

    @NotBlank
    private String city;
    @NotBlank
    private String dateFrom;
    @NotBlank
    private String dateTo;

    public QuestionCarRequest() {
    }

    public QuestionCarRequest(String city, String dateFrom, String dateTo) {
        this.city = city;
        this.dateFrom = dateFrom;
        this.dateTo = dateTo;
    }

    public String getCity() {
        return city;
    }

    public String getDateFrom() {
        return dateFrom;
    }

    public String getDateTo() {
        return dateTo;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setDateFrom(String dateFrom) {
        this.dateFrom = dateFrom;
    }

    public void setDateTo(String dateTo) {
        this.dateTo = dateTo;
    }
}
