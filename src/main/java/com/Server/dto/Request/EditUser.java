package com.Server.dto.Request;

import javax.validation.constraints.Email;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Set;

public class EditUser implements Serializable {


    private long id;
    @Size(min = 3)
    private String username;

    @Email
    private String email;

    private Set<String> role;

    @Size(min = 6, max = 40)
    //walidacja hasla
    //@Pattern(regexp="^[A-Za-z0-9]")
    private String password;

    public EditUser() {
    }

    public EditUser(long id, @Size(min = 3) String username, @Email String email, Set<String> role, @Size(min = 6, max = 40) String password) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.role = role;
        this.password = password;
    }

    public long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<String> getRole() {
        return this.role;
    }

    public void setRole(Set<String> role) {
        this.role = role;
    }

    public void setId(long id) {
        this.id = id;
    }
}
