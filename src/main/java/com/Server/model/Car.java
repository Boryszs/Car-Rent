package com.Server.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "car")
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_car")
    private int idcar;
    @NotNull
    @Column(name = "mark")
    private String mark;
    @NotNull
    @Column(name = "model")
    private String model;
    @NotNull
    @Column(name = "type")
    private String type;
    @NotNull
    @Column(name = "year_production")
    private int yearProduction;
    @NotNull
    @Column(name = "color")
    private String color;
    @NotNull
    @Column(name = "engine_capacity")
    private int engineCapacity;
    @NotNull
    @Column(name = "money")
    private float money;

    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "id_localization", referencedColumnName = "id_localization")
    private Localization localization;

    public Car() {
    }

    public Car(String mark, String model, String type, int yearProduction, String color, int engineCapacity, float money, Localization localization) {
        this.idcar = idcar;
        this.mark = mark;
        this.model = model;
        this.type = type;
        this.yearProduction = yearProduction;
        this.color = color;
        this.engineCapacity = engineCapacity;
        this.money = money;
        this.localization = localization;
    }


    public int getIdcar() {
        return idcar;
    }

    public String getModel() {
        return model;
    }

    public void setIdcar(int idcar) {
        this.idcar = idcar;
    }

    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getYearProduction() {
        return yearProduction;
    }

    public void setYearProduction(int yearProduction) {
        this.yearProduction = yearProduction;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getEngineCapacity() {
        return engineCapacity;
    }

    public void setEngineCapacity(int engineCapacity) {
        this.engineCapacity = engineCapacity;
    }

    public Localization getLocalization() {
        return localization;
    }

    public void setLocalization(Localization localization) {
        this.localization = localization;
    }

    public float getMoney() {
        return money;
    }

    public void setMoney(float money) {
        this.money = money;
    }

    public void setModel(String model) {
        this.model = model;
    }
}
