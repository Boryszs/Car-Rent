package com.Server.dto.Request;


import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Set;

/**
 * Class DTO use to register user.
 * @author Krystian Cwioro Kamil Bieniasz Damian Mierzynski.
 * @version 1.0
 * @since 2020-12-29.
 */

@Data
@Setter
@Getter
@NoArgsConstructor
@EqualsAndHashCode
@AllArgsConstructor
@ToString
@Builder
public class UserRequest implements Serializable {

    @Size(min = 3)
    /**username*/
    private String username;

    @Email
    /**email*/
    private String email;

    /**role*/
    private Set<String> role;

    @Size(min = 6, max = 40)
    //walidacja hasla
    //@Pattern(regexp="^[A-Za-z0-9]")
    /**password*/
    private String password;
}
