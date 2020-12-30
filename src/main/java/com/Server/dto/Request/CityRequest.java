package com.Server.dto.Request;

/**
 * Class DTO use to city request.
 * @author Krystian Cwioro Kamil Bieniasz Damian Mierzynski.
 * @version 1.0
 * @since 2020-12-29.
 */

public class CityRequest {

    /**City*/
    private String city;

    /**Constructor*/
    public CityRequest() {
    }

    /**Constructor*/
    public CityRequest(String city) {
        this.city = city;
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
     * @param city setCity
     */
    public void setCity(String city) {
        this.city = city;
    }
}
