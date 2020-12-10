package com.Server.model;

import java.util.Set;

public class SafetyUser {
    private int idUser;
    private String userName;
    private String email;
    private Set<Role> roles;

    public SafetyUser(int idUser, String userName, String email, Set<Role> roles) {
        this.idUser = idUser;
        this.userName = userName;
        this.email = email;
        this.roles = roles;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }
}