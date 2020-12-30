package com.Server.dto.Response;

import java.util.Date;

/**
 * Class DTO get Reservation.
 * @author Krystian Cwioro Kamil Bieniasz Damian Mierzynski.
 * @version 1.0
 * @since 2020-12-29.
 */

public class CarReservationResponse {
    /**mark*/
    private String mark;
    /**model*/
    private String model;
    /**localizationStart*/
    private String localizationStart;
    /**localizationEnd*/
    private String localizationEnd;
    /**dateFrom*/
    private Date dateFrom;
    /**dateTo*/
    private Date dateTo;
    /**price*/
    private float price;

    /**Constructor*/
    public CarReservationResponse() {
    }

    /**Constructor*/
    public CarReservationResponse(String mark, String model, String localizationStart, String localizationEnd, Date dateFrom, Date dateTo, float price) {
        this.mark = mark;
        this.model = model;
        this.localizationStart = localizationStart;
        this.localizationEnd = localizationEnd;
        this.dateFrom = dateFrom;
        this.dateTo = dateTo;
        this.price = price;
    }

    /**
     *
     * @return Mark
     */
    public String getMark() {
        return mark;
    }

    /**
     *
     * @return Model
     */
    public String getModel() {
        return model;
    }

    /**
     *
     * @return LocalizationStart
     */
    public String getLocalizationStart() {
        return localizationStart;
    }

    /**
     *
     * @return LocalizationEnd
     */
    public String getLocalizationEnd() {
        return localizationEnd;
    }

    /**
     *
     * @return DateFrom
     */
    public Date getDateFrom() {
        return dateFrom;
    }

    /**
     *
     * @return DateTo
     */
    public Date getDateTo() {
        return dateTo;
    }

    /**
     *
     * @return Price
     */
    public float getPrice() {
        return price;
    }

    /**
     *
     * @param mark setMark
     */
    public void setMark(String mark) {
        this.mark = mark;
    }

    /**
     *
     * @param model setModel
     */
    public void setModel(String model) {
        this.model = model;
    }

    /**
     *
     * @param localizationStart setLocalizationStart
     */
    public void setLocalizationStart(String localizationStart) {
        this.localizationStart = localizationStart;
    }

    /**
     *
     * @param localizationEnd setLocalizationEnd
     */
    public void setLocalizationEnd(String localizationEnd) {
        this.localizationEnd = localizationEnd;
    }

    /**
     *
     * @param dateFrom setDateFrom
     */
    public void setDateFrom(Date dateFrom) {
        this.dateFrom = dateFrom;
    }

    /**
     *
     * @param dateTo setDateTo
     */
    public void setDateTo(Date dateTo) {
        this.dateTo = dateTo;
    }

    /**
     *
     * @param price setPrice
     */
    public void setPrice(float price) {
        this.price = price;
    }
}
