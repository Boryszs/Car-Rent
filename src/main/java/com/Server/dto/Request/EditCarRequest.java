package com.Server.dto.Request;

/**
 * Class DTO use to edit car.
 * @author Krystian Cwioro Kamil Bieniasz Damian Mierzynski.
 * @version 1.0
 * @since 2020-12-29.
 */

public class EditCarRequest {
    /**idcar*/
    private Integer idcar;
    /**mark*/
    private String mark;
    /**model*/
    private String model;
    /**type*/
    private String type;
    /**yearProduction*/
    private int yearProduction;
    /**color*/
    private String color;
    /**engine*/
    private int engine;
    /**city*/
    private String city;
    /**money*/
    private float money;
    /**image*/
    private String image;

    /**Constructor*/
    public EditCarRequest() {
    }

    /**Constructor*/
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

    /**
     * @param model setModel
     */
    public void setModel(String model) {
        this.model = model;
    }

    /**
     *
     * @param idcar setIdcar
     */
    public void setIdcar(Integer idcar) {
        this.idcar = idcar;
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
     * @param type setType
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     *
     * @param yearProduction setYearProduction
     */
    public void setYearProduction(int yearProduction) {
        this.yearProduction = yearProduction;
    }

    /**
     *
     * @param color setColor
     */
    public void setColor(String color) {
        this.color = color;
    }

    /**
     *
     * @param engine setEngine
     */
    public void setEngine(int engine) {
        this.engine = engine;
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
     * @param money setMoney
     */
    public void setMoney(float money) {
        this.money = money;
    }

    /**
     *
     * @param image setImage
     */
    public void setImage(String image) {
        this.image = image;
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
     * @return Type
     */
    public String getType() {
        return type;
    }

    /**
     *
     * @return YearProduction
     */
    public int getYearProduction() {
        return yearProduction;
    }

    /**
     *
     * @return Color
     */
    public String getColor() {
        return color;
    }

    /**
     *
     * @return Engine
     */
    public int getEngine() {
        return engine;
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
     * @return Money
     */
    public float getMoney() {
        return money;
    }

    /**
     *
     * @return Idcar
     */
    public Integer getIdcar() {
        return idcar;
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
     * @return Image
     */
    public String getImage() {
        return image;
    }
}
