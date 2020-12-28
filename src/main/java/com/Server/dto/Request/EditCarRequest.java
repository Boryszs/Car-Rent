package com.Server.dto.Request;

public class EditCarRequest {
    private Integer idcar;
    private String mark;
    private String model;
    private String type;
    private int yearProduction;
    private String color;
    private int engine;
    private String city;
    private float money;
    private String image;
    public EditCarRequest() {
    }

    public EditCarRequest(Integer idcar, String mark, String model, String type, int yearProduction, String color, int engine, String city, float money,String image) {
        this.idcar = idcar;
        this.mark = mark;
        this.model = model;
        this.type = type;
        this.yearProduction = yearProduction;
        this.color = color;
        this.engine = engine;
        this.city = city;
        this.money = money;
        this.image=image;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public void setIdcar(Integer idcar) {
        this.idcar = idcar;
    }

    public void setMark(String mark) {
        this.mark = mark;
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

    public void setImage(String image) {
        this.image = image;
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

    public Integer getIdcar() {
        return idcar;
    }

    public String getModel() {
        return model;
    }

    public String getImage() {
        return image;
    }
}
