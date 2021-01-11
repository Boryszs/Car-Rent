package com.Server.dto.Request;

import lombok.*;

import java.io.Serializable;


/**
 *  Class DTO use authorization user.
 *  @author @author Krystian Cwioro Kamil Bieniasz Damian Mierzynski.
 *  @version 1.0
 *  @since 2020-12-29.
 */

@Data
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class LoginRequest implements Serializable {

    /**serialVersionUID*/
    private static final long serialVersionUID = 5926468583005150707L;
    /**username*/
    private String username;
    /**password*/
    private String password;
}
