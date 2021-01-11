package com.Server.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * Entity localization to store Localizations data.
 * @author Krystian Cwioro Kamil Bieniasz Damian Mierzynski.
 * @version 1.0
 * @since 2020-12-29.
 */
@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
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



    /** Constructor Class Localization
     *
     * @param city Car location city
     */
    public Localization(String city) {
        this.city = city;
    }

}
