package com.Server.dto.Request;


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
public class AddReservationRequest implements Serializable{
    @NotNull
    @Min(1)
    /**id user*/
    private Long id_user;
    @NotNull
    @Min(1)
    /**id car*/
    private int id_car;
    /** dato to reservation*/
    private String dateto;
    /** data from reservation*/
    private String datefrom;
    @NotBlank
    /**localization end*/
    private String localization_end;
    @NotBlank
    /**localization start*/
    private String localization_start;
}
