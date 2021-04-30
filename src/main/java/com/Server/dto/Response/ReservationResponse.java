package com.Server.dto.Response;


import lombok.*;

import java.io.Serializable;

/**
 * Class DTO use to add reservation.
 * @author Krystian Cwioro Kamil Bieniasz Damian Mierzynski.
 * @version 2.0.
 * @since 2020-04-27.
 */
@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class ReservationResponse implements Serializable{
    /**id rent*/
    private Long idRent;
    /**car response*/
    private CarResponse carResponse;
    /** dato to reservation*/
    private String dateTo;
    /** data from reservation*/
    private String dateFrom;
    /**localization end*/
    private LocalizationResponse localizationEnd;
    /**localization start*/
    private LocalizationResponse localizationStart;
    private float price;
}
