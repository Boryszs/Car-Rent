package com.Server.dto.Request;

import javax.validation.constraints.NotBlank;

/**
 * Class DTO use to query a reservation.
 * @author Krystian Cwioro Kamil Bieniasz Damian Mierzynski.
 * @version 1.0
 * @since 2020-12-29.
 */

public class QuestionCarRequest {

    @NotBlank
    /**city*/
    private String city;
    @NotBlank
    /**dateFrom*/
    private String dateFrom;
    @NotBlank
    /**dateTo*/
    private String dateTo;

    /**Constructor*/
    public QuestionCarRequest() {
    }

    /**Constructor*/
    public QuestionCarRequest(String city, String dateFrom, String dateTo) {
        this.city = city;
        this.dateFrom = dateFrom;
        this.dateTo = dateTo;
    }

    /**
     *
     * @return City
     */
    public String getCity() {
        return city;
    }

    /**
     *
     * @return DateFrom
     */
     public String getDateFrom() {
     return dateFrom;
     }

     /**
     *
     * @return DateTo
     */
    public String getDateTo() {
        return dateTo;
    }

    /**
     *
     * @param city setCity
     */
    public void setCity(String city) {
        this.city = city;
    }

    /**
     *
     * @param dateFrom setDateFrom
     */
    public void setDateFrom(String dateFrom) {
        this.dateFrom = dateFrom;
    }

    /**
     *
     * @param dateTo setDateTo
     */
    public void setDateTo(String dateTo) {
        this.dateTo = dateTo;
    }
}
