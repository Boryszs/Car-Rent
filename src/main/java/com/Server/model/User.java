package com.Server.model;


import lombok.*;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

/**
 * Entity user to store Users data.
 *
 * @author Krystian Cwioro Kamil Bieniasz Damian Mierzynski.
 * @version 2.0.
 * @since 2020-04-27.
 */

@Data
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
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

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "users_roles",
            joinColumns = @JoinColumn(name = "id_users"),
            inverseJoinColumns = @JoinColumn(name = "id_roles"))
    /**roles*/
    private List<Role> roles = new LinkedList<>();

    @OneToMany(
            mappedBy = "user",
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    /**reservations*/
    private List<Reservation> reservations = new ArrayList<>();




    /**Constructor*/
    /**
     * @param username Name of User
     * @param email    Email of User
     * @param password Password of User
     * @param roles    Role of User
     */
    public User(@NotBlank @Size(max = 20) String username, @NotBlank @Size(max = 50) @Email String email, @NotBlank @Size(max = 120) String password, List<Role> roles) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.roles = roles;
    }

    /**
     * @param reservations setReservations
     */
    public void setReservations(Reservation reservations) {
        this.reservations.add(reservations);
    }

}
