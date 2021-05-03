package com.Server.entiy;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

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
@EqualsAndHashCode
@Builder
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

    @ManyToMany(mappedBy = "roles")
    private List<User> users = new ArrayList<>();

}
