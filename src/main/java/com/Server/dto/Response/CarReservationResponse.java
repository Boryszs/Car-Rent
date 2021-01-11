package com.Server.dto.Response;

import lombok.*;
import org.springframework.boot.convert.DataSizeUnit;

import java.util.Date;

/**
 * Class DTO get Reservation.
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
public class CarReservationResponse {
    /**mark*/
    private String mark;
    /**model*/
    private String model;
    /**localizationStart*/
    private String localizationStart;
    /**localizationEnd*/
    private String localizationEnd;
    /**dateFrom*/
    private Date dateFrom;
    /**dateTo*/
    private Date dateTo;
    /**price*/
    private float price;
}
