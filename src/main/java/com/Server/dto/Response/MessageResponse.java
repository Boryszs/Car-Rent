package com.Server.dto.Response;

import lombok.*;

/**
 * Class DTO get Message text.
 * @author Krystian Cwioro Kamil Bieniasz Damian Mierzynski.
 * @version 1.0
 * @since 2020-12-29.
 */

@Data
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class MessageResponse {
    /**message*/
    String message;
}
