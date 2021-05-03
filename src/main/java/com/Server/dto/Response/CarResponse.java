package com.Server.dto.Response;

import lombok.*;

import java.io.Serializable;

/**
 * Class DTO use to get car.
 * @author Krystian Cwioro Kamil Bieniasz Damian Mierzynski.
 * @version 1.0
 * @since 2020-04-27.
 */

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@EqualsAndHashCode
public class CarResponse implements Serializable {

    /**idcar*/
    private int idcar;
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
    /**engineCapacity*/
    private int engineCapacity;
    /**money*/
    private float money;
    /**image*/
    private String image;
    /**localization*/
    private LocalizationResponse localization;
}
