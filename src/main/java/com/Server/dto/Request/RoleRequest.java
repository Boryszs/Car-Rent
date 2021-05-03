package com.Server.dto.Request;

import lombok.*;

import java.io.Serializable;

/**
 * Class DTO role users.
 * @author Krystian Cwioro Kamil Bieniasz Damian Mierzynski.
 * @version 1.0
 * @since 2020-05-03.
 */

@Data
@Setter
@Getter
@NoArgsConstructor
@EqualsAndHashCode
@AllArgsConstructor
@ToString
public class RoleRequest implements Serializable {
    /**
     * name role
     */
    private String name;

}
