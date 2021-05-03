package com.Server.dto.Response;


import lombok.*;

import java.io.Serializable;

/**
 * Class DTO use to get city data.
 * @author Krystian Cwioro Kamil Bieniasz Damian Mierzynski.
 * @version 1.0
 * @since 2020-04-27.
 */

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@EqualsAndHashCode
public class LocalizationResponse implements Serializable{

    private Long id;
    private String city;
}
