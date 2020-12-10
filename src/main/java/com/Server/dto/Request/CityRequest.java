package com.Server.dto.Request;

public class CityRequest {

    private String city;

    public CityRequest() {
    }

    public CityRequest(String city) {
        this.city = city;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}
