package com.Server.dto.Response;

import lombok.*;

import java.io.Serializable;
import java.util.List;

/**
 * Class DTO get Response with token.
 * @author Krystian Cwioro Kamil Bieniasz Damian Mierzynski.
 * @version 2.0.
 * @since 2020-04-27.
 */

@Data
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class JwtResponse implements Serializable {

    /**token*/
    private String token;
    /**type*/
    private String type = "Bearer";
    /**id*/
    private Long id;
    /**username*/
    private String username;
    /**email*/
    private String email;
    /**List Localization */
    private List<LocalizationResponse> localizations;
    /**List String */
    private List<String> roles;

    /**Constructor*/
    /**
     *
     * @param token Token
     * @param id ID
     * @param username Name of User
     * @param email Email of User
     * @param localizations All Localizations
     * @param roles Role of User
     */
    public JwtResponse(String token, Long id, String username, String email, List<LocalizationResponse> localizations, List<String> roles) {
        this.token = token;
        this.type = type;
        this.id = id;
        this.username = username;
        this.email = email;
        this.localizations = localizations;
        this.roles = roles;
    }
}

