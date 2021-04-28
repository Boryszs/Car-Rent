package com.Server.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * Entity localization to store Localizations data.
 * @author Krystian Cwioro Kamil Bieniasz Damian Mierzynski.
 * @version 2.0.
 * @since 2020-04-27.
 */
@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
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

    @OneToMany(mappedBy = "localization", fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    private List<Car> car;

    /** Constructor Class Localization
     *
     * @param city Car location city
     */
    public Localization(String city) {
        this.city = city;
    }

}
