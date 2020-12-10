package com.Server.dto.Request;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

public class AddCarRequest {
    @NotBlank
    private String mark;
    @NotBlank
    private String model;
    @NotBlank
    private String type;
    @NotNull
    @Min(1980)
    private int yearProduction;
    @NotBlank
    private String color;
    @NotNull
    @Min(500)
    private int engine;
    @NotBlank
    private String city;
    @NotNull
    @Min(40)
    private float money;

    public AddCarRequest() {
    }

    public AddCarRequest(String mark, String model, String type, int yearProduction, String color, int engine, String city, float money) {
        this.mark = mark;
        this.model = model;
        this.type = type;
        this.yearProduction = yearProduction;
        this.color = color;
        this.engine = engine;
        this.city = city;
        this.money = money;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setYearProduction(int yearProduction) {
        this.yearProduction = yearProduction;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public void setEngine(int engine) {
        this.engine = engine;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setMoney(float money) {
        this.money = money;
    }

    public String getMark() {
        return mark;
    }

    public String getType() {
        return type;
    }

    public int getYearProduction() {
        return yearProduction;
    }

    public String getColor() {
        return color;
    }

    public int getEngine() {
        return engine;
    }

    public String getCity() {
        return city;
    }

    public float getMoney() {
        return money;
    }

    public String getModel() {
        return model;
    }
}
