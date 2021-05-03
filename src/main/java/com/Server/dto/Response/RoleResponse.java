package com.Server.dto.Response;

import lombok.*;

import java.io.Serializable;
/**
 * Class DTO role users.
 * @author Krystian Cwioro Kamil Bieniasz Damian Mierzynski.
 * @version 1.0
 * @since 2020-05-03.
 */

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode
@ToString
public class RoleResponse implements Serializable {
    /**
     * id role
     */
    private Integer id;
    /**
     * role name
     */
    private String name;
}
