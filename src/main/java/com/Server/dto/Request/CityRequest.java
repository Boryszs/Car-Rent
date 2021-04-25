package com.Server.dto.Request;

import lombok.*;

import java.io.Serializable;

/**
 * Class DTO use to city request.
 * @author Krystian Cwioro Kamil Bieniasz Damian Mierzynski.
 * @version 1.0
 * @since 2020-12-29.
 */

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class CityRequest implements Serializable {

    /**City*/
    private String city;

}
