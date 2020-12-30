package com.Server.dto.Request;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * Class DTO use to add car.
 * @author Krystian Cwioro Kamil Bieniasz Damian Mierzynski.
 * @version 1.0
 * @since 2020-12-29.
 */

public class AddCarRequest {
    @NotBlank
    /**Mark car*/
    private String mark;
    @NotBlank
    /**Model car*/
    private String model;
    @NotBlank
    /**Type car*/
    private String type;
    @NotNull
    @Min(1980)
    /**Year car*/
    private int yearProduction;
    @NotBlank
    /**Color car*/
    private String color;
    @NotNull
    @Min(500)
    /**Engine car*/
    private int engine;
    @NotBlank
    /**City car*/
    private String city;
    @NotNull
    @Min(40)
    /**Money car*/
    private float money;
    @NotBlank
    /**Image car*/
    private String image;


    /**
     * Constructor
     */
    public AddCarRequest() {
    }

    /**
     * Constructor
     */
    public AddCarRequest(String mark, String model, String type, int yearProduction, String color, int engine, String city, float money, String image) {
        this.mark = mark;
        this.model = model;
        this.type = type;
        this.yearProduction = yearProduction;
        this.color = color;
        this.engine = engine;
        this.city = city;
        this.money = money;
        this.image = image;
    }

    /**
     * @param mark setMark
     */
    public void setMark(String mark) {
        this.mark = mark;
    }

    /**
     * @param model setModel
     */
    public void setModel(String model) {
        this.model = model;
    }

    /**
     * @param type setType
     */

    public void setType(String type) {
        this.type = type;
    }

    /**
     * @param yearProduction setYearProduction
     */
    public void setYearProduction(int yearProduction) {
        this.yearProduction = yearProduction;
    }

    /**
     * @param color setColor
     */
    public void setColor(String color) {
        this.color = color;
    }

    /**
     * @param engine setEngine
     */
    public void setEngine(int engine) {
        this.engine = engine;
    }

    /**
     * @param city setCity
     */
    public void setCity(String city) {
        this.city = city;
    }

    /**
     * @param money setMoney
     */
    public void setMoney(float money) {
        this.money = money;
    }

    /**
     * @return Mark
     */
    public String getMark() {
        return mark;
    }

    /**
     *
     * @return Type
     */
    public String getType() {
        return type;
    }

    /**
     * @return YearProduction
     */
    public int getYearProduction() {
        return yearProduction;
    }

    /**
     * @return Color
     */
    public String getColor() {
        return color;
    }

    /**
     * @return Engine
     */
    public int getEngine() {
        return engine;
    }

    /**
     * @return City
     */
    public String getCity() {
        return city;
    }

    /**
     * @return Money
     */
    public float getMoney() {
        return money;
    }

    /**
     * @return Model
     */
    public String getModel() {
        return model;
    }

    /**
     * @return Image
     */
    public String getImage() {
        return image;
    }

    /**
     * @param image setImage
     */
    public void setImage(String image) {
        this.image = image;
    }
}
