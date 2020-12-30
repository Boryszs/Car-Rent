package com.Server.model;


import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.*;

/**
 * Entity user to store Users data.
 * @author Krystian Cwioro Kamil Bieniasz Damian Mierzynski.
 * @version 1.0
 * @since 2020-12-29.
 */

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_user")
    /**id*/
    private Long id;

    @NotBlank
    @Size(max = 20)
    /**username*/
    private String username;

    @NotBlank
    @Size(max = 50)
    @Email
    /**email*/
    private String email;

    @NotBlank
    @Size(max = 120)
    /**password*/
    private String password;

    @ManyToMany(cascade = CascadeType.PERSIST)
    @JoinTable(name = "users_roles",
            joinColumns = @JoinColumn(name = "id_users"),
            inverseJoinColumns = @JoinColumn(name = "id_roles"))
    /**roles*/
    private List<Role> roles = new LinkedList<>();

    @ManyToMany(cascade = CascadeType.PERSIST,fetch = FetchType.LAZY)
    @JoinTable(name = "users_reservation",
            joinColumns = @JoinColumn(name = "id_users"),
            inverseJoinColumns = @JoinColumn(name = "id_rent"))
    /**reservations*/
    private List<Reservation> reservations = new LinkedList<>();

    /**Constructor*/
    public User() {
    }
    /**Constructor*/
    public User(@NotBlank @Size(max = 20) String username, @NotBlank @Size(max = 50) @Email String email, @NotBlank @Size(max = 120) String password, List<Role> roles) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.roles = roles;
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
     * @return Username
     */
    public String getUsername() {
        return username;
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
     * @return Password
     */
    public String getPassword() {
        return password;
    }

    /**
     *
     * @return Roles
     */
    public List<Role> getRoles() {
        return roles;
    }

    /**
     *
     * @return Reservations
     */
    public List<Reservation> getReservations() {
        return reservations;
    }

    /**
     *
     * @param id setId
     */
    public void setId(Long id) {
        this.id = id;
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
     * @param email setEmail
     */
    public void setEmail(String email) {
        this.email = email;
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
     * @param roles setRoles
     */
    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    /**
     *
     * @param reservations setReservations
     */
    public void setReservations(Reservation reservations) {
        this.reservations.add(reservations);
    }
}
