package com.Server.dto.Response;

import com.Server.model.Localization;

import java.util.List;

/**
 * Class DTO get Response with token.
 * @author Krystian Cwioro Kamil Bieniasz Damian Mierzynski.
 * @version 1.0
 * @since 2020-12-29.
 */

public class JwtResponse {

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
    private List<Localization> localizations;
    /**List String */
    private List<String> roles;

    /**Constructor*/
    public JwtResponse(String token, Long id, String username, String email, List<Localization> localizations, List<String> roles) {
        this.token = token;
        this.type = type;
        this.id = id;
        this.username = username;
        this.email = email;
        this.localizations = localizations;
        this.roles = roles;
    }

    /**
     *
     * @return Localizations
     */
    public List<Localization> getLocalizations() {
        return localizations;
    }

    /**
     *
     * @return Type
     */
    public String getType() {
        return type;
    }

    /**
     *
     * @return Token
     */
    public String getToken() {
        return token;
    }

    /**
     *
     * @param token setToken
     */
    public void setToken(String token) {
        this.token = token;
    }

    /**
     *
     * @return Id
     */
    public Long getId() {
        return id;
    }

    /**
     *
     * @param id Id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     *
     * @return Email
     */
    public String getEmail() {
        return email;
    }

    /**
     *
     * @param email setEmail
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     *
     * @return Username
     */
    public String getUsername() {
        return username;
    }

    /**
     *
     * @param username setUsername
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     *
     * @return Roles
     */
    public List<String> getRoles() {
        return roles;
    }

    /**
     *
     * @param type setType
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     *
     * @param roles setRoles
     */
    public void setRoles(List<String> roles) {
        this.roles = roles;
    }

    /**
     *
     * @param localizations setLocalizations
     */
    public void setLocalizations(List<Localization> localizations) {
        this.localizations = localizations;
    }
}

