package com.Server.dto.Request;


import javax.validation.constraints.Email;
import javax.validation.constraints.Size;
import java.util.Set;

/**
 * Class DTO use to register user.
 * @author Krystian Cwioro Kamil Bieniasz Damian Mierzynski.
 * @version 1.0
 * @since 2020-12-29.
 */

public class RegisterRequest {

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

    /**Constructor*/
    public RegisterRequest() {
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
     * @return Password
     */
    public String getPassword() {
        return password;
    }

    /**
     *
     * @param password setPassword
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     *
     * @return Set String
     */
    public Set<String> getRole() {
        return this.role;
    }

    /**
     *
     * @param role setRole
     */
    public void setRole(Set<String> role) {
        this.role = role;
    }
}
