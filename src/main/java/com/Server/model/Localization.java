package com.Server.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * Entity localization to store Localizations data.
 * @author Krystian Cwioro Kamil Bieniasz Damian Mierzynski.
 * @version 1.0
 * @since 2020-12-29.
 */

@Entity
@Table(name = "localization")
public class Localization {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_localization")
    /**id*/
    private Long id;

    @NotNull
    @Column(name = "city")
    /**city*/
    private String city;

    /**Constructor*/
    public Localization() {
    }

    /**Constructor*/
    public Localization(String city) {
        this.city = city;
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
     * @return City
     */
    public String getCity() {
        return city;
    }

    /**
     *
     * @param city setCity
     */
    public void setCity(String city) {
        this.city = city;
    }
}
