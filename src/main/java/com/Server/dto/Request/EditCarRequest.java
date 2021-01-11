package com.Server.dto.Request;

import lombok.*;

/**
 * Class DTO use to edit car.
 * @author Krystian Cwioro Kamil Bieniasz Damian Mierzynski.
 * @version 1.0
 * @since 2020-12-29.
 */
@Data
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
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
}
