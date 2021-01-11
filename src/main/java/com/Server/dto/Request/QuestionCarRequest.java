package com.Server.dto.Request;

import lombok.*;

import javax.validation.constraints.NotBlank;

/**
 * Class DTO use to query a reservation.
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
public class QuestionCarRequest {
    @NotBlank
    /**city*/
    private String city;
    @NotBlank
    /**dateFrom*/
    private String dateFrom;
    @NotBlank
    /**dateTo*/
    private String dateTo;
}
