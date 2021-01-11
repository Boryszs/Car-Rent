package com.Server.model;

import lombok.*;

import javax.persistence.*;

/**
 * Entity role to store Role data.
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

}
