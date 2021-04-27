package com.Server.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * Entity car to store Users data.
 * @author Krystian Cwioro Kamil Bieniasz Damian Mierzynski.
 * @version 2.0.
 * @since 2020-04-27.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Builder
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
    @OneToOne
    @JoinColumn(name = "id_localization")
    private Localization localization;



    /** Constructor Class Car
     *
     * @param mark Car Brand Name
     * @param model Car Model Name
     * @param type Car Type Name
     * @param yearProduction Year of car production
     * @param color Color of Car
     * @param engineCapacity Car Engine Capacity
     * @param money Money for the Car
     * @param localization Car Location
     * @param image Url to photo of the Car
     */
    public Car(String mark, String model, String type, int yearProduction, String color, int engineCapacity, float money, Localization localization, String image) {
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

}
