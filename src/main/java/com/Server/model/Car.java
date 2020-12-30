package com.Server.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * Entity car to store Users data.
 * @author Krystian Cwioro Kamil Bieniasz Damian Mierzynski.
 * @version 1.0
 * @since 2020-12-29.
 */

@Entity
@Table(name = "car")
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_car")
    /**idcar*/
    private int idcar;
    @NotNull
    @Column(name = "mark")
    /**mark*/
    private String mark;
    @NotNull
    @Column(name = "model")
    /**model*/
    private String model;
    @NotNull
    @Column(name = "type")
    /**type*/
    private String type;
    @NotNull
    @Column(name = "year_production")
    /**yearProduction*/
    private int yearProduction;
    @NotNull
    @Column(name = "color")
    /**color*/
    private String color;
    @NotNull
    @Column(name = "engine_capacity")
    /**engineCapacity*/
    private int engineCapacity;
    @NotNull
    @Column(name = "money")
    /**money*/
    private float money;

    @NotNull
    @Column(name = "image")
    /**image*/
    private String image;

    /**localization*/
    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "id_localization", referencedColumnName = "id_localization")
    private Localization localization;

    /**Constructor*/
    public Car() {
    }

    /**Constructor*/
    public Car(String mark, String model, String type, int yearProduction, String color, int engineCapacity, float money, Localization localization, String image) {
        this.idcar = idcar;
        this.mark = mark;
        this.model = model;
        this.type = type;
        this.yearProduction = yearProduction;
        this.color = color;
        this.engineCapacity = engineCapacity;
        this.money = money;
        this.localization = localization;
        this.image = image;
    }

    /**
     *
     * @return Idcar
     */
    public int getIdcar() {
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
     * @param idcar setIdcar
     */
    public void setIdcar(int idcar) {
        this.idcar = idcar;
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
     * @param mark setMark
     */
    public void setMark(String mark) {
        this.mark = mark;
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
     * @param type setType
     */
    public void setType(String type) {
        this.type = type;
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
     * @param yearProduction setYearProduction
     */
    public void setYearProduction(int yearProduction) {
        this.yearProduction = yearProduction;
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
     * @param color setColor
     */
    public void setColor(String color) {
        this.color = color;
    }

    /**
     *
     * @return EngineCapacity
     */
    public int getEngineCapacity() {
        return engineCapacity;
    }

    /**
     *
     * @param engineCapacity setEngineCapacity
     */
    public void setEngineCapacity(int engineCapacity) {
        this.engineCapacity = engineCapacity;
    }

    /**
     *
     * @return Localization
     */
    public Localization getLocalization() {
        return localization;
    }

    /**
     *
     * @param localization setLocalization
     */
    public void setLocalization(Localization localization) {
        this.localization = localization;
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
     * @param money setMoney
     */
    public void setMoney(float money) {
        this.money = money;
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
     * @return Image
     */
    public String getImage() { return image; }

    /**
     *
     * @param image setImage
     */
    public void setImage(String image) { this.image = image; }
}
