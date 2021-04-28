package com.Server.dto.Request;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
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
@ToString
public class ReservationRequest implements Serializable{
    @NotNull
    @Min(1)
    /**id user*/
    @JsonProperty("id_user")
    private Long idUser;
    @NotNull
    @Min(1)
    /**id car*/
    @JsonProperty("id_car")
    private int idCar;
    /** dato to reservation*/
    @JsonProperty("dateto")
    private String dateTo;
    /** data from reservation*/
    @JsonProperty("datefrom")
    private String dateFrom;
    @NotBlank
    /**localization end*/
    @JsonProperty("localization_end")
    private String localizationEnd;
    @NotBlank
    /**localization start*/
    @JsonProperty("localization_start")
    private String localizationStart;
}
