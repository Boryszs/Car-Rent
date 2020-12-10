package com.Server.dto.Response;

import java.util.Date;

public class CarReservationResponse {
    private String mark;
    private String model;
    private String localizationStart;
    private String localizationEnd;
    private Date dateFrom;
    private Date dateTo;
    private float price;

    public CarReservationResponse() {
    }

    public CarReservationResponse(String mark, String model, String localizationStart, String localizationEnd, Date dateFrom, Date dateTo, float price) {
        this.mark = mark;
        this.model = model;
        this.localizationStart = localizationStart;
        this.localizationEnd = localizationEnd;
        this.dateFrom = dateFrom;
        this.dateTo = dateTo;
        this.price = price;
    }


    public String getMark() {
        return mark;
    }

    public String getModel() {
        return model;
    }

    public String getLocalizationStart() {
        return localizationStart;
    }

    public String getLocalizationEnd() {
        return localizationEnd;
    }

    public Date getDateFrom() {
        return dateFrom;
    }

    public Date getDateTo() {
        return dateTo;
    }

    public float getPrice() {
        return price;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public void setLocalizationStart(String localizationStart) {
        this.localizationStart = localizationStart;
    }

    public void setLocalizationEnd(String localizationEnd) {
        this.localizationEnd = localizationEnd;
    }

    public void setDateFrom(Date dateFrom) {
        this.dateFrom = dateFrom;
    }

    public void setDateTo(Date dateTo) {
        this.dateTo = dateTo;
    }

    public void setPrice(float price) {
        this.price = price;
    }
}
