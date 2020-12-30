package com.Server.dto.Request;

import java.io.Serializable;


/**
 *  Class DTO use authorization user.
 *  @author @author Krystian Cwioro Kamil Bieniasz Damian Mierzynski.
 *  @version 1.0
 *  @since 2020-12-29.
 */

public class LoginRequest implements Serializable {

    /**serialVersionUID*/
    private static final long serialVersionUID = 5926468583005150707L;

    /**username*/
    private String username;
    /**password*/
    private String password;

    /**Constructor*/
    public LoginRequest() {

    }

    /**Constructor*/
    public LoginRequest(String username, String password) {
        this.setUsername(username);
        this.setPassword(password);
    }

    /**
     *
     * @return Username
     */
    public String getUsername() {
        return this.username;
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
     * @return Password
     */
    public String getPassword() {
        return this.password;
    }

    /**
     *
     * @param password setPassword
     */
    public void setPassword(String password) {
        this.password = password;
    }
}