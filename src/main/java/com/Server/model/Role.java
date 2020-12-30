package com.Server.model;

import javax.persistence.*;

/**
 * Entity role to store Role data.
 * @author Krystian Cwioro Kamil Bieniasz Damian Mierzynski.
 * @version 1.0
 * @since 2020-12-29.
 */

@Entity
@Table(name = "roles")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_role")
    /***/
    private Integer id;

    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    /***/
    private Roles name;

    /**Constructor*/
    public Role() {
    }

    /**Constructor*/
    public Role(Integer id, Roles name) {
        this.id = id;
        this.name = name;
    }

    /**
     *
     * @return Id
     */
    public Integer getId() {
        return id;
    }

    /**
     *
     * @return Name
     */
    public Roles getName() {
        return name;
    }

    /**
     *
     * @param id setId
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     *
     * @param name setName
     */
    public void setName(Roles name) {
        this.name = name;
    }
}
