package com.Server.dto.Request;

import lombok.*;

import java.io.Serializable;

/**
 * Class DTO use to city request.
 * @author Krystian Cwioro Kamil Bieniasz Damian Mierzynski.
 * @version 2.0.
 * @since 2020-04-27.
 */

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class LocalizationRequest implements Serializable {

    /**City*/
    private String city;

}
