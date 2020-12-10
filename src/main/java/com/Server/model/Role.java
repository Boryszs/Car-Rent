package com.Server.model;

import javax.persistence.*;

@Entity
@Table(name = "roles")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_role")
    private Integer id;

    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    private Roles name;

    public Role() {
    }

    public Role(Integer id, Roles name) {
        this.id = id;
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public Roles getName() {
        return name;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setName(Roles name) {
        this.name = name;
    }
}
