package com.Server.model;

import java.util.Set;
/**
 * Class SafetyUser to store user data.
 * @author Krystian Cwioro Kamil Bieniasz Damian Mierzynski.
 * @version 1.0
 * @since 2020-12-29.
 */

public class SafetyUser {
    /**idUser*/
    private int idUser;
    /**userName*/
    private String userName;
    /**email*/
    private String email;
    /**roles*/
    private Set<Role> roles;

    /**Constructor*/
    public SafetyUser(int idUser, String userName, String email, Set<Role> roles) {
        this.idUser = idUser;
        this.userName = userName;
        this.email = email;
        this.roles = roles;
    }

    /**
     *
     * @return IdUser
     */
    public int getIdUser() {
        return idUser;
    }

    /**
     *
     * @param idUser setIdUser
     */
    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    /**
     *
     * @return UserName
     */
    public String getUserName() {
        return userName;
    }

    /**
     *
     * @param userName setUserName
     */
    public void setUserName(String userName) {
        this.userName = userName;
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
     * @return Roles
     */
    public Set<Role> getRoles() {
        return roles;
    }

    /**
     *
     * @param roles setRoles
     */
    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }
}